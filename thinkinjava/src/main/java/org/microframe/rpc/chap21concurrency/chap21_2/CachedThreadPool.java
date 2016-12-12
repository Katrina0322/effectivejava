package org.microframe.rpc.chap21concurrency.chap21_2;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * used to
 * Created by tianjin on 8/3/16.
 */
public class CachedThreadPool {
    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            exec.execute(new LiftOff());
        }
        exec.shutdown();
    }


}
