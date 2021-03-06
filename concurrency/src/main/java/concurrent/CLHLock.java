package concurrent;

import java.util.concurrent.atomic.AtomicReference;

/**
 * filename: CLHLock
 * Description:
 * Author: ubuntu
 * Date: 9/5/17 4:46 PM
 */
public class CLHLock{

    private final ThreadLocal<Node> prev;
    private final ThreadLocal<Node> node;
    private final AtomicReference<Node> tail = new AtomicReference<>(new Node());

    public CLHLock() {
        this.prev = new ThreadLocal<Node>(){
            @Override
            protected Node initialValue() {
                return null;
            }
        };
        this.node = new ThreadLocal<Node>(){
            @Override
            protected Node initialValue() {
                return new Node();
            }
        };
    }

    public void lock(){
        final Node qnode = this.node.get();
        qnode.locked = true;
        Node prev = this.tail.getAndSet(qnode);
        this.prev.set(prev);
        while (prev.locked){

        }
    }

    public void unlock(){
        final Node qnode = this.node.get();
        qnode.locked = false;
        this.node.set(this.prev.get());
    }

    private static class Node {
        private volatile boolean locked;
    }

    public static void main(String[] args) throws InterruptedException {
        final CLHLock lock = new CLHLock();
        lock.lock();

        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    lock.lock();
                    System.out.println(Thread.currentThread().getId() + " acquired the lock!");
                    lock.unlock();
                }
            }).start();
            Thread.sleep(100);
        }

        System.out.println("main thread unlock!");
        lock.unlock();
    }
}
