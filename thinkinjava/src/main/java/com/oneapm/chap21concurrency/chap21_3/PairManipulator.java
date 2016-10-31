package com.oneapm.chap21concurrency.chap21_3;

/**
 * used to
 * Created by tianjin on 8/4/16.
 */
public class PairManipulator implements Runnable {
    private PairManager pairManager;

    public PairManipulator(PairManager pairManager) {
        this.pairManager = pairManager;
    }

    @Override
    public void run() {
        while (true) {
            pairManager.increment();
        }
    }

    @Override
    public String toString() {
        return "PairManipulator{" +
                "pairManager=" + pairManager +
                '}';
    }
}
