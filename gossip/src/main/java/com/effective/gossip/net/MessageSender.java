package com.effective.gossip.net;

import com.effective.gossip.model.GossipMember;

import java.nio.Buffer;

/**
 * filename: MessageSender
 * Description:
 * Author: ubuntu
 * Date: 3/26/18 5:48 PM
 */
public interface MessageSender {
    void sendMsg(GossipMember member, Buffer data);
}
