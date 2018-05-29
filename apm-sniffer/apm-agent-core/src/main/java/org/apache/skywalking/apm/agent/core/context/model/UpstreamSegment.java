package org.apache.skywalking.apm.agent.core.context.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author meixinbin
 */
public class UpstreamSegment {
	private List<UniqueId> globalTraceIds;
	private TraceSegmentObject segment;

	public UpstreamSegment() {
		this.globalTraceIds = new ArrayList<UniqueId>();
	}

	public List<UniqueId> getGlobalTraceIds() {
		return globalTraceIds;
	}

	public void setGlobalTraceIds(List<UniqueId> globalTraceIds) {
		this.globalTraceIds = globalTraceIds;
	}

	public TraceSegmentObject getSegment() {
		return segment;
	}

	public void setSegment(TraceSegmentObject segment) {
		this.segment = segment;
	}

	public UpstreamSegment addGlobalTraceIds(UniqueId uniqueId){
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
