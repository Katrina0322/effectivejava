package com.oneapm.chap21concurrency.chap21_5;

/**
 * Created by spark on 8/22/16.
 */
public class Car {
    private boolean waxOn = false;

    public synchronized void waxd() {
        waxOn = true;
        notifyAll();
    }

    public synchronized void buffed() {
        waxOn = false;
        notifyAll();
    }

    public synchronized void waitingForWaxd() throws InterruptedException {
        while (waxOn == false) {
            wait();
        }
    }

    public synchronized void waitingForBuffed() throws InterruptedException {
        while (waxOn == true) {
            wait();
        }
    }


}
