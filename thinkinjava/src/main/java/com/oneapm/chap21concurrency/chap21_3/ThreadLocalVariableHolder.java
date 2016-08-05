package com.oneapm.chap21concurrency.chap21_3;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * used to
 * Created by tianjin on 8/4/16.
 */
public class ThreadLocalVariableHolder {
    public static ThreadLocal<Integer> value = new ThreadLocal<Integer>(){
        private Random random = new Random(47);
        protected synchronized Integer initialValue(){
            return random.nextInt(10000);
        }
    };

    public static void increment(){
        value.set(value.get() + 1);
    }

    public static int get() {
        return value.get();
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        for(int i = 0 ; i < 5 ; i++){
            exec.execute(new Accessor(i));
        }
        TimeUnit.MILLISECONDS.sleep(300);
        exec.shutdown();
    }
}
