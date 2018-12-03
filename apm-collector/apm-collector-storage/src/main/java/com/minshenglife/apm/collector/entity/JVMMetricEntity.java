package com.minshenglife.apm.collector.entity;

import org.apache.skywalking.apm.agent.core.jvm.model.CPU;
import org.apache.skywalking.apm.agent.core.jvm.model.GC;
import org.apache.skywalking.apm.agent.core.jvm.model.Memory;
import org.apache.skywalking.apm.agent.core.jvm.model.MemoryPool;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import java.util.List;

/**
 * @author meixinbin
 */
@Document(indexName = "metrics",type = "jvmMetric")
public class JVMMetricEntity {

	@Id
	@Field(store = true,index = true,analyzer = "no")
	private String id;

	@Field(store = true,index = true,analyzer = "no")
	private String applicationInstanceId;

	private long time;

	private CPU cpu;

	private List<Memory> memory;

	private List<MemoryPool> memoryPool;

	private List<GC> gc;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getApplicationInstanceId() {
		return applicationInstanceId;
	}

	public void setApplicationInstanceId(String applicationInstanceId) {
		this.applicationInstanceId = applicationInstanceId;
	}

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
}
