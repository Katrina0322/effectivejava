package sort;

/**
 * used to
 * Created by tianjin on 11/17/16.
 */
public class MaxPQ<T extends Comparable<T>> {
    private T[] pq;
    private int N = 0;
    
    public MaxPQ() {
        super();
    }

    public MaxPQ(int max) {
        pq = (T[]) new Comparable[max];
    }

    public MaxPQ(T[] a) {
        
    }

    public void insert(T a){
        pq[++N] = a;
        swim(N);
    }

    private void sink(int k){
        T tmp;
        while( 2 * k <= N ){
            int j = 2*k;
            if(j < N && pq[j].compareTo(pq[j + 1]) < 0) j++;
            if(pq[k].compareTo(pq[j]) >= 0) break;
            tmp = pq[k];
            pq[k ] = pq[j];
            pq[j] = tmp;
            k = j;
        }
    }

    private void swim(int k){
        T tmp;
        while(k > 1 && pq[k/2].compareTo(pq[k]) < 0){
            tmp = pq[k/2];
            pq[k/2] = pq[k];
            pq[k] = tmp;
            k /= 2;
        }
    }

    public T max(){
        return pq[1];
    }

    public T delMax(){
        T max = max();
        pq[1] = pq[N--];
        pq[N + 1] = null;
        sink(1);
        return max;
    }



    public boolean isEmpty(){
        return N == 0;
    }

    public int size(){
        return N;
    }
}
