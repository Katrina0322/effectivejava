package org.microframe.rpc.chap21concurrency.chap21_3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * used to
 * Created by tianjin on 8/4/16.
 */
public class SerialNumberChecker {
    private static final int SIZE = 10;
    private static CircularSet serials = new CircularSet(1000);
    private static ExecutorService exec = Executors.newCachedThreadPool();

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < SIZE; i++) {
            exec.execute(new SerialChecker());
        }
        if (args.length > 0) {
            TimeUnit.SECONDS.sleep(new Integer(args[0]));
            System.out.println("No Duplicate Detected");
            System.exit(0);
        }
    }

    static class SerialChecker implements Runnable {
        @Override
        public void run() {
            while (true) {
                int serial = SerialNumberGenerator.nextSerialNumber();
                if (serials.contains(serial)) {
                    System.out.println("Duplicate:" + serial);
                    System.exit(0);
                }
                serials.add(serial);
            }
        }
    }
}
