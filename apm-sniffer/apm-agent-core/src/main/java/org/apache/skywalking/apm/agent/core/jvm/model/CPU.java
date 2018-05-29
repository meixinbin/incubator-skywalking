package org.apache.skywalking.apm.agent.core.jvm.model;

/**
 * @author meixinbin
 */
public class CPU {

	private double usagePercent;

	public double getUsagePercent() {
		return usagePercent;
	}

	public void setUsagePercent(double usagePercent) {
		this.usagePercent = usagePercent;
	}

	@Override
	public String toString() {
		return "CPU{" +
				"usagePercent=" + usagePercent +
				'}';
	}
}
