package com.oneapm.pojo;

import java.util.List;

/**
 * filename: Kafka
 * Description:
 * Author: ubuntu
 * Date: 11/13/17 10:38 AM
 */
public class Kafka {
    private int port = 9092;
    private int replication = 2;
    private int partitions = 1;
    private int retentionHours = 2;
    private String logDirs;
    private List<String> zooConnect;
    private String zooDataDir;
    private String zooLogDir;

    public Kafka() {
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getReplication() {
        return replication;
    }

    public void setReplication(int replication) {
        this.replication = replication;
    }

    public int getPartitions() {
        return partitions;
    }

    public void setPartitions(int partitions) {
        this.partitions = partitions;
    }

    public int getRetentionHours() {
        return retentionHours;
    }

    public void setRetentionHours(int retentionHours) {
        this.retentionHours = retentionHours;
    }

    public String getLogDirs() {
        return logDirs;
    }

    public void setLogDirs(String logDirs) {
        this.logDirs = logDirs;
    }

    public List<String> getZooConnect() {
        return zooConnect;
    }

    public void setZooConnect(List<String> zooConnect) {
        this.zooConnect = zooConnect;
    }

    public String getZooDataDir() {
        return zooDataDir;
    }

    public void setZooDataDir(String zooDataDir) {
        this.zooDataDir = zooDataDir;
    }

    public String getZooLogDir() {
        return zooLogDir;
    }

    public void setZooLogDir(String zooLogDir) {
        this.zooLogDir = zooLogDir;
    }
}
