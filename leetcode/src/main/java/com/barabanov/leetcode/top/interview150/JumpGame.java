package com.barabanov.leetcode.top.interview150;

import java.util.Arrays;

/**
 * 55
 */
public class JumpGame
{
    public static void main(String[] args)
    {
        System.out.println(mySecondTryCanJump(new int[]{1, 1, 0, 1, 1}));
    }


    // leftMostGood - элемент с которого я могу дотянуться до правого конца.
    public static boolean canJump(int[] nums)
    {
        int numsLength = nums.length;
        int leftMostGood = numsLength - 1;

        for(int i = numsLength - 2; i >= 0; i--)
            if(i + nums[i] >= leftMostGood)
                leftMostGood = i;

        return leftMostGood == 0;
    }


    // помечаем 0 пути, с которых мы не можем дойти до конца.
    public static boolean canJump0(int[] nums)
    {
        if (nums.length == 1)
            return true;

        for (int idx = nums.length - 2; idx > -1; idx--) {
            int canAchieve = 0;

            int maxJumpDist = idx + nums[idx];
            if (maxJumpDist > nums.length - 2)
                canAchieve = 1;
            else
            {
                for (int toJumpIdx = idx + 1; toJumpIdx <= maxJumpDist; toJumpIdx++)
                {
                    if (nums[toJumpIdx] > 0)
                    {
                        canAchieve = 1;
                        break;
                    }
                }
            }
            nums[idx] = canAchieve;
        }

        return nums[0] == 1;
    }



    public static boolean mySecondTryCanJump(int[] nums) {
        if (nums == null || nums.length == 0)
            return false;
        if (nums.length == 1)
            return true;

        int currIdx = 0;

        while(true) {
            int bestPlace = 0;
            int nextIdx = -1;
            int jumpRange = 1;

            if (nums[currIdx] == 0)
                return false;

            for (int idx = currIdx + 1; idx <= currIdx + nums[currIdx]; idx++) {
                if (idx == nums.length - 1)
                    return true;
                if (jumpRange + nums[idx] > bestPlace) {
                    nextIdx = idx;
                    bestPlace = jumpRange + nums[idx];
                }

                jumpRange++;
            }
            if (nextIdx == -1)
                return false;

            currIdx = nextIdx;
        }
    }

}
