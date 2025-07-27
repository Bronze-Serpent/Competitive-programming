package com.barabanov.leetcode.top;

/**
 * 1493
 */
public class LongestSubarrayOf1AfterDeletingOneElement {
    public static void main(String[] args) {

    }


    public static int longestSubarray(int[] nums) {
        int currentSeqSize = 0;
        int prevSeqSize = 0;
        int maxSeqSize = 0;

        for (int num : nums) {
            if (num == 0) {
                if (prevSeqSize > maxSeqSize)
                    maxSeqSize = prevSeqSize;
                prevSeqSize = currentSeqSize;
                currentSeqSize = 0;
            } else {
                currentSeqSize += 1;
                prevSeqSize += 1;
            }
        }

        if (prevSeqSize > maxSeqSize)
            maxSeqSize = prevSeqSize;

        if (maxSeqSize == nums.length)
            return maxSeqSize - 1;

        return maxSeqSize;
    }
}
