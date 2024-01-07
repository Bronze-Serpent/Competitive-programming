package com.barabanov.tinkoff;

import java.util.Arrays;
import java.util.Scanner;

public class MaxWordsSheriff
{

    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        String letters = scanner.nextLine();

        int maxOfWordSheriff = findMaxNumOfWordsSheriff(letters.toCharArray());
        System.out.println(maxOfWordSheriff);
    }


    private static int findMaxNumOfWordsSheriff(char[] letters)
    {
        // s - 0
        // h - 1
        // e - 2
        // r - 3
        // i - 4
        // f - 5
        int[] numOfAvailLtrs = new int[6];

        for (char letter : letters)
        {
            switch (letter)
            {
                case 's' -> numOfAvailLtrs[0]++;
                case 'h' -> numOfAvailLtrs[1]++;
                case 'e' -> numOfAvailLtrs[2]++;
                case 'r' -> numOfAvailLtrs[3]++;
                case 'i' -> numOfAvailLtrs[4]++;
                case 'f' -> numOfAvailLtrs[5]++;
            }
        }

        return Arrays.stream(numOfAvailLtrs).min().getAsInt();
    }


    private static void test()
    {
        int numOfLetters = 2 * 100_000;

        char[] letters = new char[numOfLetters];
        for (int i = 0; i < numOfLetters; i++)
            letters[i] = 5;

        letters[0] = 's';
        letters[1] = 'h';
        letters[2] = 'e';
        letters[3] = 'r';
        letters[4] = 'i';
        letters[5] = 'f';
        letters[6] = 'f';

        int maxOfWordSheriff = findMaxNumOfWordsSheriff(letters);
        System.out.println(maxOfWordSheriff);
    }
}
