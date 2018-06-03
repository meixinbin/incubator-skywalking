package org.apache.skywalking.apm.agent.core.remote.model;

import java.io.Serializable;

/**
 * @author meixinbin
 */
public class ApplicationInstanceRecover implements Serializable{
	private int applicationId;
	private int applicationInstanceId;
	private long registerTime;
	private OSInfo osinfo;

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
}
