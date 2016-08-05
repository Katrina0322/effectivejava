package com.oneapm.chap21concurrency.chap21_3;


/**
 * used to
 * Created by tianjin on 8/4/16.
 */
public abstract class IntGenerator {
    private volatile boolean canceled = false;
    public abstract int next();
    public void cancel(){
        canceled = true;
    }

    public boolean isCanceled(){
        return canceled;
    }
}
