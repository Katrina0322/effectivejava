package codewars;


import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by ubuntu on 2/22/16.
 */
public class TestDay1 {

    public static void main(String[] args) {
        System.out.println(factors(7775460));
    }


    public static int bouncingBall(double h, double bounce, double window) {
        if (bounce >= 1 || bounce <= 0 || h <= window) {
            return -1;
        }
        int n = (int) Math.floor(Math.log(window / h) / Math.log(bounce));
        return 2 * n + 1;
    }

    public static int Solution(int n, int m) {
        int count = 0;
        for (int b = 0; b <= n; b++) {
            double a = Math.sqrt(n - b);
            if (a == Math.ceil(a) && (a + (int) Math.pow(b, 2)) == m) {
                count++;
            }
        }
        return count;
    }


    public static String factors(int n) {
        StringBuffer sb = new StringBuffer();
        Map<Integer, Integer> result = new LinkedHashMap<Integer, Integer>();
        int key = 1;
        while (n > 1) {
            for (int i = 2; i <= n; i++) {
                if (n % i == 0) {
                    key = i;
                    if (result.containsKey(i)) {
                        result.put(i, result.get(i) + 1);
                    } else {
                        result.put(i, 1);
                    }
                    break;
                }
            }
            n /= key;
        }
        Iterator<Map.Entry<Integer, Integer>> iterator = result.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, Integer> entry = iterator.next();
            if (entry.getValue() == 1) {
                sb.append("(" + entry.getKey() + ")");

            } else {
                sb.append("(" + entry.getKey() + "**" + entry.getValue() + ")");
            }
        }
        return sb.toString();
    }
}
