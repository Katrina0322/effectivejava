package leetcode.medium;


/**
 * filename: Solution8
 * Description:
 * Author: ubuntu
 * Date: 9/26/17 10:20 AM
 */
public class Solution8 {
    public int myAtoi(String str) {
        int base = 0, sign = 1, i = 0;
        if (str.length() == 0) return 0;
        while (i < str.length() && str.charAt(i) == ' ') i++;
        if (i < str.length() && (str.charAt(i) == '-' || str.charAt(i) == '+')) sign = str.charAt(i++) == '-' ? -1 : 1;
        while (i < str.length()) {
            int digit = str.charAt(i) - '0';
            if (digit > 9 || digit < 0) break;
            if (base > Integer.MAX_VALUE / 10 || (base == Integer.MAX_VALUE / 10 && digit > 7)) {
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            base = base * 10 + digit;
            ++i;
        }
        return base * sign;
    }
}
