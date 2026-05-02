package com.barabanov.leetcode.top.interview150;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 128
 */
public class LongestConsecutiveSequence {
    public static void main(String[] args) {
        System.out.println(NotMineLongestConsecutive(new int[]{100,4,200,1,3,2}));
    }


    public static int NotMineLongestConsecutive(int[] nums) {
        Map<Integer, Integer> seqBoundariesMap = new HashMap<>();
        Set<Integer> processedNums = new HashSet<>();

        int maxSeqLength = 0;
        for (int num : nums) {
            if (processedNums.contains(num))
                continue;

            int leftSeqLength = seqBoundariesMap.getOrDefault(num - 1, 0);
            int rightSeqLength = seqBoundariesMap.getOrDefault(num + 1, 0);

            int mergedSeqLength = 1 + leftSeqLength + rightSeqLength;
            seqBoundariesMap.put(num - leftSeqLength, mergedSeqLength);
            seqBoundariesMap.put(num + rightSeqLength, mergedSeqLength);
            processedNums.add(num);

            if (mergedSeqLength > maxSeqLength)
                maxSeqLength = mergedSeqLength;
        }

        return maxSeqLength;
    }
}
