package chap5;

import java.util.concurrent.CountDownLatch;

/**
 * Created by spark on 11/12/16.
 */
public class TestHarness {

    public static long timeTasks(int nThreads,final Runnable task) throws InterruptedException {
        final CountDownLatch startGate = new CountDownLatch(1);
        final CountDownLatch endGate = new CountDownLatch(nThreads);

        for(int i = 0;i < nThreads;i++){
//            Runnable t = new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        startGate.await();
//                        try {
//                            task.run();
//                        } finally {
//                            endGate.countDown();
//                        }
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            };
            Thread t = new Thread(){
                @Override
                public void run() {
                    try {
                        startGate.await();
                        try {
                            task.run();
                        } finally {
                            endGate.countDown();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            t.start();
        }

        long start = System.nanoTime();

        startGate.countDown();

        endGate.await();

        long end = System.nanoTime();


        return end - start;


    }


    public static void main(String[] args) throws InterruptedException {
        Runnable a = new Runnable() {
            @Override
            public void run() {
                int sum = 0;
                for(int i = 0; i < 1000000; i++){
                    sum += i;
                }
                System.out.println(sum);
            }
        };

       System.out.println( timeTasks(100,a) );
    }


}
