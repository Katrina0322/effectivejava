package org.microframe.rpc.chap21concurrency.chap21_4;



import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * used to
 * Created by tianjin on 8/4/16.
 */
public class Entrance implements Runnable {
    private static Count count = new Count();
    private static List<Entrance> entrances = new ArrayList<Entrance>();
    private static volatile boolean canceled = false;
    private final int id;
    private int number = 0;

    public Entrance(int id) {
        this.id = id;
        entrances.add(this);
    }

    public static void cancel() {
        canceled = true;
    }

    public static int getTotalCount() {
        return count.value();
    }

    public static int sumEntrances() {
        int sum = 0;
        for (Entrance entrance : entrances) {
            sum += entrance.getValue();
        }
        return sum;
    }

    public synchronized int getValue() {
        return number;
    }


    public void run() {
        while (!canceled) {
            synchronized (this) {
                ++number;
            }
            System.out.println(this + " total:" + count.increment());
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                System.out.println("sleep interrupted");
            }
        }
        System.out.println("Stopping " + this);
    }
}
