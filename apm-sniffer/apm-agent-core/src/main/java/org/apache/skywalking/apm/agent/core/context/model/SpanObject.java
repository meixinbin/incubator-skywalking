package org.apache.skywalking.apm.agent.core.context.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author meixinbin
 */
public class SpanObject {
	private int spanId;
	private int parentSpanId;
	private long startTime;
	private long endTime;
	private List<TraceSegmentReference> refs=new ArrayList<TraceSegmentReference>();
	private int operationNameId;
	private String operationName;
	private int peerId;
	private String peer;
	private SpanType spanType;
	private SpanLayer spanLayer;
	private int spanLayerValue;
	private int componentId;
	private String component;
	private boolean isError;
	private List<KeyWithStringValue> tags = new ArrayList<KeyWithStringValue>();
	private List<LogMessage> logs = new ArrayList<LogMessage>();


	public void addTags(KeyWithStringValue tag){
		tags.add(tag);
	}
	public void addLogs(LogMessage log){
		logs.add(log);
	}
	public void addRefs(TraceSegmentReference traceSegmentReference){
		refs.add(traceSegmentReference);
	}
	public int getSpanId() {
		return spanId;
	}

	public void setSpanId(int spanId) {
		this.spanId = spanId;
	}

	public int getSpanLayerValue() {
		return spanLayerValue;
	}

	public void setSpanLayerValue(int spanLayerValue) {
		this.spanLayerValue = spanLayerValue;
	}

	public int getParentSpanId() {
		return parentSpanId;
	}

	public void setParentSpanId(int parentSpanId) {
		this.parentSpanId = parentSpanId;
	}

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public long getEndTime() {
		return endTime;
	}

	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}

	public List<TraceSegmentReference> getRefs() {
		return refs;
	}

	public void setRefs(List<TraceSegmentReference> refs) {
		this.refs = refs;
	}

	public int getOperationNameId() {
		return operationNameId;
	}

	public void setOperationNameId(int operationNameId) {
		this.operationNameId = operationNameId;
	}

	public String getOperationName() {
		return operationName;
	}

	public void setOperationName(String operationName) {
		this.operationName = operationName;
	}

	public int getPeerId() {
		return peerId;
	}

	public void setPeerId(int peerId) {
		this.peerId = peerId;
	}

	public String getPeer() {
		return peer;
	}

	public void setPeer(String peer) {
		this.peer = peer;
	}

	public SpanType getSpanType() {
		return spanType;
	}

	public void setSpanType(SpanType spanType) {
		this.spanType = spanType;
	}

	public SpanLayer getSpanLayer() {
		return spanLayer;
	}

	public void setSpanLayer(SpanLayer spanLayer) {
		this.spanLayer = spanLayer;
	}

	public int getComponentId() {
		return componentId;
	}

	public void setComponentId(int componentId) {
		this.componentId = componentId;
	}

	public String getComponent() {
		return component;
	}

	public void setComponent(String component) {
		this.component = component;
	}

	public boolean isError() {
		return isError;
	}

	public void setError(boolean error) {
		isError = error;
	}

	public List<KeyWithStringValue> getTags() {
		return tags;
	}

	public void setTags(List<KeyWithStringValue> tags) {
		this.tags = tags;
	}

	public List<LogMessage> getLogs() {
		return logs;
	}

	public void setLogs(List<LogMessage> logs) {
		this.logs = logs;
	}

	public KeyWithStringValue getTags(int i){
		return tags.get(i);
	}
	public LogMessage getLogs(int i){
		return logs.get(i);
	}
	public boolean getIsError(){
		return isError;
	}
	@Override
	public String toString() {
		return "SpanObject{" +
				"spanId=" + spanId +
				", parentSpanId=" + parentSpanId +
				", startTime=" + startTime +
				", endTime=" + endTime +
				", refs=" + refs +
				", operationNameId=" + operationNameId +
				", operationName='" + operationName + '\'' +
				", peerId=" + peerId +
				", peer='" + peer + '\'' +
				", spanType=" + spanType +
				", spanLayer=" + spanLayer +
				", spanLayerValue=" + spanLayerValue +
				", componentId=" + componentId +
				", component='" + component + '\'' +
				", isError=" + isError +
				", tags=" + tags +
				", logs=" + logs +
				'}';
	}
}
