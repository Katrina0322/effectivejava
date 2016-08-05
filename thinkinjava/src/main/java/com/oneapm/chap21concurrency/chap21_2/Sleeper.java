package com.oneapm.chap21concurrency.chap21_2;

/**
 * used to
 * Created by tianjin on 8/3/16.
 */
public class Sleeper extends Thread {
    private int duration;

    public Sleeper(String name, int duration) {
        super(name);
        this.duration = duration;
        start();
    }


    public void run() {
        try {
            sleep(duration);
        } catch (InterruptedException e) {
            System.out.println(getName() + "was interrupted. " + isInterrupted());
            return;
        }
        System.out.println(getName() + "has awakened");
    }
}
