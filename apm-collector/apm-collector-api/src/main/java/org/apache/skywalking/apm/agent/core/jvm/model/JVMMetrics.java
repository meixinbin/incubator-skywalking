package org.apache.skywalking.apm.agent.core.jvm.model;

import java.io.Serializable;
import java.util.List;

/**
 * @author meixinbin
 */
public class JVMMetrics implements Serializable{

	private List<JVMMetric> metrics;

	private int applicationInstanceId;

	public List<JVMMetric> getMetrics() {
		return metrics;
	}

	public void setMetrics(List<JVMMetric> metrics) {
		this.metrics = metrics;
	}

	public int getApplicationInstanceId() {
		return applicationInstanceId;
	}

	public void setApplicationInstanceId(int applicationInstanceId) {
		this.applicationInstanceId = applicationInstanceId;
	}

	@Override
	public String toString() {
		return "JVMMetrics{" +
				"metrics=" + metrics +
				", applicationInstanceId=" + applicationInstanceId +
				'}';
	}
}
