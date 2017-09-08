package concurrent;

import java.util.concurrent.atomic.AtomicReference;

/**
 * filename: MCSLock
 * Description:
 * Author: ubuntu
 * Date: 9/8/17 11:05 AM
 */
public class MCSLock {
    private AtomicReference<Node> tail;
    private final ThreadLocal<Node> node;

    public MCSLock() {
        tail = new AtomicReference<>(null);
        node = new ThreadLocal<Node>(){
            @Override
            protected Node initialValue() {
                return new Node();
            }
        };
    }

    public void lock(){
        Node qnode = node.get();
        qnode.locked = true;
        Node prev = tail.getAndSet(qnode);
        if(prev != null){
            prev.next = qnode;
            while (qnode.locked){

            }
        }
    }

    public void unlock(){
        Node qnode = node.get();
        System.out.println(Thread.currentThread().getId() + "" + qnode.locked);
        if(qnode.next == null){
            if(tail.compareAndSet(qnode, null)){
                return;
            }
            while (qnode.next == null){

            }
        }
        qnode.next.locked = false;
        qnode.next = null;
    }


    private static class Node {
        private volatile boolean locked = false;
        private Node next = null;
    }

    public static void main(String[] args) throws InterruptedException {
        final MCSLock lock = new MCSLock();
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
