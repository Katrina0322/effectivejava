package com.effective.gossip.model;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

/**
 * filename: GossipDigest
 * Description:
 * Author: ubuntu
 * Date: 3/26/18 4:28 PM
 */
public class GossipDigest implements Serializable, Comparable<GossipDigest> {
    private static final long serialVersionUID = 551045537130869106L;
    private InetSocketAddress endpoint;
    private long heartbeatTime;
    private long version;
    private String id;

    @Override
    public int compareTo(GossipDigest o) {
        if (heartbeatTime != o.heartbeatTime) {
            return (int) (heartbeatTime - o.heartbeatTime);
        }
        return (int) (version - o.version);
    }

    public GossipDigest(GossipMember member,  long heartbeatTime, long version) throws UnknownHostException {
        this.endpoint = new InetSocketAddress(InetAddress.getByName(member.getIpAddress()),  endpoint.getPort());
        this.heartbeatTime = heartbeatTime;
        this.version = version;
        this.id = member.getId();
    }

    public InetSocketAddress getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(InetSocketAddress endpoint) {
        this.endpoint = endpoint;
    }

    public long getHeartbeatTime() {
        return heartbeatTime;
    }

    public void setHeartbeatTime(long heartbeatTime) {
        this.heartbeatTime = heartbeatTime;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "GossipDigest{" +
                "endpoint=" + endpoint +
                ", heartbeatTime=" + heartbeatTime +
                ", version=" + version +
                ", id='" + id + '\'' +
                '}';
    }
}
