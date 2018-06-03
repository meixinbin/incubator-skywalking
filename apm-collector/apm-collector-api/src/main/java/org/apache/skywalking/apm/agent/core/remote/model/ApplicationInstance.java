package org.apache.skywalking.apm.agent.core.remote.model;

import java.io.Serializable;

/**
 * @author meixinbin
 */
public class ApplicationInstance implements Serializable{
	private String applicationId;
	private String agentUUID;
	private long registerTime;
	private OSInfo osinfo;

	public String getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	public String getAgentUUID() {
		return agentUUID;
	}

	public void setAgentUUID(String agentUUID) {
		this.agentUUID = agentUUID;
	}

	public long getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(long registerTime) {
		this.registerTime = registerTime;
	}

	public OSInfo getOsinfo() {
		return osinfo;
	}

	public void setOsinfo(OSInfo osinfo) {
		this.osinfo = osinfo;
	}

	@Override
	public String toString() {
		return "ApplicationInstance{" +
				"applicationId=" + applicationId +
				", agentUUID='" + agentUUID + '\'' +
				", registerTime=" + registerTime +
				", osinfo=" + osinfo +
				'}';
	}
}
