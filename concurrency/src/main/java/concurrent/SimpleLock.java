package concurrent;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * filename: SimpleLock
 * Description:
 * Author: ubuntu
 * Date: 9/7/17 5:31 PM
 */
public class SimpleLock extends AbstractQueuedSynchronizer {
    private static final long serialVersionUID = 2718579736184888938L;

    public SimpleLock() {
    }

    @Override
    protected boolean tryAcquire(int arg) {
        if(compareAndSetState(0, 1)){
            setExclusiveOwnerThread(Thread.currentThread());
            return true;
        }
        return false;
    }

    @Override
    protected boolean tryRelease(int arg) {
        setExclusiveOwnerThread(null);
        setState(0);
        return true;
    }

    public void lock(){
        acquire(1);
    }

    public void unlock(){
        release(1);
    }

    public static void main(String[] args) {
        final SimpleLock lock = new SimpleLock();
        lock.lock();

        for(int i = 0; i < 10; i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    lock.lock();
                    System.out.println(Thread.currentThread().getId() + " acquired the lock!");
                    lock.unlock();
                }
            }).start();

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        lock.unlock();
        System.out.println("main thread unlock!");
    }
}
