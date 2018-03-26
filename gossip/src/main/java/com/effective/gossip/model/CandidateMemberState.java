package com.effective.gossip.model;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * filename: CandidateMemberState
 * Description:
 * Author: ubuntu
 * Date: 3/26/18 4:58 PM
 */
public class CandidateMemberState {
    private long heartbeatTime;
    private AtomicInteger downingCount;

    public CandidateMemberState(long heartbeatTime, AtomicInteger downingCount) {
        this.heartbeatTime = heartbeatTime;
        this.downingCount = downingCount;
    }

    public void updateCount() {
        this.downingCount.incrementAndGet();
    }

    public long getHeartbeatTime() {
        return heartbeatTime;
    }

    public void setHeartbeatTime(long heartbeatTime) {
        this.heartbeatTime = heartbeatTime;
    }

    public AtomicInteger getDowningCount() {
        return downingCount;
    }

    public void setDowningCount(AtomicInteger downingCount) {
        this.downingCount = downingCount;
    }

    @Override
    public String toString() {
        return "CandidateMemberState{" +
                "heartbeatTime=" + heartbeatTime +
                ", downingCount=" + downingCount +
                '}';
    }
}
