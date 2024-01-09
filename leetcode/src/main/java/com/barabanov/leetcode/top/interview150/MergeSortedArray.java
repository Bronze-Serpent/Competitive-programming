package com.barabanov.leetcode.top.interview150;

/***
 * 88
 */
public class MergeSortedArray
{
    public static void main(String[] args)
    {
        int[] nums1 = {2,0};
        int[] nums2 = {1};
        merge(nums1, 1, nums2, 1);
    }


    public static void merge(int[] nums1, int m, int[] nums2, int n)
    {
        int[] resultArr = new int[m + n];

        for (int idx1 = 0, idx2 = 0, resultIdx = 0; ;)
        {
            if(idx1 == m)
            {
                if(idx2 == n)
                    rewriteArr(nums1, resultArr);
                else
                {
                    System.arraycopy(nums2, idx2, resultArr, resultIdx, nums2.length - idx2);
                    rewriteArr(nums1, resultArr);
                }
                return;
            }
            if (idx2 == n)
            {
                System.arraycopy(nums1, idx1, resultArr, resultIdx, m - idx1);
                rewriteArr(nums1, resultArr);
                return;
            }

            if (nums1[idx1] < nums2[idx2])
            {
                resultArr[resultIdx++] = nums1[idx1++];
            }
            else
                resultArr[resultIdx++] = nums2[idx2++];
        }
    }

    public static void rewriteArr(int[] arr, int[] data)
    {
        System.arraycopy(data, 0, arr, 0, arr.length);
    }

}