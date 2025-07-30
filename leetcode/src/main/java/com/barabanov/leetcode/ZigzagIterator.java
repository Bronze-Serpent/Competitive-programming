package com.barabanov.leetcode;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Given two input vectors v1 and v2, the task is to create an iterator that returns their elements in a zigzag or alternating fashion.
 * In other words, the iterator should return the first element of v1, then the first element of v2, followed by the second element of v1,
 * and so on, alternating between the two vectors.
 */
public class ZigzagIterator {

    public static void main(String[] args) {
        Iterator<Integer> zigZagIterator = zigzagIterator(new int[]{1, 3, 5}, new int[]{2, 4, 6, 7, 8, 9, 10});
        while (zigZagIterator.hasNext()) {
            System.out.print(zigZagIterator.next() + ", ");
        }
    }


    private static Iterator<Integer> zigzagIterator(int[] arr1, int[] arr2) {
        return new Iterator<>() {
            private int arr1Idx = 0;
            private int arr2Idx = 0;
            private boolean isItFirstTurn = true;


            @Override
            public boolean hasNext() {
                return arr1Idx < arr1.length || arr2Idx < arr2.length;
            }


            @Override
            public Integer next() {
                if (!hasNext())
                    throw new NoSuchElementException();

                if (isItFirstTurn) {
                    isItFirstTurn = false;

                    if (arr1Idx >= arr1.length)
                        return arr2[arr2Idx++];
                    return arr1[arr1Idx++];
                } else {
                    isItFirstTurn = true;

                    if (arr2Idx >= arr2.length)
                        return arr1[arr1Idx++];
                    return arr2[arr2Idx++];
                }
            }
        };
    }
}
