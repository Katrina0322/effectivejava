package sort;

/**
 * used to
 * Created by tianjin on 6/29/16.
 */
public class Sort {

    /**
     * 插入排序
     *
     * @param <T>
     */
    public static <T extends Comparable<? super T>> void insertionSort(T[] a) {
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
     * @param a
     * @param <T>
     */
    public static <T extends Comparable<? super T>> void shellSort(T[] a){
        int j;
        for(int gap = a.length / 2;gap > 0;gap/=2){
            for(int i = gap;i< a.length;i++){
                 T tmp = a[i];
                for(j = i;j>= gap && tmp.compareTo(a[j-gap])<0;j-=gap){
                    a[j] = a[j-gap];
                }

                a[j] = tmp;
            }
        }
    }


    private static <T extends Comparable<? super T>> void percDown(T[] a,int i,int n){

    }



}
