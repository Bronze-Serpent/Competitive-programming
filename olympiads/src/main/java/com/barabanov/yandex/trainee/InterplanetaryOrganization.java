package com.barabanov.yandex.trainee;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class InterplanetaryOrganization
{

    private static final String A_LANG = "A";
    private static final String B_LANG = "B";
    private static final String AB_LANG = "AB";

    private static final String[] employeeLanguages;
    private static final String[] organizationStructure;
    private static final int employeeQuantity;

    static {
        try (BufferedReader inputReader = new BufferedReader(new FileReader("input.txt")))
        {
            employeeQuantity = Integer.parseInt(inputReader.readLine());
            employeeLanguages = inputReader.readLine().split(" ");
            organizationStructure = inputReader.readLine().split(" ");
        } catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }


    public static void main(String[] args) throws IOException
    {
        try (FileWriter fileWriter = new FileWriter("output.txt"))
        {
            EmployeeNode bossNode = new EmployeeNode(0, 0, AB_LANG);
            int nextSubordinateIdx = 1;
            do {
                nextSubordinateIdx = createNodesFor(bossNode, nextSubordinateIdx) + 1;
            } while (nextSubordinateIdx != organizationStructure.length - 1);

            bfs(bossNode);
        }

    }


    private static int createNodesFor(EmployeeNode parentNode,
                                      int orgStrictureIdx
    )
    {
        // создание сотрудника (узла)
        int employeeNum = Integer.parseInt(organizationStructure[orgStrictureIdx]);
        EmployeeNode employeeNode = null;
        switch (parentNode.lang)
        {
            case A_LANG : {
                employeeNode = new EmployeeNode(0, parentNode.toB + 1, employeeLanguages[employeeNum - 1]);
                break;
            }
            case B_LANG : {
                employeeNode = new EmployeeNode(parentNode.toA + 1, 0, employeeLanguages[employeeNum - 1]);
                break;
            }
            case AB_LANG : {
                employeeNode = new EmployeeNode(0, 0, employeeLanguages[employeeNum - 1]);
                break;
            }
        }
        parentNode.addSubordinate(employeeNode);

        // создание его подчинённых для созданного сотрудника
        // пока номер подчинённого (не индекс в массиве структуры, а значение) не станет равно номеру самого руководителя
        int subordinateIdx = orgStrictureIdx + 1;
        for (; Integer.parseInt(organizationStructure[subordinateIdx]) != employeeNum; subordinateIdx++)
        {
            subordinateIdx = createNodesFor(employeeNode, subordinateIdx);
        }

        return subordinateIdx;
    }

    // поиск в ширину
    // т.к. родитель только 1, мне массив visited не нужен
    public static void bfs(EmployeeNode root)
    {
        // т.к. у босса нет начальников, для него не будем вычислять языковой барьер
        Queue<EmployeeNode> queue = new LinkedList<>(root.subordinates);

        while (queue.size() > 0)
        {
            EmployeeNode currEmployee = queue.remove();
            if (currEmployee.lang.equals(A_LANG))
                System.out.println(currEmployee.toA);
            else
                System.out.println(currEmployee.toB);
//            visitor.accept(curr); делаем что-то (вычисляем количество узлов до такого же языка)
            queue.addAll(currEmployee.subordinates);
        }
    }


    private static class EmployeeNode
    {
        private final List<EmployeeNode> subordinates = new ArrayList<>();
        private final int toA;
        private final int toB;
        private final String lang;

        private EmployeeNode(int toA, int toB, String lang)
        {
            this.toA = toA;
            this.toB = toB;
            this.lang = lang;
        }

        void addSubordinate(EmployeeNode employeeNode)
        {
            subordinates.add(employeeNode);
        }
    }

    private static void howManyToException(Integer num)
    {
        System.out.println(num);
        howManyToException(++num);
    }
}
