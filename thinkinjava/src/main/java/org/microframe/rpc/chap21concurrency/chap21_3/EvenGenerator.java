package org.microframe.rpc.chap21concurrency.chap21_3;

/**
 * used to
 * Created by tianjin on 8/4/16.
 */
public class EvenGenerator extends IntGenerator {
    private int currentEvenValue = 0;

    public static void main(String[] args) {
        EvenChecker.test(new EvenGenerator());
    }

    @Override
    public synchronized int next() {
        ++currentEvenValue;
        Thread.yield();
        ++currentEvenValue;
        return currentEvenValue;
    }
}
