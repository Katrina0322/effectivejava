package com.effective.gossip.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * filename: AckMessage
 * Description:
 * Author: ubuntu
 * Date: 3/26/18 4:55 PM
 */
public class AckMessage implements Serializable {
    private static final long serialVersionUID = -1256199435029465098L;
    private List<GossipDigest> olders;
    private Map<GossipMember, HeartbeatState> newers;
    public AckMessage(List<GossipDigest> olders, Map<GossipMember, HeartbeatState> newers) {
        this.olders = olders;
        this.newers = newers;
    }

    public List<GossipDigest> getOlders() {
        return olders;
    }

    public void setOlders(List<GossipDigest> olders) {
        this.olders = olders;
    }

    public Map<GossipMember, HeartbeatState> getNewers() {
        return newers;
    }

    public void setNewers(Map<GossipMember, HeartbeatState> newers) {
        this.newers = newers;
    }

    @Override
    public String toString() {
        return "AckMessage{" +
                "olders=" + olders +
                ", newers=" + newers +
                '}';
    }
}
