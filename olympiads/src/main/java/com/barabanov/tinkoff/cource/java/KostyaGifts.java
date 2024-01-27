package com.barabanov.tinkoff.cource.java;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class KostyaGifts
{
    private static final String NEGATIVE_DECISION = "NO";
    private static final String POSITIVE_DECISION = "YES";

    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        int numOfGifts = Integer.parseInt(scanner.nextLine());
        if (willMakeItInTime(scanner, numOfGifts))
            System.out.print(POSITIVE_DECISION);
        else
            System.out.print(NEGATIVE_DECISION);
    }

    private static boolean willMakeItInTime(Scanner scanner, int numOfGifts)
    {
        Set<Integer> mailIsBusy = new HashSet<>();
        List<Set<Integer>> daysToSendGifts = new ArrayList<>(numOfGifts);

        // составл€ю число дней дл€ отправки
        for (int giftNum = 0; giftNum < numOfGifts; giftNum++)
        {
            int[] giftInfo = Arrays.stream(scanner.nextLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            int daysToComplete = giftInfo[0] + giftInfo[1];
            int lasDayToSend = giftInfo[2];
            if (daysToComplete < lasDayToSend)
            {
                daysToSendGifts.add(IntStream.range(daysToComplete, lasDayToSend + 1)
                        .filter(day -> !mailIsBusy.contains(day))
                        .boxed()
                        .collect(Collectors.toSet()));
            }
            else
                if (daysToComplete > lasDayToSend)
                    return false;
            else
            {
                if (mailIsBusy.contains(lasDayToSend))
                    return false;
                else
                {
                    mailIsBusy.add(lasDayToSend);
                    for (Set<Integer> daySets : daysToSendGifts)
                    {
                        daySets.remove(lasDayToSend);
                        if (daySets.isEmpty())
                            return false;
                    }
                }
            }
        }

        // сортирую по возрастаю количества дней дл€ отправки
        daysToSendGifts.sort((o1, o2) -> Integer.compare(o2.size(), o1.size()));

        while (!daysToSendGifts.isEmpty())
        {
            Set<Integer> daysSet = daysToSendGifts.remove(daysToSendGifts.size() - 1);
            @SuppressWarnings("all")
            Integer anyDay = daysSet.stream()
                    .findAny()
                    .get();
            for (Set<Integer> otherDaySets : daysToSendGifts)
            {
                otherDaySets.remove(anyDay);
                if (otherDaySets.isEmpty())
                    return false;
            }
        }

        return true;
    }
}
