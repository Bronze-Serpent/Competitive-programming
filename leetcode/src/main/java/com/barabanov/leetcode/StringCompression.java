package com.barabanov.leetcode;

/**
 * 443
 */
public class StringCompression {

    public static void main(String[] args) {
        System.out.println(compress(new char[]{'a', 'a', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'c', 'c', 'c'}));
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
                compressedIdx = writeCharAndQuantityIntoCharArr(prevChar, elemQuantity, chars, compressedIdx);
                prevChar = currentChar;
                elemQuantity = 1;
            }
        }

        compressedIdx = writeCharAndQuantityIntoCharArr(prevChar, elemQuantity, chars, compressedIdx);

        return compressedIdx;
    }


    private static int writeCharAndQuantityIntoCharArr(char ch, int quantity, char[] chars, int arrIdx) {
        chars[arrIdx++] = ch;
        if (quantity < 10) {
            if (quantity != 1)
                chars[arrIdx++] = Character.forDigit(quantity, 10);
        } else {
            int idx = 0;
            char[] quantityAsChars = new char[quantity];
            while (quantity != 0) {
                quantityAsChars[idx++] = Character.forDigit(quantity % 10, 10);
                quantity = quantity / 10;
            }
            for (int i = idx -1; i >= 0; i--)
                chars[arrIdx++] = quantityAsChars[i];
        }

        return arrIdx;
    }
}
