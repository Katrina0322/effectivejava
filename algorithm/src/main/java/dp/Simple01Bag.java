package dp;

/**
 * used to
 * Created by tianjin on 11/22/16.
 */
public class Simple01Bag {

    private static int[][] simple01Bag_1(int[] c, int[] w, int v) {
        if (c.length != w.length) throw new IllegalArgumentException();
        int[][] f = new int[c.length + 1][v + 1];
        for (int col = 0; col <= v; col++) {
            f[0][col] = 0;
        }

        for (int col = 0; col <= c.length; col++) {
            f[col][0] = 0;
        }

        for (int i = 1; i <= c.length; i++) {
            for (int j = 1; j <= v; j++) {
                if (j < c[i - 1])   f[i][j] = f[i - 1][j];
                else f[i][j] = Math.max(f[i - 1][j], f[i - 1][j - c[i - 1]] + w[i - 1]);
            }
        }

        //看看选择的是哪些
        int[] flag = new int[c.length + 1];
        for(int i = c.length;i> 0;i--){
            if(f[i][v] > f[i - 1][v]) {
                flag[i - 1] = 1;
                v -= c[i - 1];
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

        int[][] ss = simple01Bag_1(c, w, 10);
        for (int i = 0; i <= c.length; i++) {
            for (int j = 0; j <= 10; j++) {
                System.out.print(ss[i][j] + " - ");
            }
            System.out.println();
        }
    }
}
