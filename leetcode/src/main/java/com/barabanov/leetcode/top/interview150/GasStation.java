package com.barabanov.leetcode.top.interview150;

/**
 * 134
 */
public class GasStation {
    public static void main(String[] args) {
        System.out.println(canCompleteCircuitNotMine(new int[]{1,2,3,4,5},
                new int[]{3,4,5,1,2}));
    }

    public static int canCompleteCircuit(int[] gas, int[] cost) {

        int[] gasProfit = new int[gas.length];
        for (int i = 0; i < gas.length; i++)
            gasProfit[i] = gas[i] - cost[i];

        int gasBalanceSum = 0;
        int maxSumStartIdx = -1;
        int maxSum = 0;
        int currentSum = 0;
        int currentSumStartIdx = -1;
        int gasStationIdx = 0;
        for (; gasStationIdx < gasProfit.length; gasStationIdx++) {
            if (gasProfit[gasStationIdx] >= 0) {
                if (currentSum == 0)
                    currentSumStartIdx = gasStationIdx;
                currentSum += gasProfit[gasStationIdx];
            }
            else {
                if (maxSum < currentSum) {
                    maxSum = currentSum;
                    maxSumStartIdx = currentSumStartIdx;
                }

                if (currentSum + gasProfit[gasStationIdx] > 0)
                    currentSum += gasProfit[gasStationIdx];
                else
                    currentSum = 0;
            }
            gasBalanceSum += gasProfit[gasStationIdx];
        }

        if (currentSum > 0) {
            gasStationIdx = 0;
            while (currentSum > 0 && gasStationIdx < gasProfit.length) {
                if (gasProfit[gasStationIdx] >= 0)
                    currentSum += gasProfit[gasStationIdx];
                else {
                    if (maxSum < currentSum) {
                        maxSum = currentSum;
                        maxSumStartIdx = currentSumStartIdx;
                    }

                    if (currentSum + gasProfit[gasStationIdx] > 0)
                        currentSum += gasProfit[gasStationIdx];
                    else
                        break;
                }
                gasStationIdx++;
            }
            if (maxSum < currentSum)
                maxSumStartIdx = currentSumStartIdx;
        }
        else {
            if (maxSum <= currentSum)
                maxSumStartIdx = currentSumStartIdx;
        }

        return gasBalanceSum < 0
                ? -1
                : maxSumStartIdx;
    }


    public static int canCompleteCircuitNotMine(int[] gas, int[] cost) {

        int gasBalanceSum = 0;
        int currentTank = 0;
        int startIdx = 0;
        for (int i = 0; i < gas.length; i++) {
            int gasGain = gas[i] - cost[i];

            gasBalanceSum += gasGain;
            currentTank += gasGain;

            if (currentTank < 0) {
                currentTank = 0;
                startIdx = i + 1;
            }
        }

        return gasBalanceSum >= 0
                ? startIdx
                : -1;
    }

}
