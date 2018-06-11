package org.apache.skywalking.apm.agent.core.context.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author meixinbin
 */
public class UpstreamSegment implements Serializable{
	private List<String> globalTraceIds;
	private TraceSegmentObject segment;

	public UpstreamSegment() {
		this.globalTraceIds = new ArrayList<String>();
	}

	public List<String> getGlobalTraceIds() {
		return globalTraceIds;
	}

	public void setGlobalTraceIds(List<String> globalTraceIds) {
		this.globalTraceIds = globalTraceIds;
	}

	public TraceSegmentObject getSegment() {
		return segment;
	}

	public void setSegment(TraceSegmentObject segment) {
		this.segment = segment;
	}

	public UpstreamSegment addGlobalTraceIds(String uniqueId){
		this.globalTraceIds.add(uniqueId);
		return this;
	}

	@Override
	public String toString() {
		return "UpstreamSegment{" +
				"globalTraceIds=" + globalTraceIds +
				", segment=" + segment +
				'}';
	}
}
