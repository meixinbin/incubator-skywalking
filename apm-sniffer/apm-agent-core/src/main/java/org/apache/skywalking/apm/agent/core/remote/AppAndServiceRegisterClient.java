/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package org.apache.skywalking.apm.agent.core.remote;

import org.apache.skywalking.apm.agent.core.boot.BootService;
import org.apache.skywalking.apm.agent.core.boot.DefaultImplementor;
import org.apache.skywalking.apm.agent.core.boot.DefaultNamedThreadFactory;
import org.apache.skywalking.apm.agent.core.boot.ServiceManager;
import org.apache.skywalking.apm.agent.core.conf.Config;
import org.apache.skywalking.apm.agent.core.conf.RemoteDownstreamConfig;
import org.apache.skywalking.apm.agent.core.context.TracingContext;
import org.apache.skywalking.apm.agent.core.context.TracingContextListener;
import org.apache.skywalking.apm.agent.core.context.trace.TraceSegment;
import org.apache.skywalking.apm.agent.core.logging.api.ILog;
import org.apache.skywalking.apm.agent.core.logging.api.LogManager;
import org.apache.skywalking.apm.agent.core.os.OSUtil;
import org.apache.skywalking.apm.agent.core.remote.dubbo.DubboConfig;
import org.apache.skywalking.apm.agent.core.remote.model.Application;
import org.apache.skywalking.apm.agent.core.remote.model.ApplicationInstance;
import org.apache.skywalking.apm.agent.core.remote.model.ApplicationInstanceHeartbeat;
import org.apache.skywalking.apm.service.ApplicationService;
import org.apache.skywalking.apm.util.RunnableWithExceptionProtection;

import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author wusheng
 */
@DefaultImplementor
public class AppAndServiceRegisterClient implements BootService, Runnable, TracingContextListener {
    private static final ILog logger = LogManager.getLogger(AppAndServiceRegisterClient.class);
    private static final String PROCESS_UUID = UUID.randomUUID().toString().replaceAll("-", "");

    private ApplicationService applicationService;

    private volatile ScheduledFuture<?> applicationRegisterFuture;
    private volatile long lastSegmentTime = -1;
	private boolean shouldTry = true;

    @Override
    public void prepare() throws Throwable {
    }

    @Override
    public void boot() throws Throwable {
        applicationService = ServiceManager.INSTANCE.findService(DubboConfig.class).getSerivce(ApplicationService.class,"1.0");
        applicationRegisterFuture = Executors
            .newSingleThreadScheduledExecutor(new DefaultNamedThreadFactory("AppAndServiceRegisterClient"))
            .scheduleAtFixedRate(new RunnableWithExceptionProtection(this, new RunnableWithExceptionProtection.CallbackWhenException() {
                @Override public void handle(Throwable t) {
                    logger.error("unexpected exception.", t);
                }
            }), 0, Config.Collector.APP_AND_SERVICE_REGISTER_CHECK_INTERVAL, TimeUnit.SECONDS);
    }

    @Override
    public void onComplete() throws Throwable {
        TracingContext.ListenerManager.add(this);
    }

    @Override
    public void shutdown() throws Throwable {
        applicationRegisterFuture.cancel(true);
    }

    @Override
    public void run() {
        if(shouldTry){
			//register application info
			Application application = new Application();
			application.setApplicationId(Config.Agent.APPLICATION_ID);
			ApplicationInstance applicationInstance = new ApplicationInstance();
			applicationInstance.setApplicationId(Config.Agent.APPLICATION_ID);
			applicationInstance.setAgentUUID(PROCESS_UUID);
			applicationInstance.setRegisterTime(System.currentTimeMillis());
			applicationInstance.setOsinfo(OSUtil.buildOSInfo());
			try {
				applicationService.register(application);
				String insId = applicationService.registerInstance(applicationInstance);
				RemoteDownstreamConfig.Agent.APPLICATION_INSTANCE_ID = insId;
				shouldTry = false;
			} catch (Exception e) {
				e.printStackTrace();
				shouldTry = true;
			}
		}
		if(System.currentTimeMillis()-lastSegmentTime>60*1000){
			ApplicationInstanceHeartbeat applicationInstanceHeartbeat = new ApplicationInstanceHeartbeat();
			applicationInstanceHeartbeat.setApplicationInstanceId(RemoteDownstreamConfig.Agent.APPLICATION_INSTANCE_ID);
			applicationInstanceHeartbeat.setHeartbeatTime(System.currentTimeMillis());
			applicationService.heartbeat(applicationInstanceHeartbeat);
		}
    }

    @Override
    public void afterFinished(TraceSegment traceSegment) {
        lastSegmentTime = System.currentTimeMillis();
    }
}
