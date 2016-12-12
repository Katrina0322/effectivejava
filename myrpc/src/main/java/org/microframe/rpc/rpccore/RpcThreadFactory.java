package org.microframe.rpc.rpccore;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * rpm线程工厂
 * Created by tianjin on 12/8/16.
 */
public class RpcThreadFactory implements ThreadFactory{

    private static final AtomicInteger threadNumber  = new AtomicInteger(1);

    private final AtomicInteger mThreadNum = new AtomicInteger(1);

    private final String prefix;

    private final boolean daemoThread;

    private final ThreadGroup threadGroup;

    public RpcThreadFactory() {
        this("rpcserver-threadpool-" + threadNumber.getAndIncrement(), false);
    }

    public RpcThreadFactory(String prefix) {
        this(prefix,false);
    }

    public RpcThreadFactory(String prefix, boolean daemoThread) {
        this.prefix = prefix + "--thread--";
        this.daemoThread = daemoThread;
        SecurityManager securityManager = System.getSecurityManager();
        threadGroup = (securityManager == null) ? Thread.currentThread().getThreadGroup() : securityManager.getThreadGroup();
    }


    public ThreadGroup getThreadGroup() {
        return threadGroup;
    }

    @Override
    public Thread newThread(Runnable r) {
        String name = prefix + mThreadNum.getAndIncrement();
        Thread cur = new Thread(threadGroup,r,name,0);
        cur.setDaemon(daemoThread);
        return cur;
    }
}
