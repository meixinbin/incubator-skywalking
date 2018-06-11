package com.minshenglife.apm.collector.repository;

import com.minshenglife.apm.collector.entity.ApplicationEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author meixinbin
 */
public interface ApplicationRepository extends ElasticsearchRepository<ApplicationEntity,String>{
}
