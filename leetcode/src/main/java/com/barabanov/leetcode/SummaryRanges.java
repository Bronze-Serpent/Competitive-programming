package com.barabanov.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 228
 */
public class SummaryRanges {
    public static void main(String[] args) {

    }


    public static List<String> summaryRanges(int[] nums) {
        if (nums == null || nums.length == 0)
            return Collections.emptyList();

        List<String> rangeList = new ArrayList<>();
        String rangeSeparator = "->";
        int rangeFrom = nums[0];
        int rangeTo = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (rangeTo + 1 == nums[i])
                rangeTo = nums[i];
            else {
                if (rangeFrom == rangeTo)
                    rangeList.add(String.valueOf(rangeFrom));
                else
                    rangeList.add(rangeFrom + rangeSeparator + rangeTo);
                rangeFrom = nums[i];
                rangeTo = nums[i];
            }
        }

        if (rangeFrom == rangeTo)
            rangeList.add(String.valueOf(rangeFrom));
        else
            rangeList.add(rangeFrom + rangeSeparator + rangeTo);

        return rangeList;
    }
}
