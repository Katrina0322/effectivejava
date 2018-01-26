package com.effective.hermes.gossip;

import java.util.ArrayList;
import java.util.List;

/**
 * filename: GossipDigestSynMessage
 * Description:
 * Author: ubuntu
 * Date: 1/26/18 2:35 PM
 */
public class GossipDigestSynMessage {
    private String clusterId;
    private List<GossipDigest> gDigests = new ArrayList<GossipDigest>();

    public GossipDigestSynMessage(String clusterId, List<GossipDigest> gDigests) {
        this.clusterId = clusterId;
        this.gDigests = gDigests;
    }

    public List<GossipDigest> getgDigests() {
        return gDigests;
    }
}
