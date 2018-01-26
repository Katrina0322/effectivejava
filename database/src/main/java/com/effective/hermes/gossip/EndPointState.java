package com.effective.hermes.gossip;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * filename: EndPointState
 * Description:
 * Author: ubuntu
 * Date: 1/26/18 11:14 AM
 */
public class EndPointState {
    private HeartBeatState heartBeatState;
    Map<String, ApplicationState> applicationState = new ConcurrentHashMap<>();

    private transient long updateTimestamp;
    private transient boolean isAlive;
    private transient boolean isAGossiper;

    EndPointState(HeartBeatState heartBeatState) {
        this.heartBeatState = heartBeatState;
        updateTimestamp = System.currentTimeMillis();
        isAlive = true;
        isAGossiper = false;
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

    public boolean isAGossiper() {
        return isAGossiper;
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
