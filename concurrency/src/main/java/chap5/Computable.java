package chap5;

/**
 * Created by spark on 11/12/16.
 */
public interface Computable<A,V> {
    V compute(A arg) throws InterruptedException;
}
