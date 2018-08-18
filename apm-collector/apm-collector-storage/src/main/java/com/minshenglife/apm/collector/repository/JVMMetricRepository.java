package com.minshenglife.apm.collector.repository;

import com.minshenglife.apm.collector.entity.JVMMetricEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author meixinbin
 */
public interface JVMMetricRepository extends ElasticsearchRepository<JVMMetricEntity,String>{


}
