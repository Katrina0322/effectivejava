package com.effective.gossip.model;

import java.io.Serializable;

/**
 * filename: GossipMember
 * Description:
 * Author: ubuntu
 * Date: 3/26/18 4:30 PM
 */
public class GossipMember implements Serializable {
    private static final long serialVersionUID = -4494415870226089997L;
    private String cluster;
    private String ipAddress;
    private Integer port;
    private String id;
    private GossipState state;

    public GossipMember(String cluster, String ipAddress, Integer port, String id, GossipState state) {
        this.cluster = cluster;
        this.ipAddress = ipAddress;
        this.port = port;
        this.id = id;
        this.state = state;
    }

    public String getCluster() {
        return cluster;
    }

    public void setCluster(String cluster) {
        this.cluster = cluster;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public GossipState getState() {
        return state;
    }

    public void setState(GossipState state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GossipMember that = (GossipMember) o;

        if (cluster != null ? !cluster.equals(that.cluster) : that.cluster != null) return false;
        if (ipAddress != null ? !ipAddress.equals(that.ipAddress) : that.ipAddress != null) return false;
        if (port != null ? !port.equals(that.port) : that.port != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        return state == that.state;

    }

    @Override
    public int hashCode() {
        int result = cluster != null ? cluster.hashCode() : 0;
        result = 31 * result + (ipAddress != null ? ipAddress.hashCode() : 0);
        result = 31 * result + (port != null ? port.hashCode() : 0);
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        return result;
    }

    public String ipAndPort() {
        return ipAddress.concat(":").concat(String.valueOf(port));
    }

    public String eigenValue(){
        return getCluster().concat(":").concat(getIpAddress()).concat(":").concat(getPort().toString());
    }
}
