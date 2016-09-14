package leetcode.simple;

import java.util.HashMap;
import java.util.Map;

/**
 * used to
 * Created by tianjin on 9/14/16.
 */
public class Solution {
    public static int[] twoSum(int[] nums, int target) {
//        for (int i = 0; i < nums.length; i++) {
//            for (int j = i + 1; j < nums.length; j++) {
//                if (nums[j] == target - nums[i]) {
//                    return new int[] { i, j };
//                }
//            }
//        }
//        throw new IllegalArgumentException("No two sum solution");


        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[] { map.get(complement), i };
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }



    public static void main(String[] args) {
        int[] pre = {3,2,4};
        System.out.println(twoSum(pre,6)[0] + "--" + twoSum(pre,6)[1]);
    }
}
