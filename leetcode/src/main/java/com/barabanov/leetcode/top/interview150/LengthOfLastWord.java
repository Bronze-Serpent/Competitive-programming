package com.barabanov.leetcode.top.interview150;

/**
 * 58
 */
public class LengthOfLastWord {
    public static void main(String[] args) {

    }


    public int lengthOfLastWord(String s) {

        char[] inputString = s.toCharArray();
        boolean isFirstSpacesSkipped = false;
        int charIdx = inputString.length - 1;
        int lengthOfLastWord = 0;
        while (charIdx >= 0) {
            if (inputString[charIdx] != ' ') {
                isFirstSpacesSkipped = true;
                lengthOfLastWord++;
            } else if (isFirstSpacesSkipped)
                return lengthOfLastWord;
            charIdx--;
        }

        return lengthOfLastWord;
    }
}
