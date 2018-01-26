package com.effective.hermes.gossip;

/**
 * filename: ApplicationState
 * Description:
 * Author: ubuntu
 * Date: 1/26/18 11:10 AM
 */
public class ApplicationState {
    private int version;
    private String state;

    public ApplicationState(int version, String state) {
        this.version = version;
        this.state = state;
    }

    public ApplicationState(String state) {
        this.state = state;
        this.version = VersionGenerator.getNextVersion();
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
