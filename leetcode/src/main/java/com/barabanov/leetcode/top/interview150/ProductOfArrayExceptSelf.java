package com.barabanov.leetcode.top.interview150;

/**
 * 238. Product of Array Except Self
 */
public class ProductOfArrayExceptSelf {
    public static void main(String[] args) {
        productExceptSelf(new int[]{1, 2, 3, 4});
    }

    public static int[] productExceptSelf(int[] nums) {
        int[] answer = new int[nums.length];

        int prefix = 1;
        for (int i = 0; i < nums.length; i++) {
            answer[i] = prefix;
            prefix *= nums[i];
        }

        int suffix = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            answer[i] = answer[i] * suffix;
            suffix *= nums[i];
        }

        return answer;
    }
}
