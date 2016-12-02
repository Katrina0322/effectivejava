package leetcode.medium;

/**
 * used to
 * Created by tianjin on 11/8/16.
 */
public class Solution5 {
    int palinStart = 0;
    int palinLen = 0;

    public String longestPalindrome(String s) {

        if (s.length() < 2 || s == null) return s;

        char[] chars = s.toCharArray();

        for (int i = 0; i < chars.length - 1; i++) {
            checkThisPos(chars, i, i);
            checkThisPos(chars, i, i + 1);
        }
        return s.substring(palinStart, palinStart + palinLen);
    }

    private void checkThisPos(char[] chars, int left, int right) {

        while (left >= 0 && right < chars.length && chars[left] == chars[right]) {
            left--;
            right++;
        }
        if (right - left - 1 > palinLen) {
            palinLen = right - left - 1;
            palinStart = left + 1;
            // System.out.println("palinStart: "+ palinStart+" palinLen: "+palinLen);
        }

    }
}
