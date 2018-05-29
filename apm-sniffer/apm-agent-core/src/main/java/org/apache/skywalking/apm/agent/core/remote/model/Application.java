package org.apache.skywalking.apm.agent.core.remote.model;

/**
 * @author meixinbin
 */
public class Application {

	private String applicationCode;

	public String getApplicationCode() {
		return applicationCode;
	}

	public void setApplicationCode(String applicationCode) {
		this.applicationCode = applicationCode;
	}

	@Override
	public String toString() {
		return "Application{" +
				"applicationCode='" + applicationCode + '\'' +
				'}';
	}
}
