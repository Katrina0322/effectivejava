package com.oneapm.chap21concurrency.chap21_3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * used to
 * Created by tianjin on 8/4/16.
 */
public class CriticalSection {
    static void testApproaches(PairManager pman1, PairManager pman2) {
        ExecutorService exec = Executors.newCachedThreadPool();
        PairManipulator
                pm1 = new PairManipulator(pman1),
                pm2 = new PairManipulator(pman2);
        PairCheck
                pc1 = new PairCheck(pman1),
                pc2 = new PairCheck(pman2);
        exec.execute(pm1);
        exec.execute(pm2);
        exec.execute(pc1);
        exec.execute(pc2);
        try {
            TimeUnit.MILLISECONDS.sleep(50000);
        } catch (InterruptedException e) {
            System.out.println("Sleep interrupted");
        }

        System.out.println("pm1:" + pm1 + "\npm2:" + pm2);
        System.exit(0);
    }

    public static void main(String[] args) {
        PairManager
                pm1 = new PairManager1(),
                pm2 = new PairManager2();
        testApproaches(pm1, pm2);
    }
}
