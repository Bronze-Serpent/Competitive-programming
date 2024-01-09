package com.barabanov.leetcode.top.interview150;

/**
 * 121
 */
public class BestTimeBuyAndSellStock
{
    public static void main(String[] args)
    {
        int[] prices = {1,2};
        System.out.println(maxProfit1(prices));
    }


    public static int maxProfit1(int[] prices)
    {
        int minElem = Integer.MAX_VALUE;
        int maxDiff = 0;

        for (int price : prices)
        {
            if (price < minElem)
                minElem = price;
            else {
                int currDiff = price - minElem;
                if (currDiff > maxDiff)
                    maxDiff = currDiff;
            }

        }
        return maxDiff;
    }


    // Brute force
    public static int maxProfit0(int[] prices)
    {
        int maxDiff = 0;
        for (int currIdx = 0, diff; currIdx < prices.length - 1; currIdx++)
        {
            int subtrahendIdx = currIdx + 1;
            diff = prices[subtrahendIdx] - prices[currIdx];
            maxDiff = Math.max(maxDiff, diff);

            for (int prevIdx = 0; prevIdx < currIdx; prevIdx++)
            {
                diff =  prices[subtrahendIdx] - prices[prevIdx];
                maxDiff = Math.max(maxDiff, diff);
            }
        }

        return maxDiff;
    }
}
