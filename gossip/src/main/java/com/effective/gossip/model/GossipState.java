package com.effective.gossip.model;

/**
 * filename: GossipState
 * Description:
 * Author: ubuntu
 * Date: 3/26/18 4:31 PM
 */
public enum GossipState {
    UP("up"), DOWN("down"), JOIN("join");

    private final String state;

    GossipState(String state) {
        this.state = state;
    }
}
