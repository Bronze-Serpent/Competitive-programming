package com.barabanov.leetcode.top.interview150;

import java.util.HashMap;
import java.util.Map;

/**
 * 12
 */
public class IntegerToRoman {
    public static void main(String[] args) {
        System.out.println(intToRoman(3749));
    }

    public static String intToRoman(int num) {
        Map<Integer, String> intToRomanDictionary = new HashMap<>();
        intToRomanDictionary.put(1,"I");
        intToRomanDictionary.put(2,"II");
        intToRomanDictionary.put(3,"III");
        intToRomanDictionary.put(4,"IV");
        intToRomanDictionary.put(5,"V");
        intToRomanDictionary.put(6,"VI");
        intToRomanDictionary.put(7,"VII");
        intToRomanDictionary.put(8,"VIII");
        intToRomanDictionary.put(9,"IX");
        intToRomanDictionary.put(10,"X");
        intToRomanDictionary.put(20,"XX");
        intToRomanDictionary.put(30,"XXX");
        intToRomanDictionary.put(40,"XL");
        intToRomanDictionary.put(50,"L");
        intToRomanDictionary.put(60,"LX");
        intToRomanDictionary.put(70,"LXX");
        intToRomanDictionary.put(80,"LXXX");
        intToRomanDictionary.put(90,"XC");
        intToRomanDictionary.put(100,"C");
        intToRomanDictionary.put(200,"CC");
        intToRomanDictionary.put(300,"CCC");
        intToRomanDictionary.put(400,"CD");
        intToRomanDictionary.put(500,"D");
        intToRomanDictionary.put(600,"DC");
        intToRomanDictionary.put(700,"DCC");
        intToRomanDictionary.put(800,"DCCC");
        intToRomanDictionary.put(900,"CM");
        intToRomanDictionary.put(1000,"M");

        StringBuilder sb = new StringBuilder();
        int digitPlace = 1;
        while (num != 0) {
            int digit = (num % 10);

            if (digit != 0) {
                int thousandNum = digitPlace / 1000 * digit;
                if (thousandNum != 0) {
                    for (int i = 0; i < thousandNum; i++)
                        sb.insert(0, intToRomanDictionary.get(1000));
                }
                else
                    sb.insert(0, intToRomanDictionary.get(digit * digitPlace));
            }

            num = num / 10;
            digitPlace = digitPlace * 10;
        }

        return sb.toString();
    }


    public static String intToRomanNotMine(int num) {
        int[] values = new int[]{1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] romanValues = new String[]{"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        StringBuilder resultSb = new StringBuilder();
        int valueIdx = 0;
        while(num != 0) {
            if (num >= values[valueIdx]) {
                resultSb.append(romanValues[valueIdx]);
                num -= values[valueIdx];
            }
            else
                valueIdx++;
        }

        return resultSb.toString();
    }
}
