package org.apache.skywalking.apm.agent.core.remote.model;

import java.io.Serializable;

/**
 * @author meixinbin
 */
public class ApplicationInstanceHeartbeat implements Serializable{
	private String applicationInstanceId;
	private long heartbeatTime;

	public String getApplicationInstanceId() {
		return applicationInstanceId;
	}

	public void setApplicationInstanceId(String applicationInstanceId) {
		this.applicationInstanceId = applicationInstanceId;
	}

	public long getHeartbeatTime() {
		return heartbeatTime;
	}

	public void setHeartbeatTime(long heartbeatTime) {
		this.heartbeatTime = heartbeatTime;
	}

	@Override
	public String toString() {
		return "ApplicationInstanceHeartbeat{" +
				"applicationInstanceId=" + applicationInstanceId +
				", heartbeatTime=" + heartbeatTime +
				'}';
	}
}
