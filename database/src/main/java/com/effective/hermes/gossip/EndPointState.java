package com.effective.hermes.gossip;

import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

/**
 * filename: EndPointState
 * Description:
 * Author: ubuntu
 * Date: 1/26/18 11:14 AM
 */
public class EndPointState {
    private volatile HeartBeatState heartBeatState;
    private final AtomicReference<Map<ApplicationState, VersionedValue>> applicationState;

    private volatile long updateTimestamp;
    private volatile  boolean isAlive;

    public EndPointState(Map<ApplicationState, VersionedValue> states, HeartBeatState heartBeatState) {
        this.applicationState = new AtomicReference<>(states);
        this.heartBeatState = heartBeatState;
    }

    public HeartBeatState getHeartBeatState() {
        return heartBeatState;
    }

    public long getUpdateTimestamp() {
        return updateTimestamp;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setHeartBeatState(HeartBeatState heartBeatState) {

        this.heartBeatState = heartBeatState;
    }

    public void updateTimestamp() {
        this.updateTimestamp = System.currentTimeMillis();
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }


}
