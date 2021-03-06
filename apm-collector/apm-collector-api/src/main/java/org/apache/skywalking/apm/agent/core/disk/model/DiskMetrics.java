package org.apache.skywalking.apm.agent.core.disk.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author meixinbin
 */
public class DiskMetrics implements Serializable{

	private String applicationInstanceId;

	private List<Disk> list;

	public String getApplicationInstanceId() {
		return applicationInstanceId;
	}

	public void setApplicationInstanceId(String applicationInstanceId) {
		this.applicationInstanceId = applicationInstanceId;
	}

	public List<Disk> getList() {
		return list;
	}

	public void setList(List<Disk> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "DiskMetrics{" +
				"applicationInstanceId=" + applicationInstanceId +
				", list=" + list +
				'}';
	}
}
