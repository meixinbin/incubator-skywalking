package org.apache.skywalking.apm.agent.core.remote.dubbo;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import org.apache.skywalking.apm.agent.core.boot.BootService;
import org.apache.skywalking.apm.agent.core.conf.Config;
import org.apache.skywalking.apm.service.ApplicationService;
import org.apache.skywalking.apm.util.StringUtil;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author meixinbin
 */
public class DubboConfig implements BootService {

	private static ApplicationConfig application;

	private static RegistryConfig registry;

	private static ConcurrentHashMap<String,Object> serviceCache = new ConcurrentHashMap<String, Object>();

	private void init() {
		application = new ApplicationConfig();
		application.setName(Config.Agent.APPLICATION_ID);

		// Registry Info
		registry = new RegistryConfig();
		registry.setId("zookeeperRegistry");
		registry.setAddress(Config.Collector.DUBBO_REGISTRY_ADDRESS);
//		registry.setUsername("aaa");
//		registry.setPassword("bbb");
	}

	public RegistryConfig getRegistry(){
		return registry;
	}

	public ApplicationConfig getApplicationConfig(){
		return application;
	}

	public <T> T getSerivce(Class<T> tClass,String version){
		version = StringUtil.isEmpty(version)? "1.0":version;
		String serviceName = tClass.getCanonicalName()+":"+version;
		T service = (T) serviceCache.get(serviceName);
		if(service!=null){
			return service;
		}
		// Refer remote service
		ReferenceConfig<T> reference = new ReferenceConfig<T>();
		reference.setApplication(application);
		reference.setRegistry(registry);
		reference.setInterface(tClass);
		reference.setVersion(version);
		reference.setProtocol("dubbo");
		service = reference.get();
		serviceCache.put(serviceName,service);
		return service;
	}

	@Override
	public void prepare() throws Throwable {

	}

	@Override
	public void boot() throws Throwable {
		init();
		getSerivce(ApplicationService.class,"1.0");
	}

	@Override
	public void onComplete() throws Throwable {

	}

	@Override
	public void shutdown() throws Throwable {

	}
//
//	public static void main(String[] args) {
//		getApplicationSerivce().register(new Application());
//		System.out.println("========");
//	}
}
