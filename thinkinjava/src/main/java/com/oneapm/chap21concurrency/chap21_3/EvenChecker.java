package com.oneapm.chap21concurrency.chap21_3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * used to
 * Created by tianjin on 8/4/16.
 */
public class EvenChecker implements Runnable {
    private final int id;
    private IntGenerator intGenerator;

    public EvenChecker(int id, IntGenerator intGenerator) {
        this.id = id;
        this.intGenerator = intGenerator;
    }

    public static void test(IntGenerator intGenerator, int count) {
        System.out.println("Press Control-C to exit");
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < count; i++) {
            exec.execute(new EvenChecker(count, intGenerator));
        }
        exec.shutdown();
    }

    public static void test(IntGenerator intGenerator) {
        test(intGenerator, 10);
    }

    @Override
    public void run() {
        while (!intGenerator.isCanceled()) {
            int val = intGenerator.next();
            if (val % 2 != 0) {
                System.out.println(val + " not even!");
                intGenerator.cancel();
            }
        }
    }
}
