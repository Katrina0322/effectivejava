package com.oneapm.dao;

/**
 * filename: Hadoop
 * Description:
 * Author: ubuntu
 * Date: 11/13/17 10:31 AM
 */
public class Hadoop {
    private String defaultFS;
    private String tmpDir;
    private String replication;
    private int type;  // 1 namenode  0 datanode  -1 none

    public Hadoop() {
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDefaultFS() {
        return defaultFS;
    }

    public void setDefaultFS(String defaultFS) {
        this.defaultFS = defaultFS;
    }

    public String getTmpDir() {
        return tmpDir;
    }

    public void setTmpDir(String tmpDir) {
        this.tmpDir = tmpDir;
    }

    public String getReplication() {
        return replication;
    }

    public void setReplication(String replication) {
        this.replication = replication;
    }


}
