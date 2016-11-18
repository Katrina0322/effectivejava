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
        this.currentSize = DEFAULT_CAPACITY;
    }

    public BinaryHeap(int currentSize) {
        this.currentSize = currentSize;
    }

    public BinaryHeap(T[] array) {
        currentSize = array.length;
        array = (T[]) new Comparable[(currentSize + 2) * 11 / 10];
        int i = 1;
        for (T item : array) {
            array[i++] = item;
        }

        buildHeap();
    }


    public void insert(T x) {
        if (currentSize == array.length - 1) enlargeArray(array.length * 2 + 1);
        int hole = ++currentSize;
        for (; hole > 1 && x.compareTo(array[hole / 2]) < 0; hole /= 2) {
            array[hole] = array[hole / 2];
        }
        array[hole] = x;
    }

    public T findMin() {

        return null;
    }

    public T deleteMin() {
        if (isEmpty()) throw new IllegalArgumentException();
        T minItem = findMin();
        array[1] = array[currentSize--];
        percolateDown(1);
        return minItem;
    }

    public boolean isEmpty() {
        return currentSize == 0;
    }

    public void makeEmpty() {

    }


    private void percolateDown(int hole) {
        int child;
        T tmp = array[hole];
        for (; hole * 2 <= currentSize; hole = child) {
            child = hole * 2;
            if (child != currentSize && array[child = 1].compareTo(array[child]) < 0) child++;
            if (array[child].compareTo(tmp) < 0) {
                array[hole] = array[child];
            } else {
                break;
            }
        }
    }

    private void buildHeap() {
        for (int i = currentSize / 2; i > 0; i--) {
            percolateDown(i);
        }
    }

    private void enlargeArray(int newSize) {

    }
}
