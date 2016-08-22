package com.oneapm.chap21concurrency.chap21_5;

import java.util.concurrent.TimeUnit;

/**
 * Created by spark on 8/22/16.
 */
public class WaxOff implements Runnable {
    private Car car;

    public WaxOff(Car car) {
        this.car = car;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()){
            try {
                car.waitingForWaxd();
                System.out.println("Wax Off");
                TimeUnit.MILLISECONDS.sleep(200);
                car.buffed();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Ending Wax Off task");
    }
}
