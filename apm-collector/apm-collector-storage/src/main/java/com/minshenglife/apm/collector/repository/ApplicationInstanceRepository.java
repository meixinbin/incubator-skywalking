package com.minshenglife.apm.collector.repository;

import com.minshenglife.apm.collector.entity.ApplicationInstanceEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author meixinbin
 */
public interface ApplicationInstanceRepository extends ElasticsearchRepository<ApplicationInstanceEntity,String> {
}
