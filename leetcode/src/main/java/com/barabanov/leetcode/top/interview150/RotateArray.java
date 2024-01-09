package com.barabanov.leetcode.top.interview150;


/***
 * 189
 */
public class RotateArray
{
    public static void main(String[] args)
    {
        int[] nums = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54};

        rotate(nums, 45);
        System.out.println();
    }

    public static void rotate2(int[] nums, int k)
    {
        if (k == 0
                || nums.length == 0
                || nums.length == 1)
            return;

        if (k > nums.length)
            k = k % nums.length;

        simpleSolutionRotate(nums, k);
    }


    public static void rotate(int[] nums, int k)
    {
        if (k == 0
            || nums.length == 0
            || nums.length == 1)
            return;

        if (k > nums.length)
            k = k % nums.length;

        int numOfPerm;
        int startIdxQuantity;
        int remainder = nums.length % k;
        if (remainder == 0)
        {
            numOfPerm = nums.length / k;
            startIdxQuantity = k;
        }
        else
        {
            if (remainder % 2 != 0)
            {
                numOfPerm = nums.length;
                startIdxQuantity = 1;
            }
            else
            {
                //  огда получаетс€ последовательность перезаписи индексов, в которой есть повтор€ющийс€ индекс
                // - всЄ рушитс€ и такой метод решени€ задачи невозможен.
                // ¬роде, такие последовательности образуютс€ только если остаток от делени€ - чЄтный.
                // ѕримеры последовательностей [1,2,3,4,5,6], k = 4 (последовательность: 1->5->3->1)
                // јналогично 6/4/2 сть 10/6/4 и вроде всегда, когда остаток от делени€ - чЄтный. Ќеа, не только в этом случае.
                // 54/45/9 тоже не работает (уже не провер€л почему)
                simpleSolutionRotate(nums, k);
                return;
            }
        }

        // сам алгоритм
        for (int startIdx = 0; startIdx < startIdxQuantity; startIdx++)
        {
            int whosePlaceTaken = nums[startIdx];
            int newIdx = startIdx;
            for (int counter = 0; counter < numOfPerm; counter++)
            {
                if (newIdx + k < nums.length)
                    newIdx += k;
                else
                    newIdx = (newIdx + k) - nums.length;

                int temp = nums[newIdx];
                nums[newIdx] = whosePlaceTaken;
                whosePlaceTaken = temp;
            }
        }
    }

    private static void simpleSolutionRotate(int[] nums, int k)
    {
        int[] tempArr = new int[k];
        System.arraycopy(nums, nums.length - k, tempArr, 0, k);

        for (int numOfPerm = nums.length - k, idx = nums.length - 1; numOfPerm > 0; idx--, numOfPerm--)
        {
            nums[idx] = nums[idx - k];
        }

        System.arraycopy(tempArr, 0, nums, 0, k);
    }
}