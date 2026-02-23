package com.barabanov.leetcode.top.interview150;

/**
 * 6.
 */
public class ZigzagConversion {

    public static void main(String[] args) {
        System.out.println(convert("PAYPALISHIRING", 3));
    }


    public static String convert(String s, int numRows) {
        if (numRows < 1)
            throw new IllegalArgumentException("Недопустимое значение numRows: " + numRows);
        if (s == null || s.isEmpty())
            return null;
        if (numRows == 1)
            return s;

        StringBuilder[] sbLines = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++)
            sbLines[i] = new StringBuilder();

        boolean isItGoingDown = true;
        int lineIdx = 0;
        for (int charIdx = 0; charIdx < s.length(); charIdx++) {
            sbLines[lineIdx].append(s.charAt(charIdx));

            if (lineIdx == numRows - 1)
                isItGoingDown = false;
            else if (lineIdx == 0)
                isItGoingDown = true;

            if (isItGoingDown)
                lineIdx++;
            else
                lineIdx--;
        }

        StringBuilder resultSb = sbLines[0];
        for (int i = 1; i < numRows; i++)
            resultSb.append(sbLines[i]);

        return resultSb.toString();
    }
}
