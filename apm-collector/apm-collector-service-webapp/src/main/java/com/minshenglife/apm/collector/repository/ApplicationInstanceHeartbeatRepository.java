package com.minshenglife.apm.collector.repository;

import com.minshenglife.apm.collector.entity.ApplicationInstanceHeartbeatEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author meixinbin
 */
public interface ApplicationInstanceHeartbeatRepository extends ElasticsearchRepository<ApplicationInstanceHeartbeatEntity,String>{
}
