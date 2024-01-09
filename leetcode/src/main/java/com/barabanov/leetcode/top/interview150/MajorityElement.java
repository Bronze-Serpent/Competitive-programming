package com.barabanov.leetcode.top.interview150;


import java.util.HashMap;
import java.util.Map;

/***
 * 169
 */
public class MajorityElement
{
    public static void main(String[] args)
    {
        int[] nums = {3, 2, 3};

        System.out.println(majorityElement(nums));
    }


    public static int majorityElement(int[] nums)
    {
        Map<Integer, Integer> numAppearsStat = new HashMap<>();

        for (Integer num : nums)
            numAppearsStat.merge(num, 1, Integer::sum);

        for (Map.Entry<Integer, Integer> statEntry : numAppearsStat.entrySet())
            if (statEntry.getValue() > nums.length / 2)
                return statEntry.getKey();

        throw new RuntimeException("в массиве нет элемента, по€вл€ющегос€ с частотой большей, чем n / 2");
    }

}