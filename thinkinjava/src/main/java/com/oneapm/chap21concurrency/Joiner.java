package com.oneapm.chap21concurrency;

/**
 * used to
 * Created by tianjin on 8/3/16.
 */
public class Joiner extends Thread {
    private Sleeper sleeper;

    public Joiner(String name, Sleeper sleeper) {
        super(name);
        this.sleeper = sleeper;
        start();
    }

    @Override
    public void run() {
        try {
            sleeper.join();
        } catch (InterruptedException e) {
            System.out.println("interrupted. " + isInterrupted());
        }

        System.out.println(getName() + "join completed");
    }
}
