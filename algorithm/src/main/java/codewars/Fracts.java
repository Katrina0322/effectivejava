package codewars;

/**
 * Created by ubuntu on 2/24/16.
 */
public class Fracts {

    static long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    static long lcm(long a, long b) {
        return a * b / gcd(a, b);
    }

    public static String convertFrac(long[][] lst) {
        long lcmall = 1;
        for (long[] item : lst) {
            lcmall = lcm(lcmall, item[1]);
        }
        String result = "";
        for (long[] item : lst) {
            result += "(" + (item[0] * lcmall / item[1]) + "," + lcmall + ")";
        }
        return result;
    }

}
