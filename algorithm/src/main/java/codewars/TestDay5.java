package codewars;

/**
 * used to
 * Created by tianjin on 6/20/16.
 */
public class TestDay5 {
    public static String solEquaStr(long n) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        for (long x = 1; x <= (int) Math.sqrt(n); x++) {
            if (n % x == 0) {
                long a = n / x;
                if ((a - x) % 4 == 0) stringBuilder.append(1 < stringBuilder.length() ? ", [" : "[").append((x + a) / 2 + ", " + (a - x) / 4 + "]");
            }
        }

        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        System.out.println(solEquaStr(90002));
    }
}
