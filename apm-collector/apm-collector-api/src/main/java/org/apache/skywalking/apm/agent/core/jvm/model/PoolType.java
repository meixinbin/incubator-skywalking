package org.apache.skywalking.apm.agent.core.jvm.model;

import java.io.Serializable;

/**
 * @author meixinbin
 */
public enum PoolType implements Serializable{
	CODE_CACHE_USAGE(0),
	NEWGEN_USAGE(1),
	OLDGEN_USAGE(2),
	SURVIVOR_USAGE(3),
	PERMGEN_USAGE(4),
	METASPACE_USAGE(5);

	private int code;

	private PoolType(int code){
		this.code = code;
	}

	public int value(){
		return this.code;
	}
}
