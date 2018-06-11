package com.minshenglife.apm.collector.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

/**
 * @author meixinbin
 */
@Document(indexName = "metrics",type = "applicationInstanceHeartbeat")
public class ApplicationInstanceHeartbeatEntity {

	@Id
	@Field(store = true,index = true,analyzer = "no")
	private String id;

	private String applicationInstanceId;

	private long heartbeatTime;

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

	public long getHeartbeatTime() {
		return heartbeatTime;
	}

	public void setHeartbeatTime(long heartbeatTime) {
		this.heartbeatTime = heartbeatTime;
	}
}
