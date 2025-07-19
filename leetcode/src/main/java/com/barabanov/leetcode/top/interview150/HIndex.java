package com.barabanov.leetcode.top.interview150;

/**
 * 274. H-Index
 */
public class HIndex {
    public static void main(String[] args) {
        hIndex(new int[]{1, 3, 1});
    }


    public static int hIndex(int[] articlesCitations) {
        int[] citationsNumToArticleQuantity = new int[articlesCitations.length + 1];
        for (int citationsNum : articlesCitations)
            citationsNumToArticleQuantity[Math.min(citationsNum, articlesCitations.length)]++;

        int suitableArticleNum = 0;
        for (int potentialHIdx = articlesCitations.length; potentialHIdx >= 0; potentialHIdx--) {

            suitableArticleNum += citationsNumToArticleQuantity[potentialHIdx];
            if (suitableArticleNum >= potentialHIdx)
                return potentialHIdx;
        }

        return 0;
    }
}
