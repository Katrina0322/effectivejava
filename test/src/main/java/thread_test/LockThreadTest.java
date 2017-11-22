package thread_test;

import ResourceProvider.SharedPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * filename: LockThreadTest
 * Description:
 * Author: ubuntu
 * Date: 11/22/17 5:39 PM
 */
public class LockThreadTest {
    private static Lock lock = new ReentrantLock();
    private static ExecutorService service = SharedPool.EXECUTOR;

    public static void test(){
//        for(int i = 0; i < 100; i++){
//            service.submit(new Runnable() {
//                @Override
//                public void run() {
//                    lock.lock();
//                    while (true) {
//                        System.out.println(Thread.currentThread().getName());
//                    }
//                }
//            });
//        }
//        lock.unlock();
    }

    public static void main(String[] args) {
        LockThreadTest.test();
    }

}
