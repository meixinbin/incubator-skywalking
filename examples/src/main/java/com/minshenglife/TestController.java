package com.minshenglife;

import org.apache.skywalking.apm.toolkit.trace.TraceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author meixinbin
 */
@Controller
@RequestMapping("/t")
public class TestController {

	private Logger logger = LoggerFactory.getLogger(getClass());
	@RequestMapping("/t1/{id}")
	@ResponseBody
	public String t1(@PathVariable String id){
		logger.info("====================");
		System.out.println(TraceContext.traceId());
		System.out.println(TraceContext.sessionId());
		return "ok";
	}
}
