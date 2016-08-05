package com.oneapm.chap21concurrency.chap21_2;

import java.util.concurrent.ThreadFactory;

/**
 * used to
 * Created by tianjin on 8/3/16.
 */
public class DaemonThreadFactory implements ThreadFactory {
    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r);
        t.setDaemon(true);
        return t;
    }
}
