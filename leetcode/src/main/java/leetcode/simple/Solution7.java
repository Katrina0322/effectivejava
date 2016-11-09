package leetcode.simple;

/**
 * used to
 * Created by tianjin on 11/9/16.
 */
public class Solution7 {
    public static int reverse(int x) {
        long reverseNum = 0;
        while(x != 0){
            reverseNum = reverseNum * 10 + x % 10;
            x = x / 10;
        }
        if(reverseNum > Integer.MAX_VALUE || reverseNum < Integer.MIN_VALUE){
            return 0;
        }
        return (int) reverseNum;
    }

    public static void main(String[] args) {
        System.out.println(reverse(1534236469));
    }
}
