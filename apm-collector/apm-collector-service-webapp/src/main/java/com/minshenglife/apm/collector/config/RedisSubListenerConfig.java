package com.minshenglife.apm.collector.config;

import com.minshenglife.apm.collector.controller.RedisReceiver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

/**
 * @author meixinbin
 */
@Configuration
public class RedisSubListenerConfig {
//初始化监听器

	@Bean
	RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
											MessageListenerAdapter listenerAdapter) {
		RedisMessageListenerContainer container = new RedisMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.addMessageListener(listenerAdapter, new PatternTopic("pf"));
		return container;
	}
//利用反射来创建监听到消息之后的执行方法

	@Bean
	MessageListenerAdapter listenerAdapter(RedisReceiver redisReceiver) {
		return new MessageListenerAdapter(redisReceiver, "receiveMessage");
	}

}