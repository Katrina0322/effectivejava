package com.effective.hermes.gossip;

import com.effective.hermes.net.EndPoint;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * filename: GossipDigestAckMessage
 * Description:
 * Author: ubuntu
 * Date: 1/26/18 2:19 PM
 */
public class GossipDigestAckMessage {
    private List<GossipDigest> gDigestList = new ArrayList<GossipDigest>();
    private Map<EndPoint, EndPointState> epStateMap = new HashMap<EndPoint, EndPointState>();

    public GossipDigestAckMessage(List<GossipDigest> gDigestList, Map<EndPoint, EndPointState> epStateMap) {
        this.gDigestList = gDigestList;
        this.epStateMap = epStateMap;
    }
    void addGossipDigest(EndPoint ep, int generation, int version)
    {
        gDigestList.add( new GossipDigest(ep, generation, version) );
    }

    public List<GossipDigest> getgDigestList() {
        return gDigestList;
    }

    public Map<EndPoint, EndPointState> getEpStateMap() {
        return epStateMap;
    }
}
