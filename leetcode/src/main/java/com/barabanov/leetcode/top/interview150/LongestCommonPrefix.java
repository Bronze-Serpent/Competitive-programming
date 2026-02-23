package com.barabanov.leetcode.top.interview150;

/**
 * 14
 */
public class LongestCommonPrefix {
    public static void main(String[] args) {
        System.out.println(longestCommonPrefix(new String[]{"dog","racecar","car"}));
    }


    public static String longestCommonPrefix(String[] strs) {

        StringBuilder commonPrefixSb = new StringBuilder();
        int currPrefixIdx = 0;
        while (currPrefixIdx < strs[0].length()) {

            char currPrefixChar = strs[0].charAt(currPrefixIdx);
            for (int strIdx = 1; strIdx < strs.length; strIdx++) {

                if (currPrefixIdx >= strs[strIdx].length() ||
                        strs[strIdx].charAt(currPrefixIdx) != currPrefixChar)
                    return commonPrefixSb.toString();
            }

            commonPrefixSb.append(currPrefixChar);
            currPrefixIdx++;
        }

        return commonPrefixSb.toString();
    }
}
