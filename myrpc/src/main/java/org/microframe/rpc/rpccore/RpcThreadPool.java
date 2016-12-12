package org.microframe.rpc.rpccore;

import java.util.concurrent.*;

/**
 * used to
 * Created by tianjin on 12/8/16.
 */
public class RpcThreadPool {
    public static Executor getExecutor(int thread, int queues) {
        String name = "RpcThreadPool";
        return new ThreadPoolExecutor(thread, thread, 0, TimeUnit.MILLISECONDS,
                queues == 0 ? new SynchronousQueue<Runnable>()
                        : (queues < 0 ? new LinkedBlockingQueue<Runnable>()
                        : new LinkedBlockingQueue<Runnable>(queues)),
                new RpcThreadFactory(name, true));
    }
}
