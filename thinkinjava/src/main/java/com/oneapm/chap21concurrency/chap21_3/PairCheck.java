package com.oneapm.chap21concurrency.chap21_3;

/**
 * used to
 * Created by tianjin on 8/4/16.
 */
public class PairCheck implements Runnable {
    private PairManager pairManager;

    public PairCheck(PairManager pairManager) {
        this.pairManager = pairManager;
    }

    @Override
    public void run() {
        while (true) {
            pairManager.checkCounter.incrementAndGet();
            pairManager.getP().checkState();
        }
    }
}
