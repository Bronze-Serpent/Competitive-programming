package com.barabanov.tinkoff.trainee;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;


public class Bills
{

    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        int sum = scanner.nextInt();
        int billsQuantity = scanner.nextInt();

        int[] bills = new int[billsQuantity * 2];
        for (int i = 0, j = 0; i < billsQuantity; i++)
        {
            int bill = scanner.nextInt();
            bills[j++] = bill;
            bills[j++] = bill;
        }

        List<Integer> stolenBills = selectTermsForAmount(bills, sum);
        if (!stolenBills.isEmpty())
        {
            System.out.println(stolenBills.size());
            stolenBills.forEach(b -> System.out.print(b + " "));
        }
        else
            System.out.println(-1);
    }


    private static List<Integer> selectTermsForAmount(int[] terms, int sum)
    {
        int numOfComb = (int) Math.pow(2, terms.length);

        for(int comb = 0; comb < numOfComb; comb++)
        {
            int combSum = 0;
            List<Integer> combTerms = new ArrayList<>();

            for (int termIdx = 0, addIdx = 1; addIdx <= numOfComb; addIdx <<= 1, termIdx++)
                if ((comb & addIdx) == addIdx)
                {
                    int addTerm = terms[terms.length - 1 - termIdx];
                    combSum += addTerm;
                    combTerms.add(addTerm);
                }

            if (combSum == sum)
                return combTerms;
        }

        return Collections.emptyList();
    }
}
