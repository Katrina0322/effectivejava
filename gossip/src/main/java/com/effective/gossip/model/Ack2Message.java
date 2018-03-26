package com.effective.gossip.model;

import java.io.Serializable;
import java.util.Map;

/**
 * filename: Ack2Message
 * Description:
 * Author: ubuntu
 * Date: 3/26/18 4:57 PM
 */
public class Ack2Message implements Serializable {
    private static final long serialVersionUID = 2181951099823863621L;
    private Map<GossipMember, HeartbeatState> endpoints;

    public Ack2Message(Map<GossipMember, HeartbeatState> endpoints) {
        this.endpoints = endpoints;
    }

    public Map<GossipMember, HeartbeatState> getEndpoints() {
        return endpoints;
    }

    public void setEndpoints(Map<GossipMember, HeartbeatState> endpoints) {
        this.endpoints = endpoints;
    }

    @Override
    public String toString() {
        return "Ack2Message{" +
                "endpoints=" + endpoints +
                '}';
    }
}
