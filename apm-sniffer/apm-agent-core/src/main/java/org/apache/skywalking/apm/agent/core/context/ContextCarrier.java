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

import org.apache.skywalking.apm.agent.core.context.trace.TraceSegment;
import org.apache.skywalking.apm.util.StringUtil;

import java.io.Serializable;
import java.util.List;

/**
 * {@link ContextCarrier} is a data carrier of {@link TracingContext}.
 * It holds the snapshot (current state) of {@link TracingContext}.
 * <p>
 * Created by wusheng on 2017/2/17.
 */
public class ContextCarrier implements Serializable {
    /**
     * {@link TraceSegment#traceSegmentId}
     */
    private String traceSegmentId;

    /**
     * id of parent span.
     * It is unique in parent trace segment.
     */
    private int spanId = -1;

    /**
     * id of parent application instance, it's the id assigned by collector.
     */
    private String parentApplicationInstanceId;

    /**
     * id of first application instance in this distributed trace, it's the id assigned by collector.
     */
    private String entryApplicationInstanceId;

    /**
     * peer(ipv4/ipv6/hostname + port) of the server, from client side.
     */
    private String peerHost;

    /**
     * Operation/Service name of the first one in this distributed trace.
     * This name may be compressed to an integer.
     */
    private String entryOperationName;

    /**
     * Operation/Service name of the parent one in this distributed trace.
     * This name may be compressed to an integer.
     */
    private String parentOperationName;

    /**
     *  also known as TraceId
     */
    private String primaryDistributedTraceId;

    public CarrierItem items() {
        SW3CarrierItem carrierItem = new SW3CarrierItem(this, null);
        CarrierItemHead head = new CarrierItemHead(carrierItem);
        return head;
    }

    /**
     * Serialize this {@link ContextCarrier} to a {@link String},
     * with '|' split.
     *
     * @return the serialization string.
     */
    String serialize() {
        if (this.isValid()) {
            return StringUtil.join('|',
                this.getTraceSegmentId(),
                this.getSpanId() + "",
                this.getParentApplicationInstanceId() + "",
                this.getEntryApplicationInstanceId() + "",
                this.getPeerHost(),
                this.getEntryOperationName(),
                this.getParentOperationName(),
                this.getPrimaryDistributedTraceId()+"");
        } else {
            return "";
        }
    }

    /**
     * Initialize fields with the given text.
     *
     * @param text carries {@link #traceSegmentId} and {@link #spanId}, with '|' split.
     */
    ContextCarrier deserialize(String text) {
        if (text != null) {
            String[] parts = text.split("\\|", 8);
            if (parts.length == 8) {
                try {
                    this.traceSegmentId = parts[0];
                    this.spanId = Integer.parseInt(parts[1]);
                    this.parentApplicationInstanceId = parts[2];
                    this.entryApplicationInstanceId = parts[3];
                    this.peerHost = parts[4];
                    this.entryOperationName = parts[5];
                    this.parentOperationName = parts[6];
                    this.primaryDistributedTraceId = parts[7];
                } catch (NumberFormatException e) {

                }
            }
        }
        return this;
    }

    /**
     * Make sure this {@link ContextCarrier} has been initialized.
     *
     * @return true for unbroken {@link ContextCarrier} or no-initialized. Otherwise, false;
     */
    public boolean isValid() {
        return traceSegmentId != null
            && getSpanId() > -1
            && parentApplicationInstanceId != null
            && entryApplicationInstanceId != null
            && !StringUtil.isEmpty(peerHost)
            && !StringUtil.isEmpty(entryOperationName)
            && !StringUtil.isEmpty(parentOperationName)
            && primaryDistributedTraceId != null;
    }

    public String getEntryOperationName() {
        return entryOperationName;
    }

    void setEntryOperationName(String entryOperationName) {
        this.entryOperationName = '#' + entryOperationName;
    }

    void setParentOperationName(String parentOperationName) {
        this.parentOperationName = '#' + parentOperationName;
    }

    public String getTraceSegmentId() {
        return traceSegmentId;
    }

    public int getSpanId() {
        return spanId;
    }

    void setTraceSegmentId(String traceSegmentId) {
        this.traceSegmentId = traceSegmentId;
    }

    void setSpanId(int spanId) {
        this.spanId = spanId;
    }

    public String getParentApplicationInstanceId() {
        return parentApplicationInstanceId;
    }

    void setParentApplicationInstanceId(String parentApplicationInstanceId) {
        this.parentApplicationInstanceId = parentApplicationInstanceId;
    }

    public String getPeerHost() {
        return peerHost;
    }

    void setPeerHost(String peerHost) {
        this.peerHost = '#' + peerHost;
    }

    void setPeerId(int peerId) {
        this.peerHost = peerId + "";
    }

    public String getDistributedTraceId() {
        return primaryDistributedTraceId;
    }

    public void setDistributedTraceIds(List<String> distributedTraceIds) {
        this.primaryDistributedTraceId = distributedTraceIds.get(0);
    }

    private String getPrimaryDistributedTraceId() {
        return primaryDistributedTraceId;
    }

    public String getParentOperationName() {
        return parentOperationName;
    }

    public String getEntryApplicationInstanceId() {
        return entryApplicationInstanceId;
    }

    public void setEntryApplicationInstanceId(String entryApplicationInstanceId) {
        this.entryApplicationInstanceId = entryApplicationInstanceId;
    }

}
