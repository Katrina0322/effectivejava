package leetcode.hard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * used to
 * Created by tianjin on 9/20/16.
 */
public class Solution4 {
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        List<Integer> list = new ArrayList<>();
        for(int a:nums1){
            list.add(a);
        }

        for(int a:nums2){
            list.add(a);
        }

        Collections.sort(list);

        if(list.size() % 2 == 0){
            return (list.get(list.size() / 2 - 1) + list.get(list.size() / 2)) / 2.0;
        }else{
            return list.get(list.size() / 2);
        }
    }

    public static void main(String[] args) {
        System.out.println(findMedianSortedArrays(new int[]{1, 2},new int[]{3,4}));
    }
}
