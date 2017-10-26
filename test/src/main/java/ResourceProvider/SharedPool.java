package ResourceProvider;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * filename: SharedPool
 * Description:
 * Author: ubuntu
 * Date: 10/25/17 2:54 PM
 */
public class SharedPool {
    public static final Executor EXECUTOR = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() + 1);
}
