package com.oneapm.chap21concurrency.chap21_3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * used to
 * Created by tianjin on 8/4/16.
 */
public abstract class PairManager {
    AtomicInteger checkCounter = new AtomicInteger(0);
    protected Pair p = new Pair();

    private List<Pair> storage = Collections.synchronizedList(new ArrayList<Pair>());

    public synchronized Pair getP() {
        return new Pair(p.getX(),p.getY());
    }

    protected void store(Pair p){
        storage.add(p);
        try {
            TimeUnit.MILLISECONDS.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public abstract void increment();

    @Override
    public String toString() {
        return "PairManager{" +
                "p=" + p +
                ", checkCounter=" + checkCounter +
                '}';
    }
}