package org.apache.skywalking.apm.agent.core.remote.model;

/**
 * @author meixinbin
 */
public class KeyWithIntegerValue {
	private String key;

	private int value;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
}
