package com.barabanov.leetcode.top.interview150;

/**
 * 383
 */
public class RansomNote {
    public static void main(String[] args) {
        System.out.println(canConstruct("aaaabbbbcccd", "abd"));
    }

    public static boolean canConstruct(String ransomNote, String magazine) {
        if (ransomNote == null && magazine == null)
            return true;
        if (ransomNote == null)
            return true;
        if (magazine == null)
            return false;

        int[] charQuantityArray = new int[26];

        for (char ch : magazine.toCharArray())
            charQuantityArray[(int) ch - 97]++;

        for (char ch : ransomNote.toCharArray()) {
            if (charQuantityArray[(int) ch - 97] == 0)
                return false;

            charQuantityArray[(int) ch - 97]--;
        }

        return true;
    }
}
