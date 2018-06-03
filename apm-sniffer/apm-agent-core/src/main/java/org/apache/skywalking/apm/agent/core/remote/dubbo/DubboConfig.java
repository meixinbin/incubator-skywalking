package org.apache.skywalking.apm.agent.core.remote.dubbo;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import org.apache.skywalking.apm.agent.core.remote.model.Application;
import org.apache.skywalking.apm.service.ApplicationService;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author meixinbin
 */
public class DubboConfig {

	private static ApplicationConfig application;

	private static RegistryConfig registry;

	private static ConcurrentHashMap<String,Object> serviceCache = new ConcurrentHashMap<String, Object>();

	static {
		application = new ApplicationConfig();
		application.setName("yyy");

		// Registry Info
		registry = new RegistryConfig();
		registry.setId("zookeeperRegistry");
		registry.setAddress("zookeeper://127.0.0.1:2181");
//		registry.setUsername("aaa");
//		registry.setPassword("bbb");
	}

	public static RegistryConfig getRegistry(){
		return registry;
	}


	public static ApplicationConfig getApplicationConfig(){
		return application;
	}


	public static ApplicationService getApplicationSerivce(){
		ApplicationService applicationService = (ApplicationService) serviceCache.get(ApplicationService.class.getCanonicalName());
		if(applicationService!=null){
			return applicationService;
		}
		// Refer remote service
		ReferenceConfig<ApplicationService> reference = new ReferenceConfig<ApplicationService>();
		reference.setApplication(application);
		reference.setRegistry(registry);
		reference.setInterface(ApplicationService.class);
		reference.setVersion("1.0");
		reference.setProtocol("dubbo");
		applicationService = reference.get();
		serviceCache.put(ApplicationService.class.getCanonicalName(),applicationService);
		return applicationService;
	}
//
//	public static void main(String[] args) {
//		getApplicationSerivce().register(new Application());
//		System.out.println("========");
//	}
}
