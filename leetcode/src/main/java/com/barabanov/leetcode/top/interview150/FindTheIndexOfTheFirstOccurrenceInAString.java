package com.barabanov.leetcode.top.interview150;

/**
 * 28
 */
public class FindTheIndexOfTheFirstOccurrenceInAString {
    public static void main(String[] args) {
        System.out.println(strStr("sadbutsad", "sad"));
    }


    public static int strStr(String haystack, String needle) {

        if (haystack == null || needle == null || haystack.isEmpty() || needle.isEmpty())
            return -1;

        int haystackIdx = 0;
        while(haystackIdx < haystack.length()) {
            if (haystack.charAt(haystackIdx) == needle.charAt(0)) {
                int prefixIdx = 1;
                while (prefixIdx < needle.length()) {
                    if (haystackIdx + prefixIdx >= haystack.length() ||
                            haystack.charAt(haystackIdx + prefixIdx) != needle.charAt(prefixIdx))
                        break;
                    prefixIdx++;
                }
                if (prefixIdx == needle.length())
                    return haystackIdx;
            }
            haystackIdx++;
        }

        return -1;
    }
}
