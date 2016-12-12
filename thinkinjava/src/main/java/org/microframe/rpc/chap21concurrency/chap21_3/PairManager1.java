package org.microframe.rpc.chap21concurrency.chap21_3;

/**
 * used to
 * Created by tianjin on 8/4/16.
 */
public class PairManager1 extends PairManager {
    @Override
    public synchronized void increment() {
        p.incrementX();
        p.incrementY();
        store(getP());
    }
}
