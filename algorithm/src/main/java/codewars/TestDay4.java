package codewars;

import java.math.BigDecimal;


/**
 * Created by ubuntu on 2/26/16.
 */
public class TestDay4 {

    public static void main(String[] args) {

        System.out.println(going(7));
    }

    public static int[][] pascal(int depth) {
//        int[][] pas = new int[depth][];
//
//        for (int i = 0; i < depth; i++) {
//            if (i == 0) {
//                pas[0] = new int[]{1};
//            }else if (i == 1) {
//                pas[1] = new int[]{1, 1};
//            } else {
//                pas[i] = new int[i + 1];
//                for (int j = 0; j <= i; j++) {
//                    if (j == 0 || j == i) {
//                        pas[i][j] = 1;
//                    } else {
//                        pas[i][j] = pas[i - 1][j - 1] + pas[i - 1][j];
//                    }
//                }
//            }
//        }
//        return pas;


        int[][] triangle = new int[depth][];

        for (int i = 0; i < depth; i++) {
            triangle[i] = new int[i + 1];
            triangle[i][0] = 1;
            triangle[i][i] = 1;
            for (int j = 1; j < i; j++) {
                triangle[i][j] = triangle[i - 1][j] + triangle[i - 1][j - 1];
            }
        }
        return triangle;
    }


    public static boolean scramble(String str1, String str2) {
        while (str2.length() > 0) {
            String c = str2.charAt(0) + "";
            int len1 = str1.length();
            int len2 = str2.length();
            str1 = str1.replaceAll(c, "");
            str2 = str2.replaceAll(c, "");

            if ((len1 - str1.length()) < (len2 - str2.length())) return false;
        }
        return true;
    }


    public static double going(int n) {
        BigDecimal sum = BigDecimal.valueOf(0);
        BigDecimal an = BigDecimal.valueOf(1);
        for (int i = 1; i <= n; i++) {
            an = an.multiply(BigDecimal.valueOf(i));
            sum = sum.add(an);
        }
        return sum.divide(an, 6, BigDecimal.ROUND_FLOOR).doubleValue();
    }
}
