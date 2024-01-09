package com.barabanov.leetcode.top.interview150;


/***
 * 26
 */
public class RemoveDuplicatesFromSortedArray
{
    public static void main(String[] args)
    {
        int[] nums = {0,0,1,1,1,2,2,3,3,4};
        removeDuplicates(nums);
    }


    public static int removeDuplicates(int[] nums)
    {
        int shift = 0;
        for(int idx = 0; idx < nums.length - shift; idx++)
        {
            nums[idx] = nums[idx + shift];

            while ((idx + shift + 1) < nums.length
                    && nums[idx + shift + 1] == nums[idx])
            {
                shift++;
                if (idx + shift == nums.length)
                    return nums.length - shift;
            }
        }

        return nums.length - shift;
    }

}