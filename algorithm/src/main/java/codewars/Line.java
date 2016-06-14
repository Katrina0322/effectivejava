package codewars;

/**
 * Created by ubuntu on 2/24/16.
 */
public class Line {

    public static String WhoIsNext(String[] names, int n) {
        if(n > 1000000000 || n < 1) return null;

        /**
         * 我第一次写的,运行时间太长
         */
//        ArrayList<String> list = new ArrayList<>();
//        for(String s:names){
//            list.add(s);
//        }
//
//        if( n <= 5 ) return list.get(n-1);
//        String name = null;
//        int count = 0;
//        while(count < n - 5){
//            name = list.get(0);
//            list.add(name);
//            list.add(name);
//            list.remove(0);
//            count+=2;
//        }
//        return name;
        /**
         * 第二次写的,超级快
         */
        if( n <= 5 ) return names[n-1];
        int a = (int)(Math.log(n/5+1)/ Math.log(2));
        System.out.println(a);
        int b = (int) (n - 5*(Math.pow(2,a)-1));
        System.out.println(b);
        return names[b/((int)Math.pow(2,a))];

        /**
         * 别人用循环写的,想法很好
         */
//        int count = 0;
//        String result = "";
//        int j = 1;
//        outer: while(count < n){
//            for (int i = 0; i < names.length; i++) {
//                result = names[i];
//                count += j;
//                if (count >= n) break outer;
//            }
//            j *= 2;
//        }
//        return result;

        /**
         * 循环使用两个参数
         */
//        if (n<1) throw new IllegalArgumentException("drink number must be equal or greater than 1");
//        else if (n <= 5) return names[n-1];
//        int L0 = names.length,
//                pos = 0,
//                step = 1;
//        for (int drink = 6, turn = 0; drink <= n; drink++, turn++) {
//            if (turn % (L0 * step) == 0) {
//                step = step*2;
//                turn = 0;
//            }
//            pos = turn/step % L0;
//        }
//        return names[pos];


        /**
         * 二进制
         */

//        int x = n / names.length;
//
//        if (Integer.toBinaryString(x).length() == Integer.bitCount(x) || 0 == x) {
//            x = x + 1;
//        }
//
//        int basis = n - (names.length * (Integer.highestOneBit(x) - 1));
//        double m = Math.pow(2, Integer.bitCount(Integer.highestOneBit(x) - 1));
//
//        return names[(int) Math.ceil(basis / m) - 1];


        /**
         * 和我第二次的解法一样,代码比我的简洁
         */
//        if (n < 1) return "";
//        if (n < 6) return names[n-1];
//
//        int r = (int)(Math.log((n - 1)/5 + 1) / Math.log(2));
//        int np = n - 5 * (int)(Math.pow(2, r) - 1);
//        int p = np / (int)Math.pow(2, r);
//
//        return names[p];
    }
}
