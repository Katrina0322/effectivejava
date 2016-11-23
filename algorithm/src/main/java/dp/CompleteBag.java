package dp;


import java.util.ArrayList;

/**
 * used to
 * Created by tianjin on 11/23/16.
 */
public class CompleteBag {
    public static int[] completeBag(int[] c, int[] w, int v) {
        if (c.length != w.length) throw new IllegalArgumentException();
        int[][] path = new int[c.length + 1][v + 1];   //保存添加的物品
        int[] f = new int[v + 1];
        for (int i = 0; i < c.length; i++) {
            for (int j = c[i]; j <= v; j++) {
                if (f[j] < f[j - c[i]] + w[i]) {
                    f[j] = f[j - c[i]] + w[i];
                    path[i][j] = 1;
                }
            }
        }

        int i = c.length;
        int j = v;

        //打印添加的物品,注意和简答背包一维数组的区别
        while (i >= 0 && j >= 0) {
            if (path[i][j] == 1) {
                System.out.println(i + "____" + c[i]);
                j -= c[i];
            } else {
                i--;
            }
        }
        return f;
    }

    /**
     * 可以丢掉很多数据
     * @param c
     * @param w
     * @param v
     */
    public static int[] preOperate(int[] c, int[] w, int v){
        boolean[] flag = new boolean[c.length];
        int count = 0;

        //去掉c[i] > v或者c[i] <= c[j] && w[i] >= w[j]的数据
        for(int i = 0;i < c.length ; i++){
            if(c[i] > v) {
                flag[i] = true;
                count++;
                continue;
            }
            for(int j = i + 1;j < w.length;j++){
                if(c[i] <= c[j] && w[i] >= w[j]){
                    flag[j] = true;
                    count++;
                }
            }
        }


        ArrayList<Integer> newC = new ArrayList<>();
        ArrayList<Integer> newW = new ArrayList<>();

        //一开始java代码没有想好,写的稍微有点复杂
        for(int i = 0;i < flag.length; i++){
            System.out.println(flag[i] + "   " + count);
            if(!flag[i]){
                newC.add(c[i]);
                newW.add(w[i]);
            }
        }

        int[] newc = new int[flag.length - count];
//        System.arraycopy(newC,0,newc,0,newc.length);
        int[] neww = new int[flag.length - count];
//        System.arraycopy(newW,0,neww,0,newc.length);

        for(int i = 0;i < newc.length; i++){
            newc[i] = newC.get(i);
            neww[i] = newW.get(i);
        }

//        for(int i = 0;i < newc.length; i++){
//            System.out.println(newc[i] + "  " +neww[i]);
//        }

        int[] ss = completeBag(newc,neww,v);
//        for (int i = 0; i <= v; i++) {
//            System.out.print(ss[i] + " - ");
//        }
        return ss;
    }

    public static void main(String[] args) {
        int[] c = {5, 2, 6, 5, 3};
        int[] w = {6, 3, 5, 4, 6};

        int v = 10;
//        int[] ss = completeBag(c, w, v);
//        for (int i = 0; i <= v; i++) {
//            System.out.print(ss[i] + " - ");
//        }

        int[] ss = preOperate(c, w, v);
        for (int i = 0; i <= v; i++) {
            System.out.print(ss[i] + " - ");
        }
    }
}
