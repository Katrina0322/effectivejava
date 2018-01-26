package com.effective.hermes.gossip;

import com.effective.hermes.net.EndPoint;

import java.util.HashMap;
import java.util.Map;

/**
 * filename: GossipDigestAck2Message
 * Description:
 * Author: ubuntu
 * Date: 1/26/18 2:22 PM
 */
public class GossipDigestAck2Message {
    private Map<EndPoint, EndPointState> epStateMap = new HashMap<EndPoint, EndPointState>();

    public GossipDigestAck2Message(Map<EndPoint, EndPointState> epStateMap) {
        this.epStateMap = epStateMap;
    }

    public Map<EndPoint, EndPointState> getEpStateMap() {
        return epStateMap;
    }
}
