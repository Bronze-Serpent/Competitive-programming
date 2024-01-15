package com.barabanov.leetcode.top.interview150;

/**
 * 45
 */
public class JumpGamell
{
    public static void main(String[] args)
    {
        int[] arr = {2,3,1,10,1,1,1,1,1,1,1,1,1,1};
        System.out.println(jump1(arr));
    }

    public static int jump(int[] nums)
    {
        int jumpQuantity = 0;
        int pathStartIdx = nums.length - 1;
        int jumpFrom = -1;
        while (pathStartIdx != 0)
        {
            for (int idx = pathStartIdx - 1; idx >= 0; idx--)
            {
                if (nums[idx] - (pathStartIdx - idx) >= 0)
                    jumpFrom = idx;
            }
            jumpQuantity++;
            pathStartIdx = jumpFrom;
        }

        return jumpQuantity;
    }

    // O(n) - time. O(1) - space решение.
    // С позиции мы в любом случае делаем прыжок. Мы выбираем ту позицию в промежутке, которая позволит сделать самый далёкий прыжок.
    public static int jump1(int[] nums)
    {
        int jumps = 0;

        for(int i = 0, farthest = 0, end = 0; i < nums.length - 1; i++) {
            farthest = Math.max(farthest, nums[i] + i);

            if(farthest >= nums.length - 1) {
                jumps++;
                break;
            }

            if(i == end) {
                jumps++;
                end = farthest;
            }
        }
        return jumps;
    }
}
