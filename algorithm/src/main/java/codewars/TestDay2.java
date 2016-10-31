package codewars;

import java.math.BigInteger;
import java.util.ArrayList;


/**
 * Created by ubuntu on 2/24/16.
 */
public class TestDay2 {

    public static void main(String[] args) {


    }


    public static String convertFrac(long[][] lst) {
        if (lst.length == 0) {
            return "";
        }
        ArrayList<Long> arrays = new ArrayList<>();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < lst.length; i++) {
            if (lst[i][1] > 0) {
                arrays.add(lst[i][1]);
            }
        }
        long result = arrays.get(0);
        for (int i = 1; i < arrays.size(); i++) {
            long x = result;
            long y = arrays.get(i);
            while (true) {
                if (0 == (y %= x)) {
                    result = result * arrays.get(i) / x;
                    break;
                }
                if (0 == (x %= y)) {
                    result = result * arrays.get(i) / y;
                    break;
                }
            }
        }
//        for(long i = 0; i <= Long.MAX_VALUE ; i++){
//            int num = lst.length;
//            while(num > 0){
//                int count = 0;
//                for(long array:arrays){
//                    if( i % array != 0){
//                        break;
//                    }else{
//                        count++;
//                    }
//                }
//                if(count == arrays.size()){
//                    result = i;
//                }
//                num--;
//            }
//            if(result > 0){
//                break;
//            }
//        }
        System.out.println(result);
        for (int i = 0; i < lst.length; i++) {
            sb.append("(" + (result / lst[i][1] * lst[i][0]) + "," + result + ")");
        }
        return sb.toString();
    }


    public static int zeros(int n) {
        if (n == 0) return 0;

        BigInteger factorial = BigInteger.valueOf(1);
        for (int i = 1; i <= n; i++) {
            factorial = factorial.multiply(BigInteger.valueOf((long) i));
        }
        System.out.println(factorial);
        int count = 0;
        String s = factorial.toString();

        for (int i = s.length() - 1; i >= 0; i--) {
            if (Integer.parseInt(String.valueOf(s.charAt(i))) == 0) {
                count++;
            } else {
                break;
            }
        }
        return count;
    }
}
