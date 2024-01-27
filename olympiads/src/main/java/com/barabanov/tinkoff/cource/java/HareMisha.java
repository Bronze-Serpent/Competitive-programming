package com.barabanov.tinkoff.cource.java;

import java.util.*;

public class HareMisha
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        scanner.nextLine();

        HashMap<Integer, Integer> typeOfBookToRepeats = new HashMap<>();

        Arrays.stream(scanner.nextLine().split(" "))
                .map(Integer::valueOf)
                .forEach((bookLength) ->
                        typeOfBookToRepeats.merge(bookLength, 1, Integer::sum)
                );

        Integer[] bookStacks = typeOfBookToRepeats.values()
                .toArray(Integer[]::new);
        Arrays.sort(bookStacks);

        System.out.println(bookStacks.length);

        for (int i = 0; i < bookStacks.length; i++)
        {
            System.out.print(bookStacks[i]);
            if (i != bookStacks.length - 1)
                System.out.print(" ");
        }
    }
}
