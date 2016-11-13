package chap5;

import java.util.concurrent.*;

/**
 * Created by spark on 11/12/16.
 */
public class Momoizerl<A,V> implements Computable<A,V> {
    private final ConcurrentMap<A,Future<V>> cache = new ConcurrentHashMap<>();
    private final Computable<A,V> c;

    public Momoizerl(Computable<A, V> c) {
        this.c = c;
    }

    @Override
    public V compute(final A arg) throws InterruptedException {
        while (true){
            Future<V> f = cache.get(arg);
            if(f == null){
                Callable<V> eval = new Callable<V>() {
                    @Override
                    public V call() throws Exception {
                        return c.compute(arg);
                    }
                };
                FutureTask<V> ft = new FutureTask<V>(eval);
                f = cache.putIfAbsent(arg,ft);
                if(f == null){
                    f = ft;
                    ft.run();
                }
            }
            try {
                return f.get();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
}
