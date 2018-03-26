package com.effective.gossip.model;

import java.io.Serializable;
import java.util.List;

/**
 * filename: SyncMessage
 * Description:
 * Author: ubuntu
 * Date: 3/26/18 4:50 PM
 */
public class SyncMessage implements Serializable {
    private static final long serialVersionUID = -3693455429078473729L;
    private String cluster;
    private List<GossipDigest> digestList;

    public SyncMessage(String cluster, List<GossipDigest> digestList) {
        this.cluster = cluster;
        this.digestList = digestList;
    }

    public String getCluster() {
        return cluster;
    }

    public void setCluster(String cluster) {
        this.cluster = cluster;
    }

    public List<GossipDigest> getDigestList() {
        return digestList;
    }

    public void setDigestList(List<GossipDigest> digestList) {
        this.digestList = digestList;
    }

    @Override
    public String toString() {
        return "SyncMessage{" +
                "cluster='" + cluster + '\'' +
                ", digestList=" + digestList +
                '}';
    }
}
