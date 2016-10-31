package com.oneapm.chap21concurrency.chap21_5;

import java.util.concurrent.TimeUnit;

/**
 * Created by spark on 8/22/16.
 */
public class WaxOn implements Runnable {
    private Car car;

    public WaxOn(Car car) {
        this.car = car;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            System.out.println("Wax On");
            try {
                TimeUnit.MILLISECONDS.sleep(200);
                car.waxd();
                car.waitingForBuffed();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Ending Wax On task");
    }
}
