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
        final Node node = this.node.get();
        node.locked = true;
        Node prev = this.tail.getAndSet(node);
        this.prev.set(prev);
        while (prev.locked){

        }
    }

    public void unlock(){
        final Node node = this.node.get();
        node.locked = false;

    }

    private static class Node {
        private volatile boolean locked;
    }

    public static void main(String[] args) {
        CLHLock lock = new CLHLock();

    }
}
