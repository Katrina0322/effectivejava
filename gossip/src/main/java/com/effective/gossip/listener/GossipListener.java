package com.effective.gossip.listener;

import com.effective.gossip.model.GossipMember;
import com.effective.gossip.model.GossipState;

/**
 * filename: GossipListener
 * Description:
 * Author: ubuntu
 * Date: 3/26/18 5:05 PM
 */
public interface GossipListener {
    void gossipEvent(GossipMember member, GossipState state);
}
