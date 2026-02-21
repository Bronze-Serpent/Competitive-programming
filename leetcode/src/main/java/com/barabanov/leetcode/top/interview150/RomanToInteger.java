package com.barabanov.leetcode.top.interview150;

/**
 * 13
 */
public class RomanToInteger {
    public static void main(String[] args) {
        System.out.println(romanToIntNotMy("XIV")); //1570
    }


    public static int romanToInt(String s) {

        if (s == null)
            return 0;

        int intNum = 0;
        char[] chairs = s.toCharArray();
        for (int charIdx = 0; charIdx < chairs.length; charIdx++) {

            switch (chairs[charIdx]) {

                case 'I': {
                    if (charIdx + 1 >= chairs.length) {
                        intNum += 1;
                        break;
                    }
                    switch (chairs[charIdx + 1]) {
                        case 'V': {
                            intNum += 4;
                            charIdx++;
                            break;
                        }
                        case 'X': {
                            intNum += 9;
                            charIdx++;
                            break;
                        }
                        default: {
                            intNum += 1;
                        }
                    }
                    break;
                }
                case 'V': {
                    intNum += 5;
                    break;
                }
                case 'X': {
                    if (charIdx + 1 >= chairs.length) {
                        intNum += 10;
                        break;
                    }
                    switch (chairs[charIdx + 1]) {
                        case 'L': {
                            intNum += 40;
                            charIdx++;
                            break;
                        }
                        case 'C': {
                            intNum += 90;
                            charIdx++;
                            break;
                        }
                        default: {
                            intNum += 10;
                        }
                    }
                    break;
                }
                case 'L': {
                    intNum += 50;
                    break;
                }
                case 'C': {
                    if (charIdx + 1 >= chairs.length) {
                        intNum += 100;
                        break;
                    }
                    switch (chairs[charIdx + 1]) {
                        case 'D': {
                            intNum += 400;
                            charIdx++;
                            break;
                        }
                        case 'M': {
                            intNum += 900;
                            charIdx++;
                            break;
                        }
                        default: {
                            intNum += 100;
                        }
                    }
                    break;
                }
                case 'D': {
                    intNum += 500;
                    break;
                }
                case 'M': {
                    intNum += 1000;
                    break;
                }
            }
        }

        return intNum;
    }



    public static int romanToIntNotMy(String s) {

        int answer = 0, number = 0, prev = 0;

        for (int j = s.length() - 1; j >= 0; j--) {
            switch (s.charAt(j)) {
                case 'M' -> number = 1000;
                case 'D' -> number = 500;
                case 'C' -> number = 100;
                case 'L' -> number = 50;
                case 'X' -> number = 10;
                case 'V' -> number = 5;
                case 'I' -> number = 1;
            }
            if (number < prev) {
                answer -= number;
            }
            else {
                answer += number;
            }
            prev = number;
        }
        return answer;
    }
}

