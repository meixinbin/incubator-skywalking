package org.apache.skywalking.apm.agent.core.remote.dubbo;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.RegistryConfig;

/**
 * @author meixinbin
 */
public class DubboConfig {

	private ApplicationConfig application;

	public void init(){
		application = new ApplicationConfig();
		application.setName("yyy");

		// Registry Info
		RegistryConfig registry = new RegistryConfig();
		registry.setAddress("10.20.130.230:9090");
		registry.setUsername("aaa");
		registry.setPassword("bbb");
	}



	public ApplicationConfig getApplicationConfig(){
		return application;
	}

}
