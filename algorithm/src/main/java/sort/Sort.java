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
        int child;
        T tmp;

        for(tmp = a[i]; 2*i < n - 1 ; i=child){
            child = 2*i+1;
            if(child != n - 1 && a[child].compareTo(a[child+1]) < 0) child++;
            if(tmp.compareTo(a[child]) < 0){
                a[i] = a[child];
            }else{
                break;
            }
        }
        a[i] = tmp;
    }

    public static <T extends Comparable<? super T>> void heapSort(T[] a){
        for(int i = a.length/2;i>= 0;i--) percDown(a,i,a.length);
        for(int i = a.length - 1;i > 0;i--){
            T tmp = a[0];
            a[0] = a[i];
            a[i] = tmp;
            percDown(a,0,i);
        }
    }

    public static void main(String[] args){
        Integer[] a = {1,2,8,3,4};
        heapSort(a);
        for(int b:a){
            System.out.println(b);
        }
    }

}
