package org.apache.skywalking.apm.agent.core.jvm.model;

import java.util.List;

/**
 * @author meixinbin
 */
public class JVMMetric {

	private long time;

	private CPU cpu;

	private List<Memory> memory;

	private List<MemoryPool> memoryPool;

	private List<GC> gc;

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public CPU getCpu() {
		return cpu;
	}

	public void setCpu(CPU cpu) {
		this.cpu = cpu;
	}

	public List<Memory> getMemory() {
		return memory;
	}

	public void setMemory(List<Memory> memory) {
		this.memory = memory;
	}

	public List<MemoryPool> getMemoryPool() {
		return memoryPool;
	}

	public void setMemoryPool(List<MemoryPool> memoryPool) {
		this.memoryPool = memoryPool;
	}

	public List<GC> getGc() {
		return gc;
	}

	public void setGc(List<GC> gc) {
		this.gc = gc;
	}

	@Override
	public String toString() {
		return "JVMMetric{" +
				"time=" + time +
				", cpu=" + cpu +
				", memory=" + memory +
				", memoryPool=" + memoryPool +
				", gc=" + gc +
				'}';
	}
}
