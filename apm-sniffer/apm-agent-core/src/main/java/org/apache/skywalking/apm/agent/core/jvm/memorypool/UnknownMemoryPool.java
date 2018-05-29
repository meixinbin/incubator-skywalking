/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */


package org.apache.skywalking.apm.agent.core.jvm.memorypool;

import org.apache.skywalking.apm.agent.core.jvm.model.MemoryPool;
import org.apache.skywalking.apm.agent.core.jvm.model.PoolType;

import java.util.LinkedList;
import java.util.List;


/**
 * @author wusheng
 */
public class UnknownMemoryPool implements MemoryPoolMetricAccessor {
    @Override
    public List<MemoryPool> getMemoryPoolMetricList() {
        List<MemoryPool> poolList = new LinkedList<MemoryPool>();
        poolList.add(new MemoryPool(PoolType.CODE_CACHE_USAGE));
        poolList.add(new MemoryPool(PoolType.NEWGEN_USAGE));
        poolList.add(new MemoryPool(PoolType.OLDGEN_USAGE));
        poolList.add(new MemoryPool(PoolType.SURVIVOR_USAGE));
        poolList.add(new MemoryPool(PoolType.PERMGEN_USAGE));
        poolList.add(new MemoryPool(PoolType.METASPACE_USAGE));
        return new LinkedList<MemoryPool>();
    }
}
