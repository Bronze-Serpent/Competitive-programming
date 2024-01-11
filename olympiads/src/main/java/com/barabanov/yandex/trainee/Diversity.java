package com.barabanov.yandex.trainee;

import java.util.*;
import java.util.stream.Collectors;

public class Diversity
{
    // TODO: 11.01.2024 это слишком медленный алгоритм. Нужно быстрее
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        // num of A cards
        scanner.nextInt();
        // num of B cards
        scanner.nextInt();

        int numOfChanges = scanner.nextInt();
        scanner.nextLine();

        List<Integer> aStartCards = Arrays.stream(scanner.nextLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        List<Integer> bStartCards = Arrays.stream(scanner.nextLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        StringBuilder answer = new StringBuilder();
        for (int counter = 0; counter < numOfChanges; counter++)
        {
            String[] changeData = scanner.nextLine().split(" ");
            int changeType = Integer.parseInt(changeData[0]);
            String player = changeData[1];
            int typeOfCard = Integer.parseInt(changeData[2]);

            if (player.equals("A"))
            {
                if (changeType == 1)
                    aStartCards.add(typeOfCard);
                else
                    removeCardWithType(typeOfCard, aStartCards);
            }
            else
            {
                if (changeType == 1)
                    bStartCards.add(typeOfCard);
                else
                    removeCardWithType(typeOfCard, bStartCards);
            }

            answer.append(calcDiversity(new ArrayList<>(aStartCards), new ArrayList<>(bStartCards)));
            answer.append(" ");
        }

        System.out.println(answer);
    }


    public static int calcDiversity(List<Integer> aCards, List<Integer> bCards)
    {
        Iterator<Integer> aCardIterator = aCards.iterator();
        while (aCardIterator.hasNext())
        {
            Integer aCard = aCardIterator.next();
            if (removeCardWithType(aCard, bCards))
                aCardIterator.remove();
        }

        return aCards.size() + bCards.size();
    }


    public static boolean removeCardWithType(Integer type, List<Integer> cards)
    {
        Iterator<Integer> iterator = cards.iterator();
        while (iterator.hasNext())
        {
            Integer currentElem = iterator.next();
            if (currentElem.equals(type))
            {
                iterator.remove();
                return true;
            }
        }
        return false;
    }
}
