package org.microframe.rpc.chap21concurrency.chap21_4;

import java.util.Random;

/**
 * used to
 * Created by tianjin on 8/4/16.
 */
public class Count {
    private int count = 0;
    private Random random = new Random(47);

    public static void main(String[] args) {
        Count count = new Count();
        System.out.println(count.increment());
        System.out.println(count.value());
    }

    public synchronized int increment() {
        int tmp = count;
        if (random.nextBoolean()) Thread.yield();
        return count = ++tmp;
    }

    public synchronized int value() {
        return count;
    }
}
