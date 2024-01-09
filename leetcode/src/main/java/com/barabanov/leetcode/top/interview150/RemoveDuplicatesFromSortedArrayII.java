package com.barabanov.leetcode.top.interview150;


/***
 * 80
 */
public class RemoveDuplicatesFromSortedArrayII
{
    public static void main(String[] args)
    {
        int[] nums = {1,1,1,2,2,3};
        removeDuplicates(nums);
        System.out.println();
    }


    public static int removeDuplicates(int[] nums)
    {
        int shift = 0;
        for(int idx = 0; idx < nums.length - shift; idx++)
        {
            nums[idx] = nums[idx + shift];

            int shiftAddition = 0;
            while ((idx + shift + shiftAddition + 1) < nums.length
                    && nums[idx + shift + shiftAddition + 1] == nums[idx])
            {
                shiftAddition++;
                if (idx + shift + shiftAddition == nums.length)
                    return nums.length - shift;
            }

            if (shiftAddition > 0)
                shift += shiftAddition - 1;
        }

        return nums.length - shift;
    }

}