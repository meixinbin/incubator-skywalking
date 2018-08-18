package com.minshenglife.apm.collector.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

/**
 * @author meixinbin
 */
@Document(indexName = "metrics",type = "applicationInstance")
public class ApplicationInstanceEntity {

	@Id
	@Field(store = true,index = true,analyzer = "no")
	private String id;

	private String applicationId;

	private String agentUUID;

	private long registerTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	public String getAgentUUID() {
		return agentUUID;
	}

	public void setAgentUUID(String agentUUID) {
		this.agentUUID = agentUUID;
	}

	public long getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(long registerTime) {
		this.registerTime = registerTime;
	}
}
