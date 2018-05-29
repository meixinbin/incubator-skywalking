package org.apache.skywalking.apm.agent.core.remote.model;

/**
 * @author meixinbin
 */
public class ApplicationInstanceMapping {
	private int applicationId;
	private int applicationInstanceId;

	public int getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(int applicationId) {
		this.applicationId = applicationId;
	}

	public int getApplicationInstanceId() {
		return applicationInstanceId;
	}

	public void setApplicationInstanceId(int applicationInstanceId) {
		this.applicationInstanceId = applicationInstanceId;
	}
}
