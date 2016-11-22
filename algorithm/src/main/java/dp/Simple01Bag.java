package dp;

/**
 * used to
 * Created by tianjin on 11/22/16.
 */
public class Simple01Bag {

    private static int[][] simple01Bag_1(int[] c, int[] w, int v) {
        if (c.length != w.length) throw new IllegalArgumentException();
        int[][] f = new int[c.length + 1][v + 1];
        for (int i = 0; i < c.length; i++) {
            for (int j = 0; j <= v; j++) {
//                if(j == 0) f[i][j] = 0;
//                else  if (j < c[i])  f[i][j] = f[i - 1][j];
//                else  f[i][j] = Math.max(f[i - 1][j], f[i - 1][j - c[i]] + w[i]);
                if( i == 0 ){
                    if(j >= c[0])    f[i][j] = w[i];
                }else{
                    if (j < c[i])  f[i][j] = f[i - 1][j];
                    else  f[i][j] = Math.max(f[i - 1][j], f[i - 1][j - c[i]] + w[i]);
                }
            }
        }
        int[] flag = new int[c.length];
        for(int i = 0;i < c.length;i++){
            if(f[i][v] > f[i+1 ][v]) {
                flag[i] = 1;
                v -= c[i];
            }
        }

        for (int i = 0; i < c.length; i++){
            System.out.println(flag[i]);
        }
        return f;
    }

    private static int[] simple01Bag(int[] c, int[] w, int v) {
        if (c.length != w.length) throw new IllegalArgumentException();
        int[] f = new int[v + 1];
        int bound = 0;
        for (int i = 0; i < c.length; i++) {
            bound += c[i];
        }

        for (int i = 0; i < c.length; i++) {
            for (int j = v; j >= Math.max(v - bound, c[i]); j--) {
                f[j] = Math.max(f[j], f[j - c[i]] + w[i]);
            }
            bound -= c[i];
        }
        return f;
    }

    public static void main(String[] args) {
        int[] c = {2, 2, 6, 5, 4};
        int[] w = {6, 3, 5, 4, 6};
//        int[] f = simple01Bag(c,w,10);
//        for(int a:f){
//            System.out.println(a);
//        }

        int[][] ss = simple01Bag_1(c, w, 10);
        for (int i = 0; i < c.length; i++) {
            for (int j = 0; j <= 10; j++) {
                System.out.print(ss[i][j] + " - ");
            }
            System.out.println();
        }

//        int[] s = simple01Bag_1(c,w,10);
//        for (int i = 0; i < c.length; i++){
//            System.out.println(s[i]);
//        }
    }
}
