package com.oneapm.chap21concurrency.chap21_2;

/**
 * used to
 * Created by tianjin on 8/3/16.
 */
public class MoreBasicThread {
    public static void main(String[] args) {
        for(int i = 0 ; i < 5 ; i++){
            new Thread(new LiftOff()).start();
        }
    }
}
