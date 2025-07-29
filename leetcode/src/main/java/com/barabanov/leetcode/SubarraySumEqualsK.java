package com.barabanov.leetcode;

import java.util.HashMap;

/**
 * 560
 */
public class SubarraySumEqualsK {

    public static void main(String[] args) {
        System.out.println(subarraySum(new int[]{1, 2, 1, 1, 2, -7, 3, 3, 4, 1, 5, 2}, 7));
    }


    public static int subarraySum(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int sum = 0, count = 0;
        map.put(0, 1);

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            int freq = 1;

            if (map.containsKey(sum - k))
                count += map.get(sum - k);

            if (map.containsKey(sum))
                freq += map.get(sum);

            map.put(sum, freq);
        }
        return count;
    }


}
