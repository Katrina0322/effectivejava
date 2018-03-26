package com.effective.gossip.model;

/**
 * filename: SeedMember
 * Description:
 * Author: ubuntu
 * Date: 3/26/18 4:51 PM
 */
public class SeedMember {
    private String cluster;
    private String ipAddress;
    private Integer port;
    private String id;

    public SeedMember(String cluster, String ipAddress, Integer port, String id) {
        this.cluster = cluster;
        this.ipAddress = ipAddress;
        this.port = port;
        this.id = id;
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

    public String eigenvalue(){
        return getCluster().concat(":").concat(getIpAddress()).concat(":").concat(getPort().toString());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SeedMember that = (SeedMember) o;

        if (cluster != null ? !cluster.equals(that.cluster) : that.cluster != null) return false;
        if (ipAddress != null ? !ipAddress.equals(that.ipAddress) : that.ipAddress != null) return false;
        if (port != null ? !port.equals(that.port) : that.port != null) return false;
        return id != null ? id.equals(that.id) : that.id == null;

    }

    @Override
    public int hashCode() {
        int result = cluster != null ? cluster.hashCode() : 0;
        result = 31 * result + (ipAddress != null ? ipAddress.hashCode() : 0);
        result = 31 * result + (port != null ? port.hashCode() : 0);
        result = 31 * result + (id != null ? id.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SeedMember{" +
                "cluster='" + cluster + '\'' +
                ", ipAddress='" + ipAddress + '\'' +
                ", port=" + port +
                ", id='" + id + '\'' +
                '}';
    }
}
