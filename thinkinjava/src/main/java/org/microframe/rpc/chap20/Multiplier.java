package org.microframe.rpc.chap20;

/**
 * used to
 * Created by tianjin on 8/2/16.
 */
@ExtractInterface("IMultiplier")
public class Multiplier {

    public static void main(String[] args) {
        Multiplier multiplier = new Multiplier();
        System.out.println(multiplier.multiply(11, 16));
    }

    public int multiply(int x, int y) {
        int total = 0;
        for (int i = 0; i < x; i++) {
            total = add(total, y);
        }
        return total;
    }

    private int add(int x, int y) {
        return x + y;
    }
}
