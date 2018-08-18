package com.minshenglife.apm.collector.config;

import com.minshenglife.guid.GuidGenerate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author meixinbin
 */
@Configuration
public class AppConfig {

	@Value("${application.id}")
	private String applicationId;

	@Bean
	GuidGenerate guidGenerate(){
		return new GuidGenerate(applicationId);
	}
}
