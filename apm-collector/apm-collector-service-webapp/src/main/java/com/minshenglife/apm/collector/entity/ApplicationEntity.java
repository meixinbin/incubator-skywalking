package com.minshenglife.apm.collector.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

/**
 * @author meixinbin
 */
@Document(indexName = "metrics",type = "application")
public class ApplicationEntity {

	@Id
	@Field(store = true,index = true,analyzer = "no")
	private String applicationId;

	public String getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}
}
