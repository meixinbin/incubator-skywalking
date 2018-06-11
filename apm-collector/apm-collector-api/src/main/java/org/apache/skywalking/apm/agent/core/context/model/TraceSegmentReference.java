package org.apache.skywalking.apm.agent.core.context.model;

import java.io.Serializable;

/**
 * @author meixinbin
 */
public class TraceSegmentReference implements Serializable{
	private RefType refType;
	private String parentTraceSegmentId;
	private int parentSpanId;
	private String parentApplicationInstanceId;
	private String networkAddress;
	private int networkAddressId;
	private String entryApplicationInstanceId;
	private String entryServiceName;
	private int entryServiceId;
	private String parentServiceName;
	private int parentServiceId;

	public RefType getRefType() {
		return refType;
	}

	public void setRefType(RefType refType) {
		this.refType = refType;
	}

	public String getParentTraceSegmentId() {
		return parentTraceSegmentId;
	}

	public void setParentTraceSegmentId(String parentTraceSegmentId) {
		this.parentTraceSegmentId = parentTraceSegmentId;
	}

	public int getParentSpanId() {
		return parentSpanId;
	}

	public void setParentSpanId(int parentSpanId) {
		this.parentSpanId = parentSpanId;
	}

	public String getParentApplicationInstanceId() {
		return parentApplicationInstanceId;
	}

	public void setParentApplicationInstanceId(String parentApplicationInstanceId) {
		this.parentApplicationInstanceId = parentApplicationInstanceId;
	}

	public String getNetworkAddress() {
		return networkAddress;
	}

	public void setNetworkAddress(String networkAddress) {
		this.networkAddress = networkAddress;
	}

	public int getNetworkAddressId() {
		return networkAddressId;
	}

	public void setNetworkAddressId(int networkAddressId) {
		this.networkAddressId = networkAddressId;
	}

	public String getEntryApplicationInstanceId() {
		return entryApplicationInstanceId;
	}

	public void setEntryApplicationInstanceId(String entryApplicationInstanceId) {
		this.entryApplicationInstanceId = entryApplicationInstanceId;
	}

	public String getEntryServiceName() {
		return entryServiceName;
	}

	public void setEntryServiceName(String entryServiceName) {
		this.entryServiceName = entryServiceName;
	}

	public int getEntryServiceId() {
		return entryServiceId;
	}

	public void setEntryServiceId(int entryServiceId) {
		this.entryServiceId = entryServiceId;
	}

	public String getParentServiceName() {
		return parentServiceName;
	}

	public void setParentServiceName(String parentServiceName) {
		this.parentServiceName = parentServiceName;
	}

	public int getParentServiceId() {
		return parentServiceId;
	}

	public void setParentServiceId(int parentServiceId) {
		this.parentServiceId = parentServiceId;
	}

	@Override
	public String toString() {
		return "TraceSegmentReference{" +
				"refType=" + refType +
				", parentTraceSegmentId=" + parentTraceSegmentId +
				", parentSpanId=" + parentSpanId +
				", parentApplicationInstanceId=" + parentApplicationInstanceId +
				", networkAddress='" + networkAddress + '\'' +
				", networkAddressId=" + networkAddressId +
				", entryApplicationInstanceId=" + entryApplicationInstanceId +
				", entryServiceName='" + entryServiceName + '\'' +
				", entryServiceId=" + entryServiceId +
				", parentServiceName='" + parentServiceName + '\'' +
				", parentServiceId=" + parentServiceId +
				'}';
	}
}
