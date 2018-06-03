package com.minshenglife.apm.collector.service;

import com.alibaba.dubbo.config.annotation.DubboService;
import org.apache.skywalking.apm.agent.core.context.model.UpstreamSegment;
import org.apache.skywalking.apm.agent.core.disk.model.DiskMetrics;
import org.apache.skywalking.apm.agent.core.jvm.model.JVMMetric;
import org.apache.skywalking.apm.agent.core.jvm.model.JVMMetrics;
import org.apache.skywalking.apm.agent.core.remote.model.Application;
import org.apache.skywalking.apm.agent.core.remote.model.ApplicationInstance;
import org.apache.skywalking.apm.agent.core.remote.model.ApplicationInstanceHeartbeat;
import org.apache.skywalking.apm.service.ApplicationService;

import java.util.List;

/**
 * @author meixinbin
 */
@DubboService(version = "1.0")
public class ApplicationServiceImpl implements ApplicationService {

	@Override
	public void register(Application application) {
		System.out.println(application);
	}

	@Override
	public int registerInstance(ApplicationInstance applicationInstance) {
		System.out.println(applicationInstance);
		return 1;
	}

	@Override
	public void heartbeat(ApplicationInstanceHeartbeat applicationInstanceHeartbeat) {
		System.out.println(applicationInstanceHeartbeat);
	}

	@Override
	public void collectorTraceInfo(UpstreamSegment upstreamSegment) {

		System.out.println(upstreamSegment);
	}

	@Override
	public void jvmMetrics(JVMMetrics jvmMetrics) {
		System.out.println(jvmMetrics);

	}

	@Override
	public void diskMetrics(DiskMetrics diskMetrics) {

		System.out.println(diskMetrics);
	}

}
