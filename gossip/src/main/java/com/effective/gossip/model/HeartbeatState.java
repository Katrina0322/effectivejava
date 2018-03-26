package com.effective.gossip.model;


import com.effective.gossip.util.VersionHelper;

import java.io.Serializable;

/**
 * filename: HeartbeatState
 * Description:
 * Author: ubuntu
 * Date: 3/26/18 4:39 PM
 */
public class HeartbeatState implements Serializable {
    private static final long serialVersionUID = 8396042522599237019L;
    private long heartbeatTime;
    private long version;
    public HeartbeatState() {
        this.heartbeatTime = System.currentTimeMillis();
        this.version = VersionHelper.getInstance().nextVersion();
    }

    public long updateVersion() {
        setHeartbeatTime(System.currentTimeMillis());
        this.version = VersionHelper.getInstance().nextVersion();
        return version;
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
}
