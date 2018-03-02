package com.effective.hermes.gossip;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
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

    public EndPointState(HeartBeatState heartBeatState) {
        this.heartBeatState = heartBeatState;
        updateTimestamp = System.currentTimeMillis();
        isAlive = true;
    }

    public EndPointState(<Map<String, ApplicationState> states, HeartBeatState heartBeatState) {
        this.applicationState = applicationState;
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

    public void setAGossiper(boolean AGossiper) {
        isAGossiper = AGossiper;
    }

    public ApplicationState getApplicationState(String key)
    {
        return applicationState.get(key);
    }

    public Map<String, ApplicationState> getApplicationState() {
        return applicationState;
    }

    public void addApplicationState(String key, ApplicationState appState)
    {
        applicationState.put(key, appState);
    }


}
