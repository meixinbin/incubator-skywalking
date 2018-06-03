package org.apache.skywalking.apm.agent.core.jvm.model;

import java.io.Serializable;

/**
 * @author meixinbin
 */
public class Memory implements Serializable{

	private boolean isHeap;

	private long init;

	private long max;

	private long used;

	private long committed;

	public boolean isHeap() {
		return isHeap;
	}

	public void setHeap(boolean heap) {
		isHeap = heap;
	}

	public long getInit() {
		return init;
	}

	public void setInit(long init) {
		this.init = init;
	}

	public long getMax() {
		return max;
	}

	public void setMax(long max) {
		this.max = max;
	}

	public long getUsed() {
		return used;
	}

	public void setUsed(long used) {
		this.used = used;
	}

	public long getCommitted() {
		return committed;
	}

	public void setCommitted(long committed) {
		this.committed = committed;
	}

	@Override
	public String toString() {
		return "Memory{" +
				"isHeap=" + isHeap +
				", init=" + init +
				", max=" + max +
				", used=" + used +
				", committed=" + committed +
				'}';
	}
}
