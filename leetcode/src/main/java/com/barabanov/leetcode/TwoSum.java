package com.barabanov.leetcode;

import java.util.*;

/**
 1. Two Sum
 */
public class TwoSum {

    public static void main(String[] args) {
        Arrays.stream(twoSum(new int[]{2, 9, 11, 7}, 9)).forEach(System.out::println);
    }


    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> neededNumToPairIdx = new HashMap<>();

        for (int idx = 0; idx < nums.length; idx++) {
            if (neededNumToPairIdx.containsKey(nums[idx]))
                return new int[]{idx, neededNumToPairIdx.get(nums[idx])};

            Integer neededNum = target - nums[idx];
            neededNumToPairIdx.put(neededNum, idx);
        }

        return null;
    }
}
