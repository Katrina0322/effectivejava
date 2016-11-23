package dp;

/**
 * used to
 * Created by tianjin on 11/22/16.
 */
public class Simple01Bag {
    /**
     * 二维数组表示
     *
     * @param c
     * @param w
     * @param v
     * @return
     */
    private static int[][] simple01Bag_1(int[] c, int[] w, int v) {
        if (c.length != w.length) throw new IllegalArgumentException();
        int[][] f = new int[c.length + 1][v + 1];

        //初始化
        for (int col = 0; col <= v; col++) {
            f[0][col] = 0;
        }
        //初始化
        for (int col = 0; col <= c.length; col++) {
            f[col][0] = 0;
        }

        for (int i = 1; i <= c.length; i++) {
            for (int j = 1; j <= v; j++) {
                if (j < c[i - 1]) f[i][j] = f[i - 1][j];    //这里是c[i-1],因为我们的数组起始索引是0
                else f[i][j] = Math.max(f[i - 1][j], f[i - 1][j - c[i - 1]] + w[i - 1]);    //这里是w[i-1],因为我们的数组起始索引是0
            }
        }

        //看看选择的是哪些
        int[] flag = new int[c.length + 1];
        for (int i = c.length; i > 0; i--) {
            if (f[i][v] > f[i - 1][v]) {
                flag[i - 1] = 1;  //这里是flag[i-1],因为我们的数组起始索引是0
                v -= c[i - 1];   //这里是c[i-1],因为我们的数组起始索引是0
            }
        }

        for (int i = 0; i < c.length; i++) {
            System.out.println(flag[i]);
        }
        return f;
    }

    /**
     * 一维数组表示  path[][]计算加入物品
     *
     * @param c
     * @param w
     * @param v
     * @return
     */
    private static int[] simple01Bag_2(int[] c, int[] w, int v) {
        if (c.length != w.length) throw new IllegalArgumentException();
        int[] f = new int[v + 1];
        int[][] path = new int[c.length + 1][v + 1];
        int bound = 0;

        //初始下限
        for (int i = 0; i < c.length; i++) {
            bound += c[i];
        }

        //  f[v] <- f[v-c[i] <- f[v-c[i]-c[i-1]] <- f[v-c[i]-c[i-1] - c[i-2]] <- f[v-sum{c[i..n]
        for (int i = 0; i < c.length; i++) {
            for (int j = v; j >= Math.max(v - bound, c[i]); j--) {
//                f[j] = Math.max(f[j], f[j - c[i]] + w[i]);
                if (f[j] < f[j - c[i]] + w[i]) {
                    f[j] = f[j - c[i]] + w[i];
                    path[i][j] = 1;
                }
            }
            bound -= c[i];
        }


        int i = c.length;
        int j = v;

        //打印出选择的物品
        while (i >= 0 && j >= 0) {
            if (path[i][j] == 1) {
                System.out.println(i + "____" + c[i]);  //  i=0..(c.length-1)
                j -= c[i];
                i--;
            } else {
                i--;
            }
        }
        return f;
    }

    //test
    public static void main(String[] args) {
        int[] c = {2, 2, 6, 5, 4};
        int[] w = {6, 3, 5, 4, 6};


        int[] f = simple01Bag_2(c, w, 10);
        for (int j = 0; j <= 10; j++) {
            System.out.print(f[j] + " - ");
        }

        System.out.println();


        int[][] ss = simple01Bag_1(c, w, 10);
        for (int i = 0; i <= c.length; i++) {
            for (int j = 0; j <= 10; j++) {
                System.out.print(ss[i][j] + " - ");
            }
            System.out.println();
        }
    }
}
