package concurrent;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

/**
 * filename: ConcurrentHashMapTest
 * Description:
 * Author: ubuntu
 * Date: 8/30/17 11:06 AM
 */
public class ConcurrentHashMapTest {
    private ConcurrentHashMap<String, String> test = new ConcurrentHashMap<>();
    private Map<String, String> map = new HashMap<>();
    ReentrantLock lock = new ReentrantLock();
    CountDownLatch latch = new CountDownLatch(2);
    Semaphore semaphere = new Semaphore(5);

    public static void main(String[] args) {
        Callable<Integer> integerCallable = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return 5;
            }
        };
        ExecutorService service = Executors.newCachedThreadPool();
        Future<Integer> a = service.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return 5;
            }
        });
        try {
            System.out.println(a.get());
            service.shutdown();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

}
