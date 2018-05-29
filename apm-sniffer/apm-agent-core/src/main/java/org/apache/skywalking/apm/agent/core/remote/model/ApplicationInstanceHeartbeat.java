package org.apache.skywalking.apm.agent.core.remote.model;

/**
 * @author meixinbin
 */
public class ApplicationInstanceHeartbeat {
	private int applicationInstanceId;
	private long heartbeatTime;

	public int getApplicationInstanceId() {
		return applicationInstanceId;
	}

	public void setApplicationInstanceId(int applicationInstanceId) {
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
