package com.barabanov.yandex.trainee;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;


public class InterplanetaryOrganization_WithoutRecursion
{

    private static final String A_LANG = "A";
    private static final String B_LANG = "B";
    private static final String AB_LANG = "AB";


    public static void main(String[] args) throws IOException
    {
        try (BufferedReader inputReader = new BufferedReader(new FileReader("input.txt"));
             FileWriter fileWriter = new FileWriter("output.txt"))
        {
            // employee quantity
            inputReader.readLine();
            String[] employeeLanguages = inputReader.readLine().split(" ");
            String[] organizationStructure = inputReader.readLine().split(" ");
            
            EmployeeNode boss = new EmployeeNode(null,0, 0, AB_LANG);
            createTree(employeeLanguages, organizationStructure, boss);

            fileWriter.append(getLangBarrierForEmployee(boss));
        }

    }


    private static void createTree(String[] employeeLanguages, String[] organizationStructure, EmployeeNode boss)
    {
        Set<Integer> openingNumOfManagers = new HashSet<>();

        EmployeeNode manager = boss;
        for (int structureIdx = 1; structureIdx < organizationStructure.length - 1; structureIdx++)
        {
            int employeeNum = Integer.parseInt(organizationStructure[structureIdx]);
            if (!openingNumOfManagers.contains(employeeNum))
            {
                openingNumOfManagers.add(employeeNum);
                EmployeeNode employeeNode = null;
                switch (manager.lang)
                {
                    case A_LANG : {
                        employeeNode = new EmployeeNode(manager, 0, manager.toB + 1, employeeLanguages[employeeNum - 1]);
                        break;
                    }
                    case B_LANG : {
                        employeeNode = new EmployeeNode(manager, manager.toA + 1, 0, employeeLanguages[employeeNum - 1]);
                        break;
                    }
                    case AB_LANG : {
                        employeeNode = new EmployeeNode(manager,0, 0, employeeLanguages[employeeNum - 1]);
                        break;
                    }
                }
                manager.addSubordinate(employeeNode);
                manager = employeeNode;
            }
            else
                manager = manager.straitBoss;
        }
    }


    // поиск в ширину
    // т.к. родитель у вершины только 1, мне массив visited не нужен
    public static String getLangBarrierForEmployee(EmployeeNode bossNode)
    {
        // т.к. у босса нет начальников, для него не будем вычислять языковой барьер
        Queue<EmployeeNode> queue = new LinkedList<>(bossNode.subordinates);

        StringBuilder sb = new StringBuilder();
        while (queue.size() > 0)
        {
            EmployeeNode currEmployee = queue.remove();
            if (currEmployee.lang.equals(A_LANG))
            {
                sb.append(currEmployee.toA);
                sb.append(' ');
            }
            else
            {
                sb.append(currEmployee.toB);
                sb.append(' ');
            }
            queue.addAll(currEmployee.subordinates);
        }
        return sb.toString();
    }


    private static class EmployeeNode
    {
        private final EmployeeNode straitBoss;
        private final List<EmployeeNode> subordinates = new ArrayList<>();
        private final int toA;
        private final int toB;
        private final String lang;

        private EmployeeNode(EmployeeNode straitBoss, int toA, int toB, String lang)
        {
            this.straitBoss = straitBoss;
            this.toA = toA;
            this.toB = toB;
            this.lang = lang;
        }

        void addSubordinate(EmployeeNode employeeNode)
        {
            subordinates.add(employeeNode);
        }
    }

}
