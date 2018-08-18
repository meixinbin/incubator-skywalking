package com.minshenglife.apm.collector.service;

import com.minshenglife.apm.collector.entity.ApplicationEntity;
import com.minshenglife.apm.collector.entity.ApplicationInstanceEntity;
import com.minshenglife.apm.collector.entity.ApplicationInstanceHeartbeatEntity;
import com.minshenglife.apm.collector.entity.JVMMetricEntity;
import com.minshenglife.apm.collector.repository.ApplicationInstanceHeartbeatRepository;
import com.minshenglife.apm.collector.repository.ApplicationInstanceRepository;
import com.minshenglife.apm.collector.repository.ApplicationRepository;
import com.minshenglife.apm.collector.repository.JVMMetricRepository;
import com.minshenglife.guid.GuidGenerate;
import org.apache.skywalking.apm.agent.client.api.CollectorService;
import org.apache.skywalking.apm.agent.core.context.model.UpstreamSegment;
import org.apache.skywalking.apm.agent.core.disk.model.DiskMetrics;
import org.apache.skywalking.apm.agent.core.jvm.model.JVMMetric;
import org.apache.skywalking.apm.agent.core.jvm.model.JVMMetrics;
import org.apache.skywalking.apm.agent.core.remote.model.Application;
import org.apache.skywalking.apm.agent.core.remote.model.ApplicationInstance;
import org.apache.skywalking.apm.agent.core.remote.model.ApplicationInstanceHeartbeat;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author meixinbin
 */
@Service
public class CollectorServiceImpl implements CollectorService {

	@Autowired
	private ApplicationRepository applicationRepository;

	@Autowired
	private ApplicationInstanceRepository applicationInstanceRepository;

	@Autowired
	private ApplicationInstanceHeartbeatRepository applicationInstanceHeartbeatRepository;

	@Autowired
	private JVMMetricRepository jvmMetricRepository;

	@Autowired
	private GuidGenerate guidGenerate;

	@Override
	public void register(Application application) {
		ApplicationEntity applicationEntity = new ApplicationEntity();
		BeanUtils.copyProperties(application,applicationEntity);
		applicationRepository.save(applicationEntity);
	}

	@Override
	public String registerInstance(ApplicationInstance applicationInstance) {
		ApplicationInstanceEntity applicationInstanceEntity = new ApplicationInstanceEntity();
		BeanUtils.copyProperties(applicationInstance,applicationInstanceEntity);
		applicationInstanceEntity.setId(guidGenerate.next());
		ApplicationInstanceEntity instanceEntity = applicationInstanceRepository.save(applicationInstanceEntity);
		return instanceEntity.getId();
	}

	@Override
	public void heartbeat(ApplicationInstanceHeartbeat applicationInstanceHeartbeat) {
		ApplicationInstanceHeartbeatEntity applicationInstanceHeartbeatEntity = new ApplicationInstanceHeartbeatEntity();

		BeanUtils.copyProperties(applicationInstanceHeartbeat,applicationInstanceHeartbeatEntity);
		applicationInstanceHeartbeatEntity.setId(guidGenerate.next());
		applicationInstanceHeartbeatRepository.save(applicationInstanceHeartbeatEntity);
	}

	@Override
	public void collectorTraceInfo(UpstreamSegment upstreamSegment) {


	}

	@Override
	public void jvmMetrics(JVMMetrics jvmMetrics) {
		List<JVMMetric> metrics = jvmMetrics.getMetrics();
		for(JVMMetric jvmMetric:metrics){
			JVMMetricEntity jvmMetricEntity = new JVMMetricEntity();
			BeanUtils.copyProperties(jvmMetric,jvmMetricEntity);
			jvmMetricEntity.setApplicationInstanceId(jvmMetrics.getApplicationInstanceId());
			jvmMetricEntity.setId(guidGenerate.next());
			jvmMetricRepository.save(jvmMetricEntity);
		}

	}

	@Override
	public void diskMetrics(DiskMetrics diskMetrics) {

		System.out.println(diskMetrics);
	}

}
