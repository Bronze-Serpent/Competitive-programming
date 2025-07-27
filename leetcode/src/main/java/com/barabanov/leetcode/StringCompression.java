package com.barabanov.leetcode;

/**
 * 443
 */
public class StringCompression {

    public static void main(String[] args) {
        System.out.println(compress(new char[]{'a'}));
    }

    public static int compress(char[] chars) {
        if (chars == null || chars.length == 0)
            return 0;

        int compressedIdx = 0;
        int elemQuantity = 0;
        char prevChar = chars[0];

        for (char currentChar : chars) {
            if (prevChar == currentChar)
                elemQuantity += 1;
            else {
                chars[compressedIdx++] = prevChar;
                if (elemQuantity < 10) {
                    chars[compressedIdx++] = (char) elemQuantity;
                    elemQuantity = 0;
                } else
                    while (elemQuantity != 0) {
                        chars[compressedIdx++] = (char) (elemQuantity % 10);
                        elemQuantity = elemQuantity / 10;
                    }

                prevChar = currentChar;
            }
        }

        chars[compressedIdx++] = prevChar;
        if (elemQuantity < 10) {
            chars[compressedIdx++] = (char) elemQuantity;
            elemQuantity = 0;
        } else
            while (elemQuantity != 0) {
                chars[compressedIdx++] = (char) (elemQuantity % 10);
                elemQuantity = elemQuantity / 10;
            }

        return compressedIdx;
    }
}
