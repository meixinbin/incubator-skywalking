/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */


package org.apache.skywalking.apm.plugin.tomcat78x;

import java.lang.reflect.Method;
import java.util.UUID;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.skywalking.apm.agent.core.conf.Config;
import org.apache.skywalking.apm.agent.core.context.CarrierItem;
import org.apache.skywalking.apm.agent.core.context.ContextCarrier;
import org.apache.skywalking.apm.agent.core.context.ContextManager;
import org.apache.skywalking.apm.agent.core.context.tag.Tags;
import org.apache.skywalking.apm.agent.core.context.trace.AbstractSpan;
import org.apache.skywalking.apm.agent.core.context.trace.SpanLayer;
import org.apache.skywalking.apm.agent.core.context.trace.TraceSegment;
import org.apache.skywalking.apm.agent.core.plugin.interceptor.enhance.EnhancedInstance;
import org.apache.skywalking.apm.agent.core.plugin.interceptor.enhance.InstanceMethodsAroundInterceptor;
import org.apache.skywalking.apm.agent.core.plugin.interceptor.enhance.MethodInterceptResult;
import org.apache.skywalking.apm.network.trace.component.ComponentsDefine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

/**
 * {@link TomcatInvokeInterceptor} fetch the serialized context data by using {@link
 * HttpServletRequest#getHeader(String)}. The {@link TraceSegment#refs} of current trace segment will reference to the
 * trace segment id of the previous level if the serialized context is not null.
 */
public class TomcatInvokeInterceptor implements InstanceMethodsAroundInterceptor {

	private static final Logger logger = LoggerFactory.getLogger("accessLog");

	/**
	 * * The {@link TraceSegment#refs} of current trace segment will reference to the
	 * trace segment id of the previous level if the serialized context is not null.
	 *
	 * @param objInst
	 * @param method
	 * @param allArguments
	 * @param argumentsTypes
	 * @param result         change this result, if you want to truncate the method.
	 * @throws Throwable
	 */
	@Override
	public void beforeMethod(EnhancedInstance objInst, Method method, Object[] allArguments,
							 Class<?>[] argumentsTypes, MethodInterceptResult result) throws Throwable {
		HttpServletRequest request = (HttpServletRequest) allArguments[0];
		HttpServletResponse response = (HttpServletResponse) allArguments[1];
		ContextCarrier contextCarrier = new ContextCarrier();

		CarrierItem next = contextCarrier.items();
		while (next.hasNext()) {
			next = next.next();
			next.setHeadValue(request.getHeader(next.getHeadKey()));
		}
		String uri = request.getRequestURI();
		AbstractSpan span = ContextManager.createEntrySpan(uri, contextCarrier);
		Tags.URL.set(span, request.getRequestURL().toString());
		Tags.HTTP.METHOD.set(span, request.getMethod());
		span.setComponent(ComponentsDefine.TOMCAT);
		SpanLayer.asHttp(span);
		int suffixIdx = uri.lastIndexOf(".");
		if (suffixIdx == -1 || !Config.Agent.IGNORE_SUFFIX.contains(uri.substring(suffixIdx))) {

			String referer = request.getHeader("referer");
			String userAgent = request.getHeader("User-Agent");
			String clientIp = IPUtils.getIpAddr(request);

			Tags.URL_REFERER.set(span, referer);
			Tags.CLIENT_IP.set(span, clientIp);

			Cookie[] cookies = request.getCookies();
			String sessionId = null;
			if (cookies != null) {
				for (Cookie cookie : cookies) {
					if ("sessionId".equals(cookie.getName())) {
						sessionId = cookie.getValue();
						break;
					}
				}
			}
			if (sessionId == null) {
				sessionId = UUID.randomUUID().toString().replaceAll("-", "");
				Cookie cookie = new Cookie("sessionId", sessionId);
				cookie.setPath("/");
				cookie.setMaxAge(-1);
				cookie.setHttpOnly(false);
				cookie.setDomain(request.getServerName());
				response.addCookie(cookie);
			}

			ContextManager.setSessionId(sessionId);
			MDC.put("sessionId", sessionId);
			logger.info("access info :{} \"{} {}\" {} \"{}\" {} \"{}\"", clientIp, request.getMethod(), request.getRequestURL(), request.getProtocol(), referer == null ? "" : referer, sessionId, userAgent == null ? "" : userAgent);
		}
	}

	@Override
	public Object afterMethod(EnhancedInstance objInst, Method method, Object[] allArguments,
							  Class<?>[] argumentsTypes, Object ret) throws Throwable {
		HttpServletResponse response = (HttpServletResponse) allArguments[1];

		AbstractSpan span = ContextManager.activeSpan();
		if (response.getStatus() >= 400) {
			span.errorOccurred();
			Tags.STATUS_CODE.set(span, Integer.toString(response.getStatus()));
		}
		ContextManager.stopSpan();
		return ret;
	}

	@Override
	public void handleMethodException(EnhancedInstance objInst, Method method, Object[] allArguments,
									  Class<?>[] argumentsTypes, Throwable t) {
		AbstractSpan span = ContextManager.activeSpan();
		span.log(t);
		span.errorOccurred();
	}
}
