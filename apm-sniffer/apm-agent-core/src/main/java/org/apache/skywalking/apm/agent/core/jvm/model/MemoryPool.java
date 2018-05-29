package org.apache.skywalking.apm.agent.core.jvm.model;

/**
 * @author meixinbin
 */
public class MemoryPool {
	private PoolType type;
	private long init;
	private long max;
	private long used;
	private long commited;

	public MemoryPool() {
	}

	public MemoryPool(PoolType type) {
		this.type = type;
	}

	public PoolType getType() {
		return type;
	}

	public void setType(PoolType type) {
		this.type = type;
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

	public long getCommited() {
		return commited;
	}

	public void setCommited(long commited) {
		this.commited = commited;
	}

	@Override
	public String toString() {
		return "MemoryPool{" +
				"type=" + type +
				", init=" + init +
				", max=" + max +
				", used=" + used +
				", commited=" + commited +
				'}';
	}
}
