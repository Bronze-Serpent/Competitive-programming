package com.barabanov.yandex.trainee;

import java.io.*;
import java.util.*;


public class Closeness
{
    public static void main(String[] args) throws IOException
    {
        try (BufferedReader inputReader = new BufferedReader(new FileReader("input.txt"));
             FileWriter fileWriter = new FileWriter("output.txt"))
        {
            int arrQuantity = Integer.parseInt(inputReader.readLine());
            Integer[][] arrays = new Integer[arrQuantity][];
            for (int arrayNum = 0; arrayNum < arrQuantity; arrayNum++)
            {
                // число элементов в массиве
                inputReader.readLine();
                arrays[arrayNum] = Arrays.stream(inputReader.readLine().split(" "))
                        .map(Integer::valueOf)
                        .toArray(Integer[]::new);
            }

            Map<Integer, Vertex> prefixGraph = buildAGraph(arrays);

            fileWriter.append(Integer.toString(calcCloseness(prefixGraph)));
        }
    }


    public static Map<Integer, Vertex> buildAGraph(Integer[][] arrays)
    {
        Map<Integer, Vertex> unrelatedBranches = new HashMap<>();

        for (Integer[] array : arrays)
        {
            if (array.length == 0)
                continue;

            // отдельная обработка для начальной вершины, чтобы в цикле не делать проверки
            int arrFirstElem = array[0];
            Vertex currVertex = unrelatedBranches.get(arrFirstElem);
            if (currVertex == null)
            {
                currVertex = new Vertex();

                unrelatedBranches.put(arrFirstElem, currVertex);
            }
            else
                currVertex.additionalOccurrences++;

            for (int arrIdx = 1; arrIdx < array.length - 1; arrIdx++)
            {
                Vertex nextVertex = currVertex.branches.get(array[arrIdx]);
                if (nextVertex != null)
                {
                    nextVertex.additionalOccurrences++;
                    currVertex = nextVertex;
                }
                else {
                    Vertex createdVertex = new Vertex();
                    currVertex.branches.put(array[arrIdx], createdVertex);
                    currVertex = createdVertex;
                }
            }
            // отдельная обработка для конечной вершины, чтобы в цикле не делать проверки
            Vertex nextVertex = currVertex.branches.get(array[array.length - 1]);
            if (nextVertex != null)
                nextVertex.additionalOccurrences++;

            else
            {
                Vertex createdVertex = new Vertex();
                currVertex.branches.put(array[array.length - 1], createdVertex);
            }
        }
        
        return unrelatedBranches;
    }


    // поиск в ширину
    // т.к. родитель у вершины только 1, мне массив visited не нужен
    public static int calcCloseness(Map<Integer, Vertex> prefixGraph)
    {
        Queue<Vertex> queue = new LinkedList<>(prefixGraph.values());

        int sumOfPairwisePrefix = 0;
        while (queue.size() > 0)
        {
            Vertex currVertex = queue.remove();

            sumOfPairwisePrefix += (1 + currVertex.additionalOccurrences) * currVertex.additionalOccurrences / 2;

            queue.addAll(currVertex.branches.values());
        }
        return sumOfPairwisePrefix;
    }


    private static class Vertex
    {
        private int additionalOccurrences = 0;
        private final Map<Integer, Vertex> branches = new HashMap<>();
    }
}
