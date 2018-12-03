package org.apache.skywalking.apm.agent.core.context.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author meixinbin
 */
public class LogMessage implements Serializable{
	private long time;

	private Map<String,String> data = new HashMap<>();

	public void addData(String k,String v){
		data.put(k,v);
	}
	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public Map<String, String> getData() {
		return data;
	}

	public void setData(Map<String, String> data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "LogMessage{" +
				"time=" + time +
				", data=" + data +
				'}';
	}
}
