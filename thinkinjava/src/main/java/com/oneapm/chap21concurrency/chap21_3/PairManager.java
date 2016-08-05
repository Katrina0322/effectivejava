package com.oneapm.chap21concurrency.chap21_3;

/**
 * used to
 * Created by tianjin on 8/4/16.
 */
public class PairManager {
    private static PairManager ourInstance = new PairManager();

    public static PairManager getInstance() {
        return ourInstance;
    }

    private PairManager() {
    }
}
