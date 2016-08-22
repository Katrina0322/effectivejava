package com.oneapm.chap21concurrency.chap21_5;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by spark on 8/22/16.
 */
public class WaxOMatic {
    public static void main(String[] args) throws InterruptedException {
        Car car = new Car();
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new WaxOff(car));
        exec.execute(new WaxOn(car));
        TimeUnit.SECONDS.sleep(2);
        exec.shutdownNow();
    }
}
