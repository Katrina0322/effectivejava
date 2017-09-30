package leetcode.simple;

/**
 * filename: Solution9
 * Description:
 * Author: ubuntu
 * Date: 9/28/17 4:21 PM
 */
public class Solution9 {
    public static boolean isPalindrome(int x) {
        if(x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }
        int half = 0;
        while (x > half){
            half = half * 10 + x % 10;
            x = x / 10;
        }
        return half == x || x == half / 10;
    }

    public static void main(String[] args) {
        System.out.println(isPalindrome(1));
    }
}
