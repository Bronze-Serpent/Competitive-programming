package com.barabanov.tinkoff.trainee;

import java.util.*;

public class RoadsBetweenCities
{

    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int m = scanner.nextInt();

        Map<Integer, List<int[]>> cityRoads = new HashMap<>();
        for (int i = 0; i < n; i ++)
            cityRoads.put(i, new ArrayList<>());

        for (int i = 0; i < m; i++)
        {
            int v = scanner.nextInt();
            int u = scanner.nextInt();
            int w = scanner.nextInt();
            v--;
            u--;
            cityRoads.get(v).add(new int[]{u, w});
            cityRoads.get(u).add(new int[]{v, w});
        }

        int initial = countForX(cityRoads, -1, n);
        int l = 0;
        int r = 1_000_000_001;

        while (l < r)
        {
            int mid = (l + r + 1) >>> 1;
            int curr = countForX(cityRoads, mid, n);
            if (initial == curr) {
                l = mid;
            }
            else {
                r = mid - 1;
            }
        }

        System.out.println(l);
    }


    private static void dfs(Map<Integer, List<int[]>> graph,
                     boolean[] visited,
                     int curr,
                     int x)
    {
        if (visited[curr])
            return;
        visited[curr] = true;

        for (int[] s : graph.get(curr))
            if (s[1] > x)
                dfs(graph, visited, s[0], x);
    }


    static int countForX(Map<Integer, List<int[]>> graph, int x, int n)
    {
       boolean[] visited = new boolean[n];
       int answer = 0;
        for (int i = 0; i < n; i++)
            if (!visited[i])
            {
                dfs(graph, visited, i, x);
                answer++;
            }

        return answer;
    }

}
