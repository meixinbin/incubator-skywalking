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


package org.apache.skywalking.apm.agent.core.context;

import org.apache.skywalking.apm.util.StringUtil;

import java.util.List;

/**
 * The <code>ContextSnapshot</code> is a snapshot for current context. The snapshot carries the info for building
 * reference between two segments in two thread, but have a causal relationship.
 *
 * @author wusheng
 */
public class ContextSnapshot {
    /**
     * trace segment id of the parent trace segment.
     */
    private String traceSegmentId;

    /**
     * span id of the parent span, in parent trace segment.
     */
    private int spanId = -1;

    private String entryOperationName;

    private String parentOperationName;

    private String primaryDistributedTraceId;

    private String entryApplicationInstanceId;

    ContextSnapshot(String traceSegmentId, int spanId,
        List<String> distributedTraceIds) {
        this.traceSegmentId = traceSegmentId;
        this.spanId = spanId;
        if (distributedTraceIds != null) {
            this.primaryDistributedTraceId = distributedTraceIds.get(0);
        }
    }

    public void setEntryOperationName(String entryOperationName) {
        this.entryOperationName = "#" + entryOperationName;
    }

    public void setEntryOperationId(int entryOperationId) {
        this.entryOperationName = entryOperationId + "";
    }

    public void setParentOperationName(String parentOperationName) {
        this.parentOperationName = "#" + parentOperationName;
    }

    public void setParentOperationId(int parentOperationId) {
        this.parentOperationName = parentOperationId + "";
    }

    public String getDistributedTraceId() {
        return primaryDistributedTraceId;
    }

    public String getTraceSegmentId() {
        return traceSegmentId;
    }

    public int getSpanId() {
        return spanId;
    }

    public String getParentOperationName() {
        return parentOperationName;
    }

    public boolean isValid() {
        return traceSegmentId != null
            && spanId > -1
            && entryApplicationInstanceId != null
            && primaryDistributedTraceId != null
            && !StringUtil.isEmpty(entryOperationName)
            && !StringUtil.isEmpty(parentOperationName);
    }

    public String getEntryOperationName() {
        return entryOperationName;
    }

    public void setEntryApplicationInstanceId(String entryApplicationInstanceId) {
        this.entryApplicationInstanceId = entryApplicationInstanceId;
    }

    public String getEntryApplicationInstanceId() {
        return entryApplicationInstanceId;
    }
    
    public boolean isFromCurrent() {
        return traceSegmentId.equals(ContextManager.capture().getTraceSegmentId());
    }
}
