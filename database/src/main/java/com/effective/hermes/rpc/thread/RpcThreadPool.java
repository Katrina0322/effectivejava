package com.effective.hermes.rpc.thread;

import java.util.concurrent.*;

/**
 * filename: RpcThreadPool
 * Description:
 * Author: ubuntu
 * Date: 2/27/18 10:20 AM
 */
public class RpcThreadPool {
    public static Executor getExecutor(int threads, int queues) {
        String name = "RpcThreadPool";
        return new ThreadPoolExecutor(threads, threads, 0, TimeUnit.MILLISECONDS,
                queues == 0 ? new SynchronousQueue<Runnable>()
                        : (queues < 0 ? new LinkedBlockingQueue<Runnable>()
                        : new LinkedBlockingQueue<Runnable>(queues)),
                new NamedThreadFactory(name, true), new AbortPolicyWithReport(name));
    }
}
