package org.apache.skywalking.apm.agent.core.jvm.model;

import java.io.Serializable;

/**
 * @author meixinbin
 */
public enum PoolType implements Serializable{
	CODE_CACHE_USAGE,NEWGEN_USAGE,OLDGEN_USAGE,SURVIVOR_USAGE,PERMGEN_USAGE,METASPACE_USAGE;
}
