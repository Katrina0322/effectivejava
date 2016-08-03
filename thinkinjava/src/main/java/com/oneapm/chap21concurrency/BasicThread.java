package com.oneapm.chap21concurrency;

/**
 * used to
 * Created by tianjin on 8/3/16.
 */
public class BasicThread {
    public static void main(String[] args) {
        Thread thread = new Thread(new LiftOff());
        thread.start();
        System.out.println("waiting for liftoff!");
    }
}
