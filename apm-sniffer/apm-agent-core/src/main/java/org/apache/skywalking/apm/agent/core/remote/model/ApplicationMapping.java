package org.apache.skywalking.apm.agent.core.remote.model;

/**
 * @author meixinbin
 */
public class ApplicationMapping {
	private KeyWithIntegerValue application;

	public KeyWithIntegerValue getApplication() {
		return application;
	}

	public void setApplication(KeyWithIntegerValue application) {
		this.application = application;
	}
}
