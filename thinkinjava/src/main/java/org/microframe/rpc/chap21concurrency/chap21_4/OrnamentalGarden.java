package org.microframe.rpc.chap21concurrency.chap21_4;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * used to
 * Created by tianjin on 8/18/16.
 */
public class OrnamentalGarden {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            exec.execute(new Entrance(i));
        }
        TimeUnit.SECONDS.sleep(3);
        Entrance.cancel();
        exec.shutdown();
        if (!exec.awaitTermination(250, TimeUnit.MILLISECONDS)) {
            System.out.println("Some task were not terminated");
        }
        System.out.println("Total:" + Entrance.getTotalCount());
        System.out.println("SumEntrances:" + Entrance.sumEntrances());
    }
}
