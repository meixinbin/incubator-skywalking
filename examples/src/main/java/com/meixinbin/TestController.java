package com.meixinbin;

import org.apache.skywalking.apm.toolkit.trace.TraceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author meixinbin
 */
@Controller
@RequestMapping("/t")
public class TestController {

	private Logger logger = LoggerFactory.getLogger(getClass());
	@RequestMapping("/t1")
	@ResponseBody
	public String t1(){
		System.out.println(MDC.get("tid"));
		System.out.println(TraceContext.traceId());
		System.out.println(TraceContext.sessionId());
		return "ok";
	}
}
