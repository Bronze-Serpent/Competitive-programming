package com.barabanov.tinkoff;


import java.util.Scanner;

public class TheMostExpensiveRevolver
{

    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        int numOfRev = scanner.nextInt();
        int money = scanner.nextInt();

        int[] prices = new int[numOfRev];
        for (int i = 0; i < numOfRev; i++)
            prices[i] = scanner.nextInt();

        int mostExRev = findClosestFromTheBtm(prices, money);
        System.out.println(mostExRev);
    }


    private static int findClosestFromTheBtm(int[] prices, int money)
    {
        int closest = 0;

        for (int price : prices)
            if (price <= money && price > closest)
                closest = price;

        return closest;
    }




    private static void test()
    {

        int numOfRev = 1_000_000_000;
        int money = 1_426_430;

        int[] prices = new int[numOfRev];
        for (int i = 0; i < numOfRev; i++)
            prices[i] = i;

        int mostExRev = findClosestFromTheBtm(prices, money);
        System.out.println(mostExRev);
    }
}
