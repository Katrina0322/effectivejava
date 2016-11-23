package chap8;

import java.util.Collection;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.*;

/**
 * Created by spark on 11/22/16.
 */
public class SeToParallel {
    public <T> void sequentialRecursive(List<Node<T>> nodes, Collection<T> results){
        for(Node<T> n:nodes){
            results.add(n.compute());
            sequentialRecursive(n.getChildren(),results);
        }
    }

    public <T> void parallelRecursive(final Executor exec,List<Node<T>> nodes,final Collection<T> results){
        for(final Node<T> n : nodes){
            exec.execute(new Runnable() {
                @Override
                public void run() {
                    results.add(n.compute());
                }
            });
            parallelRecursive(exec,n.getChildren(),results);
        }
    }


    public <T> Collection<T> getParallelResults(List<Node<T>> nodes) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        Queue<T> resultQueue = new ConcurrentLinkedQueue<>();
        parallelRecursive(exec,nodes,resultQueue);
        exec.shutdown();
        exec.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
        return resultQueue;
    }


        class Node<T>{

            private T t;
            private List<Node<T>> children;

            public List<Node<T>> getChildren() {
                return children;
            }

            public T compute(){
                return t;
            }
        }
}
