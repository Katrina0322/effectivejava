package com.oneapm.chap21concurrency.chap21_2;

/**
 * used to
 * Created by tianjin on 8/3/16.
 */
public class MainThread {
    public static void main(String[] args) {
        LiftOff launch = new LiftOff();
        launch.run();
        System.out.println("waiting for liftoff!");
    }
}
