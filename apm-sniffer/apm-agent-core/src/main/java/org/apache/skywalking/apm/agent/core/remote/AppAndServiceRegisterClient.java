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
import org.apache.skywalking.apm.agent.core.conf.Config;
import org.apache.skywalking.apm.agent.core.conf.RemoteDownstreamConfig;
import org.apache.skywalking.apm.agent.core.context.TracingContext;
import org.apache.skywalking.apm.agent.core.context.TracingContextListener;
import org.apache.skywalking.apm.agent.core.context.trace.TraceSegment;
import org.apache.skywalking.apm.agent.core.logging.api.ILog;
import org.apache.skywalking.apm.agent.core.logging.api.LogManager;
import org.apache.skywalking.apm.agent.core.os.OSUtil;
import org.apache.skywalking.apm.agent.core.remote.model.Application;
import org.apache.skywalking.apm.agent.core.remote.model.ApplicationInstance;
import org.apache.skywalking.apm.agent.core.remote.model.ApplicationInstanceHeartbeat;
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

    private volatile ScheduledFuture<?> applicationRegisterFuture;
    private volatile long lastSegmentTime = -1;

    @Override
    public void prepare() throws Throwable {
    }

    @Override
    public void boot() throws Throwable {
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
        Application application = new Application();
        application.setApplicationCode(Config.Agent.APPLICATION_CODE);
        //TODO
        System.out.println(application);
        ApplicationInstance applicationInstance = new ApplicationInstance();
        applicationInstance.setApplicationId(RemoteDownstreamConfig.Agent.APPLICATION_ID);
        applicationInstance.setAgentUUID(PROCESS_UUID);
        applicationInstance.setRegisterTime(System.currentTimeMillis());
        applicationInstance.setOsinfo(OSUtil.buildOSInfo());
        System.out.println(applicationInstance);
        ApplicationInstanceHeartbeat applicationInstanceHeartbeat = new ApplicationInstanceHeartbeat();
        applicationInstanceHeartbeat.setApplicationInstanceId(RemoteDownstreamConfig.Agent.APPLICATION_INSTANCE_ID);
        applicationInstanceHeartbeat.setHeartbeatTime(System.currentTimeMillis());
        System.out.println(applicationInstanceHeartbeat);
        //TODO
        boolean shouldTry = true;
       /* while (GRPCChannelStatus.CONNECTED.equals(status) && shouldTry) {
            shouldTry = false;
            try {
                if (RemoteDownstreamConfig.Agent.APPLICATION_ID == DictionaryUtil.nullValue()) {
                    if (applicationRegisterServiceBlockingStub != null) {
                        ApplicationMapping applicationMapping = applicationRegisterServiceBlockingStub.applicationCodeRegister(
                            Application.newBuilder().setApplicationCode(Config.Agent.APPLICATION_CODE).build());
                        if (applicationMapping != null) {
                            RemoteDownstreamConfig.Agent.APPLICATION_ID = applicationMapping.getApplication().getValue();
                            shouldTry = true;
                        }
                    }
                } else {
                    if (instanceDiscoveryServiceBlockingStub != null) {
                        if (RemoteDownstreamConfig.Agent.APPLICATION_INSTANCE_ID == DictionaryUtil.nullValue()) {

                            ApplicationInstanceMapping instanceMapping = instanceDiscoveryServiceBlockingStub.registerInstance(ApplicationInstance.newBuilder()
                                .setApplicationId(RemoteDownstreamConfig.Agent.APPLICATION_ID)
                                .setAgentUUID(PROCESS_UUID)
                                .setRegisterTime(System.currentTimeMillis())
                                .setOsinfo(OSUtil.buildOSInfo())
                                .build());
                            if (instanceMapping.getApplicationInstanceId() != DictionaryUtil.nullValue()) {
                                RemoteDownstreamConfig.Agent.APPLICATION_INSTANCE_ID
                                    = instanceMapping.getApplicationInstanceId();
                            }
                        } else {
                            if (lastSegmentTime - System.currentTimeMillis() > 60 * 1000) {
                                instanceDiscoveryServiceBlockingStub.heartbeat(ApplicationInstanceHeartbeat.newBuilder()
                                    .setApplicationInstanceId(RemoteDownstreamConfig.Agent.APPLICATION_INSTANCE_ID)
                                    .setHeartbeatTime(System.currentTimeMillis())
                                    .build());
                            }

                            NetworkAddressDictionary.INSTANCE.syncRemoteDictionary(networkAddressRegisterServiceBlockingStub);
                            OperationNameDictionary.INSTANCE.syncRemoteDictionary(serviceNameDiscoveryServiceBlockingStub);
                        }
                    }
                }
            } catch (Throwable t) {
                logger.error(t, "AppAndServiceRegisterClient execute fail.");
                ServiceManager.INSTANCE.findService(GRPCChannelManager.class).reportError(t);
            }
        }*/
    }

    @Override
    public void afterFinished(TraceSegment traceSegment) {
        lastSegmentTime = System.currentTimeMillis();
    }
}
