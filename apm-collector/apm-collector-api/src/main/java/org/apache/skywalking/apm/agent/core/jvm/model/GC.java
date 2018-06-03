package org.apache.skywalking.apm.agent.core.jvm.model;

import java.io.Serializable;

/**
 * @author meixinbin
 */
public class GC implements Serializable{
	private GCPhrase phrase;
	private long count;
	private long time;

	public GCPhrase getPhrase() {
		return phrase;
	}

	public void setPhrase(GCPhrase phrase) {
		this.phrase = phrase;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "GC{" +
				"phrase=" + phrase +
				", count=" + count +
				", time=" + time +
				'}';
	}
}
