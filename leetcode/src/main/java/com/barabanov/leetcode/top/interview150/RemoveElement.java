package com.barabanov.leetcode.top.interview150;


/***
 * 27
 * O(n). Решает всю задачу за один проход и не создавая дополнительных массивов
 */
public class RemoveElement
{
    public static void main(String[] args)
    {
        int[] nums = {4,5};
        int val = 4;

        System.out.println(removeElement(nums, val));
    }


    public static int removeElement(int[] nums, int val)
    {
        if (nums.length == 0)
            return 0;

        // initialization first elem
        int shift = 0;
        for (int num : nums)
        {
            if (num == val)
                shift++;
            else
                break;
        }

        if (shift == nums.length)
            return 0;

        nums[0] = nums[shift];

        // algorithm
        for(int idx = 1; idx < nums.length - shift; idx++)
        {
            while (nums[idx + shift] == val)
            {
                shift++;
                if (idx + shift == nums.length)
                    return nums.length - shift;
            }
            nums[idx] = nums[idx + shift];
        }

        return nums.length - shift;
    }

}