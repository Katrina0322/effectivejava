package dp;


/**
 * used to
 * Created by tianjin on 11/24/16.
 */
public class MultipleBag {

    /**
     * F[I][V]可以从F[I-1][V-Wi],F[I-1][V-2Wi],F[I-1][V-3Wi]...转移而来
     * F[I][V-Wi]可以从F[I-1][V-2Wi],F[I-1][V-3Wi] ...转移而来
     * 由于F[I][]要反复地借用F[I-1][]这些状态，因此想到将F[I-1][]的这些状态放入一个队列      将余数相同的体积分为一组
     * <p/>
     * f[i][j]=max{f[i−1][j−k*wi]+k*vi}, k∈[0, ci]
     * 令 j=p*wi+r ,  r=j%wi ,    p=j/wi
     * f[i][p*wi+r]=max{f[i−1][(p−k)*wi+r]+k*vi}, k∈[0, ci]
     * 令q=p−k:
     * f[i][p*wi+r]=max{f[i−1][q*wi+r]+(p−q)*vi}, q∈[p−ci, p]
     * f[i][p*wi+r]=max{f[i−1][q*wi+r]−q*vi}+p*vi, q∈[p−ci, p]    p=j/wi
     *
     * @param w 体积或者重量 weight
     * @param v 价值 value
     * @param c 个数
     * @param V 总体积或者总承重
     * @return
     */
    public static int[] multipleBag(int[] w, int[] v, int[] c, int V) {
        if (w.length != v.length) throw new IllegalArgumentException();
        int[][] f = new int[2][V + 1];      //滚动数组   一个保存f[i][v],一个保存f[i-1][q * w[i] + r] - q * v[i]) + p * v[i],循环利用.也可以将其中一个数组和Q保存在一起
        int[] Q = new int[V + 1];       //保存p,这里相当于一个双端队列
        int z = 0;
        f[z][0] = 0;
        for (int i = 0; i < w.length; i++) {
            for (int r = 0; r < w[i]; ++r) {     //对不同的余数   r=j % wi
                int left = 0, right = 0;  //left和right相当于指针,这里模仿一个定长的双端队列
                for (int p = 0; p * w[i] + r <= V; ++p) {       //  p=j / wi
                    while (left < right && f[z][Q[right - 1] * w[i] + r] - Q[right - 1] * v[i] < f[z][p * w[i] + r] - p * v[i]) --right;    //使得每个状态相当于体积为r,互相之间就具有了可比性
                    Q[right++] = p;
                    while (left < right && p - c[i] > Q[left]) ++left;   // q∈[p−ci, p]   q >= p-c[i]
                    int q = Q[left];        //q取最小值,相当于k取最大值
                    f[1 - z][p * w[i] + r] = (f[z][q * w[i] + r] - q * v[i]) + p * v[i];
                }
            }
            z = 1 - z;
        }
        return f[z];
    }

    public static void main(String[] args) {
        int[] w = {5, 2, 6, 5, 3};
        int[] v = {6, 3, 5, 4, 6};
        int[] c = {1, 1, 1, 1, 3};
        int[] f = multipleBag(w, v, c, 10);
        System.out.println(f.length);
        for (int i = 0; i < f.length; i++) {
            System.out.print(f[i] + "__");
        }
    }
}
