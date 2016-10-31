package codewars;

import java.math.BigInteger;

/**
 * Created by ubuntu on 3/22/16.
 */
public class Dictionary {

    public static BigInteger fib(BigInteger n) {

//        if(n.compareTo(BigInteger.valueOf(2)) < 0 && n.compareTo(BigInteger.ZERO) >= 0){
//            return n;
//        }else if(n.compareTo(BigInteger.valueOf(2)) >= 0){
//            BigInteger a = BigInteger.ZERO;
//            BigInteger b = BigInteger.ONE;
//                for(BigInteger i = BigInteger.valueOf(2); i.compareTo(n.subtract(BigInteger.valueOf(1))) > 0 ;i=i.add(BigInteger.ONE)){
//                    b = a.add(b);
//                    a = b.subtract(a);
//                }
//                return a.add(b);
//        }else{
//            BigInteger a = BigInteger.ONE;
//            BigInteger b = BigInteger.ZERO;
//            for(BigInteger i = n; i.compareTo(BigInteger.valueOf(-1)) < 0 ;i=i.add(BigInteger.ONE)){
//                b = a.subtract(b);
//                a = a.subtract(b);
//            }
//            return a.subtract(b);
//        }

//        long end = Long.parseLong(n.toString());
//        if(n.compareTo(BigInteger.valueOf(2)) < 0 && n.compareTo(BigInteger.ZERO) >= 0){
//            return n;
//        }else if(n.compareTo(BigInteger.valueOf(2)) >= 0){
//            long a = 0;
//            long b = 1;
//            for(int i = 2; i< end ;i++){
//                b = a+b;
//                a = b-a;
//            }
//            return BigInteger.valueOf(a+b);
//        }else{
//            long a = 0;
//            long b = 1;
//            for(long i = end; i < -1 ;i=i++){
//                b = a-b;
//                a = a-b;
//            }
//            return BigInteger.valueOf(a-b);
//        }
        Double end = n.doubleValue();
        Double abs = Math.abs(end);
        if (end >= 0 || abs % 2 != 0) {
            return BigInteger.valueOf((long) ((Math.pow((1 + Math.sqrt(5)) / 2, end) - Math.pow((1 - Math.sqrt(5)) / 2, end)) / Math.sqrt(5)));
        } else {
            return BigInteger.valueOf((long) (-(Math.pow((1 + Math.sqrt(5)) / 2, abs) - Math.pow((1 - Math.sqrt(5)) / 2, abs)) / Math.sqrt(5)));
        }
    }


    public static void main(String[] args) {
        BigInteger n = fib(BigInteger.valueOf(-1000));
        System.out.println(n);
    }
}
