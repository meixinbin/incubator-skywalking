package org.apache.skywalking.apm.agent.core.remote.model;

import java.io.Serializable;

/**
 * @author meixinbin
 */
public class Application implements Serializable{

	private String applicationId;

	public String getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	@Override
	public String toString() {
		return "Application{" +
				"applicationId='" + applicationId + '\'' +
				'}';
	}
}
