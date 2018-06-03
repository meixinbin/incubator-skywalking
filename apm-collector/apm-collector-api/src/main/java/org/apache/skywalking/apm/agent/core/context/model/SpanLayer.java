package org.apache.skywalking.apm.agent.core.context.model;

import java.io.Serializable;

/**
 * @author meixinbin
 */
public enum SpanLayer implements Serializable{
	Unknown,Database,RPCFramework,Http,MQ,Cache
}
