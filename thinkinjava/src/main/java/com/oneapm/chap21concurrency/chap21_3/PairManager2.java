package com.oneapm.chap21concurrency.chap21_3;

/**
 * used to
 * Created by tianjin on 8/4/16.
 */
public class PairManager2 extends PairManager {
    @Override
    public void increment() {
        Pair tmp;
        synchronized (this) {
            p.incrementX();
            p.incrementY();
            tmp = getP();
        }
        store(tmp);
    }
}
