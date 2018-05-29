package org.apache.skywalking.apm.agent.core.context.model;

/**
 * @author meixinbin
 */
public class KeyWithStringValue {
	private String key;
	private String value;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "KeyWithStringValue{" +
				"key='" + key + '\'' +
				", value='" + value + '\'' +
				'}';
	}
}
