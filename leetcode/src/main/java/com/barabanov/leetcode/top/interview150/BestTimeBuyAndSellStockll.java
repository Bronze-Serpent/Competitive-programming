package com.barabanov.leetcode.top.interview150;

/**
 * 122
 */
public class BestTimeBuyAndSellStockll
{
    public static void main(String[] args)
    {
        int[] prices = {1,2};
        System.out.println(maxProfit1(prices));
    }


    public static int maxProfit1(int[] prices)
    {
        int purchasePrice = Integer.MAX_VALUE;
        int diffSum = 0;

        for (int currPrice : prices)
        {
            if (currPrice >= purchasePrice) {
                diffSum += currPrice - purchasePrice;
            }
            purchasePrice = currPrice;

        }
        return diffSum;
    }


}
