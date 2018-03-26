package com.effective.gossip.model;

/**
 * filename: MessageType
 * Description:
 * Author: ubuntu
 * Date: 3/26/18 4:26 PM
 */
public enum MessageType {
    SYNC_MESSAGE("synaMessage"),
    ACK_MESSAGE("ackMessage"),
    ACK2_MESSAGE("ack2Message"),
    SHUTDOWN("shutdown");

    private final String type;

    MessageType(String type) {
        this.type = type;
    }
}
