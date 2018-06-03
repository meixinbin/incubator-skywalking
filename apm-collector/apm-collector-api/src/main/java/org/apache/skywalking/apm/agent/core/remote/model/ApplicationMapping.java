package org.apache.skywalking.apm.agent.core.remote.model;

import java.io.Serializable;

/**
 * @author meixinbin
 */
public class ApplicationMapping implements Serializable{
	private KeyWithIntegerValue application;

	public KeyWithIntegerValue getApplication() {
		return application;
	}

	public void setApplication(KeyWithIntegerValue application) {
		this.application = application;
	}
}
