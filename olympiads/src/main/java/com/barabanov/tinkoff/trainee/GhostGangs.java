package com.barabanov.tinkoff.trainee;

import java.util.*;

public class GhostGangs
{

    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int m = scanner.nextInt();

        DisjointSets disjointSets = new DisjointSets(n);
        for (int i = 0; i < n; i++)
            disjointSets.makeSet(i);

        for (int i = 0; i < m; i++)
        {
            int type = scanner.nextInt();

            if (type == 1)
            {
                int x = scanner.nextInt() - 1;
                int y = scanner.nextInt() - 1;
                disjointSets.union_sets(x, y);
            }
            else if (type == 2)
            {
                int x = scanner.nextInt() - 1;
                int y = scanner.nextInt() - 1;

                int one = disjointSets.find_set(x);
                int two = disjointSets.find_set(y);
                System.out.println(one == two ? "YES" : "NO");
            }
            else
            {
                int x = scanner.nextInt() - 1;
                System.out.println(disjointSets.getGroupCnt(x));
            }
        }
    }


    private static class DisjointSets
    {
        private final int[] parent;
        private final int[] rank;
        private final Map<Integer, List<Integer>> parentElements;
        private final int[] groupCnt;


        DisjointSets(int setCardinality)
        {
            this.parent = new int[setCardinality];
            this.rank = new int[setCardinality];
            this.parentElements = new HashMap<>();
            this.groupCnt = new int[setCardinality];
            for (int i = 0; i < setCardinality; i++)
            {
                parentElements.put(i, new ArrayList<>());
                groupCnt[i] = 1;
            }
        }


        void makeSet(int a)
        {
            parent[a] = a;
            rank[a] = 0;
            parentElements.get(a).add(a);
        }


        int find_set(int a)
        {
            if (a == parent[a])
                return a;
            int set = find_set(parent[a]);
            parent[a] = set;

            return set;
        }


        void union_sets(int a, int b)
        {
            a = find_set(a);
            b = find_set(b);
            if (a != b)
            {
                if (rank[a] < rank[b])
                {
                    int temp = a;
                    a = b;
                    b =temp;
                }
                parent[b] = a;
                for (Integer i : parentElements.get(b))
                {
                    groupCnt[i]++;
                }
                for (Integer i : parentElements.get(a))
                {
                    groupCnt[i]++;
                }
                for (Integer i : parentElements.get(b)) {
                    parentElements.get(a).add(i);
                }
                if (rank[a] == rank[b])
                    ++rank[a];
            }
        }


        int getGroupCnt(int v)
        {
            return groupCnt[v];
        }
    }

}
