package org.apache.skywalking.apm.agent.core.context.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author meixinbin
 */
public class LogMessage implements Serializable{
	private long time;

	private List<KeyWithStringValue> data = new ArrayList<KeyWithStringValue>();

	public void addData(KeyWithStringValue log){
		data.add(log);
	}
	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public List<KeyWithStringValue> getData() {
		return data;
	}

	public void setData(List<KeyWithStringValue> data) {
		this.data = data;
	}

	public List<KeyWithStringValue> getDataList(){
		return data;
	}
	@Override
	public String toString() {
		return "LogMessage{" +
				"time=" + time +
				", data=" + data +
				'}';
	}
}
