package sort;

/**
 * used to
 * Created by tianjin on 6/29/16.
 */
public class Sort {

    /**
     * 检查参数
     *
     * @param a
     * @param <T>
     * @return
     */
    private static <T extends Comparable<? super T>> boolean isEmpty(T[] a) {
        return a == null || a.length == 0;
    }

    /**
     * 选择排序
     *
     * @param a
     * @param <T>
     */
    public static <T extends Comparable<? super T>> void selectSort(T[] a) {
        if (isEmpty(a)) throw new IllegalArgumentException();
        int n = a.length;
        for (int i = 0; i < n; i++) {
            T tmp = a[i];
            int min = i;
            for (int j = i + 1; j < n; j++) {
                if (a[j].compareTo(a[min]) < 0) min = j;
            }
            a[i] = a[min];
            a[min] = tmp;
        }
    }

    /**
     * 插入排序
     *
     * @param a
     * @param <T>
     */
    public static <T extends Comparable<? super T>> void insertionSort(T[] a) {
        if (isEmpty(a)) throw new IllegalArgumentException();
        int j;
        for (int i = 1; i < a.length; i++) {
            T tmp = a[i];
            for (j = i; j > 0 && tmp.compareTo(a[j - 1]) < 0; j--) {
                a[j] = a[j - 1];
            }
            a[j] = tmp;
        }
    }


    /**
     * 希尔排序
     *
     * @param a
     * @param <T>
     */
    public static <T extends Comparable<? super T>> void shellSort(T[] a) {
        if (isEmpty(a)) throw new IllegalArgumentException();
        int j;
        for (int gap = a.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < a.length; i++) {
                T tmp = a[i];
                for (j = i; j >= gap && tmp.compareTo(a[j - gap]) < 0; j -= gap) {
                    a[j] = a[j - gap];
                }
                a[j] = tmp;
            }
        }
    }


    /**
     * 归并排序
     *
     * @param a
     * @param <T>
     */
    public static <T extends Comparable<? super T>> void mergeSort(T[] a) {
        if (isEmpty(a)) throw new IllegalArgumentException();
        T[] tmp = (T[]) new Comparable[a.length];
        mergeSort(a, tmp, 0, a.length - 1);
    }

    /**
     * 自底向上的归并
     *
     * @param a
     * @param <T>
     */
    public static <T extends Comparable<? super T>> void mergeSort2(T[] a) {
        if (isEmpty(a)) throw new IllegalArgumentException();
        int N = a.length;
        T[] tmp = (T[]) new Comparable[N];
        for (int sz = 1; sz < N; sz += sz) {
            for (int lo = 0; lo < N - sz; lo += sz + sz) {
                merge(a, tmp, lo, lo + sz - 1, Math.min(lo + sz + sz - 1, N - 1));
            }
        }
    }

    private static <T extends Comparable<? super T>> void mergeSort(T[] a, T[] tmp, int left, int right) {
        if (left < right) {
            int center = (left + right) / 2;
            mergeSort(a, tmp, left, center);
            mergeSort(a, tmp, center + 1, right);
            merge(a, tmp, left, center, right);
        }
    }

    private static <T extends Comparable<? super T>> void merge(T[] a, T[] tmp, int lo, int mid, int hi) {
        int i = lo;
        int j = mid + 1;

        for (int k = lo; k <= hi; k++) tmp[k] = a[k];

        for (int k = lo; k <= hi; k++) {
            if (i > mid) a[k] = tmp[j++];
            else if (j > hi) a[k] = tmp[i++];
            else if (tmp[j].compareTo(tmp[i]) < 0) a[k] = tmp[j++];
            else a[k] = tmp[i++];
        }
    }


    /**
     * 堆排序
     *
     * @param a
     * @param <T>
     */
    public static <T extends Comparable<? super T>> void heapSort(T[] a) {
        if (isEmpty(a)) throw new IllegalArgumentException();
        for (int i = a.length / 2; i >= 0; i--) percDown(a, i, a.length);
        for (int i = a.length - 1; i > 0; i--) {
            T tmp = a[0];
            a[0] = a[i];
            a[i] = tmp;
            percDown(a, 0, i);
        }
    }

    private static <T extends Comparable<? super T>> void percDown(T[] a, int i, int n) {
        int child;
        T tmp;
        for (tmp = a[i]; 2 * i < n - 1; i = child) {
            child = 2 * i + 1;
            if (child != n - 1 && a[child].compareTo(a[child + 1]) < 0) child++;
            if (tmp.compareTo(a[child]) < 0) {
                a[i] = a[child];
            } else {
                break;
            }
        }
        a[i] = tmp;
    }


    /**
     * 快速排序
     *
     * @param a
     * @param <T>
     */
    public static <T extends Comparable<? super T>> void quickSort(T[] a) {
        if (isEmpty(a)) throw new IllegalArgumentException();
        quickSort(a, 0, a.length - 1);
    }

    private static <T extends Comparable<? super T>> void quickSort(T[] a, int lo, int hi) {
        if (lo < hi) {
            int index = partition(a, lo, hi);
            quickSort(a, lo, index - 1);
            quickSort(a, index + 1, hi);
        }
    }

    private static <T extends Comparable<? super T>> int partition(T[] a, int lo, int hi) {
        T tmp = a[lo];
        while (lo < hi) {
            while (lo < hi && a[hi].compareTo(tmp) >= 0) hi--;
            a[lo] = a[hi];
            while (lo < hi && a[lo].compareTo(tmp) <= 0) lo++;
            a[hi] = a[lo];
        }
        a[lo] = tmp;
        return lo;
    }


    public static void main(String[] args) {
        Integer[] a = {1, 2, 8, 5, 10, 18, 29, 3, 4, 199, 153, 960, 255, 68, 785, 634, 2, 28, 99, 66, 43};

        long startTime1 = System.nanoTime();
        mergeSort(a);
        long endTime1 = System.nanoTime();
        System.out.println(endTime1 - startTime1);

        long startTime2 = System.nanoTime();
        quickSort(a);
        long endTime2 = System.nanoTime();
        System.out.println(endTime2 - startTime2);

        for (int b : a) {
            System.out.println(b);
        }
    }
}
