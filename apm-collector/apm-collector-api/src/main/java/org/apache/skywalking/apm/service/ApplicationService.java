package org.apache.skywalking.apm.service;

import org.apache.skywalking.apm.agent.core.context.model.UpstreamSegment;
import org.apache.skywalking.apm.agent.core.disk.model.DiskMetrics;
import org.apache.skywalking.apm.agent.core.jvm.model.JVMMetric;
import org.apache.skywalking.apm.agent.core.jvm.model.JVMMetrics;
import org.apache.skywalking.apm.agent.core.remote.model.Application;
import org.apache.skywalking.apm.agent.core.remote.model.ApplicationInstance;
import org.apache.skywalking.apm.agent.core.remote.model.ApplicationInstanceHeartbeat;

import java.util.LinkedList;
import java.util.List;

/**
 * @author meixinbin
 */
public interface ApplicationService {


	/**
	 * register application info
	 * @param application
	 */
	void register(Application application);

	int registerInstance(ApplicationInstance applicationInstance);

	void heartbeat(ApplicationInstanceHeartbeat applicationInstanceHeartbeat);

	void collectorTraceInfo(UpstreamSegment upstreamSegment);

	void jvmMetrics(JVMMetrics jvmMetrics);

	void diskMetrics(DiskMetrics diskMetrics);

}
