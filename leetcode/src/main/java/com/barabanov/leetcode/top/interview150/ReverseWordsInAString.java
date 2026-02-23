package com.barabanov.leetcode.top.interview150;

/**
 * 151
 */
public class ReverseWordsInAString {
    public static void main(String[] args) {
        System.out.println(reverseWords("a good   example"));
    }


    public static String reverseWords(String s) {

        char[] words = s.toCharArray();

        int reversedWordsIdx = 0;
        char[] reversedWords = new char[words.length];


        int charIdx = words.length - 1;
        int wordStartIdx = 0;
        int wordEndIdx = 0;
        boolean isItNewWord = true;
        boolean isItFirstWord = true;
        while (charIdx >= 0) {
            char currentChar = words[charIdx];
            if (currentChar != ' ') {
                if (isItNewWord) {
                    wordEndIdx = charIdx;
                    isItNewWord = false;
                }
                wordStartIdx = charIdx;
            }
            else if (!isItNewWord) {
                isItNewWord = true;
                if (!isItFirstWord)
                    reversedWords[reversedWordsIdx++] = ' ';

                int wordLength = wordEndIdx - wordStartIdx + 1;
                System.arraycopy(words, wordStartIdx, reversedWords, reversedWordsIdx, wordLength);
                reversedWordsIdx += wordLength;
                isItFirstWord = false;
            }

            charIdx--;
        }

        if (!isItNewWord) {
            if (!isItFirstWord)
                reversedWords[reversedWordsIdx++] = ' ';
            int wordLength = wordEndIdx - wordStartIdx + 1;
            System.arraycopy(words, wordStartIdx, reversedWords, reversedWordsIdx, wordLength);
            reversedWordsIdx += wordLength;
        }

        return new String(reversedWords, 0, reversedWordsIdx);
    }

    public static String easyReverseWords(String s) {
        if (s == null || s.isEmpty())
            return null;

        String[] words = s.trim().split("\\s+");
        int leftWordIdx = 0;
        int rightWordIdx = words.length - 1;
        while (leftWordIdx < rightWordIdx) {
            String temp = words[leftWordIdx];
            words[leftWordIdx] = words[rightWordIdx];
            words[rightWordIdx] = temp;

            leftWordIdx++;
            rightWordIdx--;
        }

        return String.join(" ", words);
    }
}
