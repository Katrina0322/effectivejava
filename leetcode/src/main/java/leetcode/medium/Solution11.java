package leetcode.medium;

/**
 * filename: Solution11
 * Description:
 * Author: ubuntu
 * Date: 9/29/17 4:10 PM
 */
public class Solution11 {
    public static int maxArea(int[] height) {
        if(height.length < 2) return 0;
        int maxArea = 0, l = 0, r = height.length - 1;
        while (l < r){
            maxArea = Math.max(maxArea, Math.min(height[l], height[r]) * (r - l));
            if(height[l] < height[r]) l++;
            else r--;
        }
        return maxArea;
    }

    public static void main(String[] args) {
        System.out.println(maxArea(new int[]{1,2,3,8,5,2}));
    }
}
