package binaryheap;

/**
 * used to
 * Created by tianjin on 6/28/16.
 */
public class BinaryHeap<T extends Comparable<? super T>> {

    private static final int DEFAULT_CAPACITY = 10;

    private int currentSize;

    private T[] array;


    public BinaryHeap() {

    }

    public BinaryHeap(int currentSize) {
        this.currentSize = currentSize;
    }

    public BinaryHeap(T[] array) {
        this.array = array;
    }


    public void insert(T x){
        if(currentSize == array.length - 1) enlargeArray(array.length * 2 + 1);
        int hole = ++currentSize;
        for(;hole > 1 && x.compareTo(array[hole/2]) < 0;hole /= 2){
            array[hole] = array[hole / 2];
        }
        array[hole] = x;
    }

    public T findMin(){

        return null;
    }

    public T deleteMin(){
        if(isEmpty()) throw new IllegalArgumentException();
        T minItem = findMin();
        array[1] = array[currentSize--];
        percolateDown(1);
        return minItem;
    }

    public boolean isEmpty(){
        return currentSize == 0;
    }

    public void makeEmpty(){

    }


    private void percolateDown(int hole){

    }

    private void buildHeap(){

    }

    private void enlargeArray(int newSize){

    }
}
