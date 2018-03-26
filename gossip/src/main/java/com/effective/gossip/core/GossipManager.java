package com.effective.gossip.core;

import com.effective.gossip.listener.GossipListener;
import com.effective.gossip.model.*;

import java.net.UnknownHostException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * filename: GossipManager
 * Description:
 * Author: ubuntu
 * Date: 3/26/18 5:13 PM
 */
public class GossipManager {
    private long executeGossipTime = 500;
    private boolean isWorking = false;
    private ReadWriteLock lock = new ReentrantReadWriteLock();
    private ScheduledExecutorService doGossipExecutor = Executors.newScheduledThreadPool(1);

    private Map<GossipMember, HeartbeatState> endpointMembers = new ConcurrentHashMap<>();
    private List<GossipMember> liveMembers = new ArrayList<>();
    private List<GossipMember> deadMembers = new ArrayList<>();
    private Map<GossipMember, CandidateMemberState> candidateMembers = new ConcurrentHashMap<>();

    private GossipSettings settings;
    private GossipMember localGossipMember;
    private String cluster;
    private GossipListener listener;
    private Random random = new Random();

    private static GossipManager instance = new GossipManager();
    private GossipManager() {
    }

    public static GossipManager getInstance() {
        return instance;
    }

    public void init(String cluster, String ipAddress, int port, String id, List<SeedMember> seedMembers, GossipSettings settings, GossipListener listener){
        this.cluster = cluster;
        this.localGossipMember = new GossipMember(cluster, ipAddress, port, id, GossipState.JOIN);
        this.endpointMembers.put(localGossipMember, new HeartbeatState());
        this.listener = listener;
        this.settings = settings;
        this.settings.setSeedMembers(seedMembers);
    }

    public GossipMember getSelf() {
        return localGossipMember;
    }

    public List<GossipMember> getLiveMembers() {
        return liveMembers;
    }

    public List<GossipMember> getDeadMembers() {
        return deadMembers;
    }

    public GossipSettings getSettings() {
        return settings;
    }

    public String getID() {
        return getSelf().getId();
    }

    public boolean isWorking() {
        return isWorking;
    }

    public Map<GossipMember, HeartbeatState> getEndpointMembers() {
        return endpointMembers;
    }

    public String getCluster() {
        return cluster;
    }

    private void randomGossipDigest(List<GossipDigest> digests) throws UnknownHostException {
        List<GossipMember> endpoints = new ArrayList<>(endpointMembers.keySet());
        Collections.shuffle(endpoints, random);
        for (GossipMember ep : endpoints) {
            HeartbeatState hb = endpointMembers.get(ep);
            long hbTime = 0;
            long hbVersion = 0;
            if (hb != null) {
                hbTime = hb.getHeartbeatTime();
                hbVersion = hb.getVersion();
            }
            digests.add(new GossipDigest(ep, hbTime, hbVersion));
        }
    }

    class GossipTask implements Runnable{

        @Override
        public void run() {

        }
    }

}
