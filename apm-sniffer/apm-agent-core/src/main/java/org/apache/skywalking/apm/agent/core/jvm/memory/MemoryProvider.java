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


package org.apache.skywalking.apm.agent.core.jvm.memory;

import org.apache.skywalking.apm.agent.core.jvm.model.Memory;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.util.LinkedList;
import java.util.List;

/**
 * @author wusheng
 */
public enum MemoryProvider {
    INSTANCE;
    private final MemoryMXBean memoryMXBean;

    MemoryProvider() {
        this.memoryMXBean = ManagementFactory.getMemoryMXBean();
    }

    public List<Memory> getMemoryMetricList() {
        List<Memory> memoryList = new LinkedList<Memory>();

        MemoryUsage heapMemoryUsage = memoryMXBean.getHeapMemoryUsage();
        Memory heapMemoryBuilder = new Memory();
        heapMemoryBuilder.setHeap(true);
        heapMemoryBuilder.setInit(heapMemoryUsage.getInit());
        heapMemoryBuilder.setUsed(heapMemoryUsage.getUsed());
        heapMemoryBuilder.setCommitted(heapMemoryUsage.getCommitted());
        heapMemoryBuilder.setMax(heapMemoryUsage.getMax());
        memoryList.add(heapMemoryBuilder);

        MemoryUsage nonHeapMemoryUsage = memoryMXBean.getNonHeapMemoryUsage();
        Memory nonHeapMemoryBuilder = new Memory();
        nonHeapMemoryBuilder.setHeap(false);
        nonHeapMemoryBuilder.setInit(nonHeapMemoryUsage.getInit());
        nonHeapMemoryBuilder.setUsed(nonHeapMemoryUsage.getUsed());
        nonHeapMemoryBuilder.setCommitted(nonHeapMemoryUsage.getCommitted());
        nonHeapMemoryBuilder.setMax(nonHeapMemoryUsage.getMax());
        memoryList.add(nonHeapMemoryBuilder);

        return memoryList;
    }

}
