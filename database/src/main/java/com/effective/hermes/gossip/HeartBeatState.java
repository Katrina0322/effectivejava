package com.effective.hermes.gossip;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * filename: HeartBeatState
 * Description: heart beat state
 * Author: ubuntu
 * Date: 1/26/18 11:14 AM
 */
public class HeartBeatState {
    private int generation;
    private AtomicInteger heartbeat;
    private int version;

    public HeartBeatState() {
    }

    public HeartBeatState(int generation, int heartbeat) {
        this(generation, heartbeat, 0);
    }

    public HeartBeatState(int generation, int heartbeat, int version) {
        this.generation = generation;
        this.heartbeat = new AtomicInteger(heartbeat);
        this.version = version;
    }

    public int getGeneration() {
        return generation;
    }

    public int getHeartbeat() {
        return heartbeat.get();
    }

    public int getVersion() {
        return version;
    }

    public void updateGeneration(){
        generation++;
        version = VersionGenerator.getNextVersion();
    }

    public void updateHeartBeat(){
        heartbeat.incrementAndGet();
    }
}
