package codewars;

import java.math.BigInteger;

/**
 * Created by ubuntu on 2/25/16.
 */
public class TestDay3 {

    public static void main(String[] args) {
        System.out.println(gap(4, 4, 100));
    }

    public static BigInteger perimeter(BigInteger n) {
        // your code
        BigInteger sum = BigInteger.valueOf(2);
        if (n.equals(BigInteger.valueOf(0))) {
            return BigInteger.valueOf(1);
        } else if (n.equals(BigInteger.valueOf(1))) {
            return BigInteger.valueOf(2);
        } else {
            BigInteger last = BigInteger.valueOf(1);
            BigInteger lastTwo = BigInteger.valueOf(1);
            BigInteger result = BigInteger.valueOf(0);
            for (BigInteger i = BigInteger.valueOf(2); i.compareTo(n) <= 0; i = i.add(BigInteger.valueOf(1))) {
                result = last.add(lastTwo);
                lastTwo = last;
                last = result;
                sum = sum.add(result);
            }
        }
        return sum.multiply(BigInteger.valueOf(4));
    }

    public static long[] gap(int g, long m, long n) {
        // your code
        long[] result = new long[2];
        result[0] = 0;
        result[1] = 0;
        if (n - m < g) {
            return null;
        }
        outer:
        for (long i = m; i <= n; i++) {
            result[0] = result[1];
            for (long j = 2; j < i / 2; j++) {
                if (i % j == 0) {
                    continue outer;
                }

            }
            result[1] = i;
            if (result[1] - result[0] == g && result[0] > 0) {
                return result;
            }
        }
        return null;
    }
}
