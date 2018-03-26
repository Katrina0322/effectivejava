package com.effective.gossip.core;

import com.effective.gossip.model.SeedMember;

import java.util.ArrayList;
import java.util.List;

/**
 * filename: GossipSettings
 * Description:
 * Author: ubuntu
 * Date: 3/26/18 5:07 PM
 */
public class GossipSettings {
    private int gossipInterval = 1000;
    private int networkDelay = 200;
    private int deleteThreshold = 3;
    private List<SeedMember> seedMembers;

    public int getGossipInterval() {
        return gossipInterval;
    }

    public void setGossipInterval(int gossipInterval) {
        this.gossipInterval = gossipInterval;
    }

    public int getNetworkDelay() {
        return networkDelay;
    }

    public void setNetworkDelay(int networkDelay) {
        this.networkDelay = networkDelay;
    }

    public int getDeleteThreshold() {
        return deleteThreshold;
    }

    public void setDeleteThreshold(int deleteThreshold) {
        this.deleteThreshold = deleteThreshold;
    }

    public List<SeedMember> getSeedMembers() {
        return seedMembers;
    }

    public void setSeedMembers(List<SeedMember> seedMembers) {
        List<SeedMember> _seedMembers = new ArrayList<>();
        if(seedMembers != null && !seedMembers.isEmpty()){
            for(SeedMember seed : seedMembers){
                if(!seed.eigenvalue().equalsIgnoreCase(GossipManager.getInstance().getSelf().eigenValue())){
                    if(!_seedMembers.contains(seed)){
                        _seedMembers.add(seed);
                    }
                }
            }
        }
        this.seedMembers = seedMembers;
    }
}
