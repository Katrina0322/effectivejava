package chap8;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by spark on 11/21/16.
 */
public class ThreadNumber {
    public static void main(String[] args) {
        int N_CPUS = Runtime.getRuntime().availableProcessors();
        ExecutorService executor = Executors.newCachedThreadPool();
        Executors.newFixedThreadPool(100);
        Executors.newSingleThreadExecutor();
        System.out.println(N_CPUS);
    }
}
