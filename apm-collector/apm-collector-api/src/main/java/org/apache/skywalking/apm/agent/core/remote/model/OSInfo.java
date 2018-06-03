package org.apache.skywalking.apm.agent.core.remote.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author meixinbin
 */
public class OSInfo implements Serializable{
	private String osName;
	private String hostname;
	private int processNo;
	private List<String> ipv4s = new ArrayList<String>();

	public void addAllIpv4S(List<String> ip){
		ipv4s.addAll(ip);
	}
	public String getOsName() {
		return osName;
	}

	public void setOsName(String osName) {
		this.osName = osName;
	}

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public int getProcessNo() {
		return processNo;
	}

	public void setProcessNo(int processNo) {
		this.processNo = processNo;
	}

	public List<String> getIpv4s() {
		return ipv4s;
	}

	public void setIpv4s(List<String> ipv4s) {
		this.ipv4s = ipv4s;
	}

	@Override
	public String toString() {
		return "OSInfo{" +
				"osName='" + osName + '\'' +
				", hostname='" + hostname + '\'' +
				", processNo=" + processNo +
				", ipv4s=" + ipv4s +
				'}';
	}
}
