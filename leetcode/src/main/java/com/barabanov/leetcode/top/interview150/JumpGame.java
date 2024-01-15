package com.barabanov.leetcode.top.interview150;

/**
 * 55
 */
public class JumpGame
{
    public static void main(String[] args)
    {
        int[] arr = {2,3,1,1,4};
        System.out.println(canJump(arr));
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

        for (int idx = nums.length - 2; idx > -1; idx--)
        {
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

}
