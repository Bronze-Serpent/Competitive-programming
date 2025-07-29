package com.barabanov.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Дан массив точек, одна точка — массив вида [x, y].
 * Гарантируется, что все точки уникальные, а обе координаты — целые числа. Порядок точек в данном массиве произвольный.
 * Требуется определить, существует ли такая вертикальная прямая, относительно которой все точки расположены симметрично.
 *
 * new int[][]{
 *                 new int[]{0, 6},
 *                 new int[]{2, 2},
 *                 new int[]{-2, 4},
 *                 new int[]{1, 0},
 *                 new int[]{4, 6},
 *                 new int[]{6, 4},
 *                 new int[]{3, 0},
 *         }
 * Прямая x=2 - ответ для точек выше
 *
 */
public class LineReflection {

    public static void main(String[] args) {
        System.out.println(isSymmetric(new int[][]{
                new int[]{0, 6},
                new int[]{2, 2},
                new int[]{-2, 4},
                new int[]{1, 0},
                new int[]{4, 6},
                new int[]{6, 4},
                new int[]{3, 0},
        }));
    }



    private static boolean isSymmetric(int[][] points) {
        Map<Integer, Set<Integer>> yToAllX = new HashMap<>();
        int minX = points[0][0];
        int maxX = points[0][0];

        for (int[] point : points) {
            if (point[0] < minX)
                minX = point[0];
            if (point[0] > maxX)
                maxX = point[0];

            Set<Integer> xSetForY = yToAllX.computeIfAbsent(point[1], k -> new HashSet<>());
            xSetForY.add(point[0]);
        }

        int minMaxSumm = minX + maxX;

        for (Set<Integer> allXForYSet : yToAllX.values()) {
            if (minMaxSumm % 2 == 0)
                allXForYSet.remove(minMaxSumm / 2);

            for (Integer x : allXForYSet) {
                // if x > symmetricalLineX
                // x - (x - symmetricalLineX) * 2
                // x - 2 * x + 2 * symmetricalLineX
                // 2 * symmetricalLineX - x
                // minMaxSumm - x

                // if x < symmetricalLineX
                // x + (symmetricalLineX - x) * 2
                // x + 2 * symmetricalLineX - 2x
                // minMaxSumm - x
                if (!allXForYSet.contains(minMaxSumm - x))
                    return false;
            }
        }

        return true;
    }
}
