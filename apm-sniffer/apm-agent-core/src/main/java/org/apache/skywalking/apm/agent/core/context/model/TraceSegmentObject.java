package org.apache.skywalking.apm.agent.core.context.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author meixinbin
 */
public class TraceSegmentObject {
	private UniqueId traceSegmentId;
	private List<SpanObject> spans = new ArrayList<SpanObject>();
	private int applicationId;
	private int applicationInstanceId;
	private boolean isSizeLimited;

	public void addSpans(SpanObject span){
		spans.add(span);
	}
	public UniqueId getTraceSegmentId() {
		return traceSegmentId;
	}

	public void setTraceSegmentId(UniqueId traceSegmentId) {
		this.traceSegmentId = traceSegmentId;
	}

	public List<SpanObject> getSpans() {
		return spans;
	}

	public void setSpans(List<SpanObject> spans) {
		this.spans = spans;
	}

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

	public boolean isSizeLimited() {
		return isSizeLimited;
	}

	public void setSizeLimited(boolean sizeLimited) {
		isSizeLimited = sizeLimited;
	}

	@Override
	public String toString() {
		return "TraceSegmentObject{" +
				"traceSegmentId=" + traceSegmentId +
				", spans=" + spans +
				", applicationId=" + applicationId +
				", applicationInstanceId=" + applicationInstanceId +
				", isSizeLimited=" + isSizeLimited +
				'}';
	}
}
