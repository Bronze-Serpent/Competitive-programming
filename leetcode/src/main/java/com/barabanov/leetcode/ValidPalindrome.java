package com.barabanov.leetcode;

public class ValidPalindrome {

    public static void main(String[] args) {

    }


    public static boolean isPalindrome(String s) {
        char[] clearedChairs = s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase().toCharArray();

        for (int headIdx = 0, tailIdx = clearedChairs.length - 1; headIdx < clearedChairs.length / 2; headIdx++, tailIdx--) {
            if (clearedChairs[headIdx] != clearedChairs[tailIdx])
                return false;
        }

        return true;
    }


    public static boolean isPalindromeOptimized(String s) {
        int leftIdx = 0, rightIdx = s.length() - 1;

        char leftChar, rightChar;
        while (leftIdx < rightIdx) {

            // Такая проверка быстрее, чем Character.isLetterOrDigit
            leftChar = s.charAt(leftIdx);
            if (!(('a' <= leftChar && leftChar <= 'z') ||
                    ('A' <= leftChar && leftChar <= 'Z') ||
                    ('0' <= leftChar && leftChar <= '9'))) {
                leftIdx++;
                continue;
            }

            rightChar = s.charAt(rightIdx);
            if (!(('a' <= rightChar && rightChar <= 'z') ||
                    ('A' <= rightChar && rightChar <= 'Z') ||
                    ('0' <= rightChar && rightChar <= '9'))) {
                rightIdx--;
                continue;
            }

            if (Character.toLowerCase(leftChar) != Character.toLowerCase(rightChar))
                return false;
            leftIdx++;
            rightIdx--;
        }

        return true;
    }
}
