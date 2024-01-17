package com.barabanov.yandex.trainee;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

// В данном алгоритме хранятся только различия в картах, сами карты игроков не хранятся
public class DiversityPotentiallyFaster
{

    private static final String FIRST_PLAYER_NAME = "A";
    private static final String SECOND_PLAYER_NAME = "B";


    public static void main(String[] args) throws IOException
    {
        try (BufferedReader inputReader = new BufferedReader(new FileReader("input.txt"));
             FileWriter writer = new FileWriter("output.txt"))
        {
            String[] metaData = inputReader.readLine().split(" ");
            int numOfChanges = Integer.parseInt(metaData[2]);

            // Создание мапы, хранящей различия в картах игроков
            // num of A cards
            int currNumOfDiff = Integer.parseInt(metaData[0]);
            Map<Integer, Difference> cardTypeToDifference = Arrays.stream(inputReader.readLine().split(" "))
                    .map(Integer::parseInt)
                    .collect(Collectors.toMap(
                            val -> val,
                            val -> new Difference(FIRST_PLAYER_NAME, val),
                            (dif1, dif2) -> new Difference(FIRST_PLAYER_NAME, dif1.quantity + dif2.quantity))
                    );
            String[] bCards = inputReader.readLine().split(" ");
            for (String bCardStr : bCards)
            {
                Integer bCardType = Integer.parseInt(bCardStr);

                Difference currTypeDiff = cardTypeToDifference.get(bCardType);
                if (currTypeDiff != null)
                {
                    if (currTypeDiff.holder.equals(FIRST_PLAYER_NAME))
                    {
                        if (currTypeDiff.quantity != 1)
                            currTypeDiff.quantity--;
                        else
                            cardTypeToDifference.remove(bCardType);

                        currNumOfDiff--;
                    }
                    else
                    {
                        currTypeDiff.quantity++;
                        currNumOfDiff++;
                    }
                }
                else
                {
                    cardTypeToDifference.put(bCardType, new Difference(SECOND_PLAYER_NAME, 1));
                    currNumOfDiff++;
                }
            }

            // сам алгоритм по подсчёту различий в картах игроков
            for (int changeCounter = 0; changeCounter < numOfChanges; changeCounter++)
            {
                String[] changeData = inputReader.readLine().split(" ");
                int changeType = Integer.parseInt(changeData[0]);
                String player = changeData[1];
                int typeOfCard = Integer.parseInt(changeData[2]);

                Difference difference = cardTypeToDifference.get(typeOfCard);

                // если добавляем карту
                if (changeType == 1)
                {
                    // если такие карты уже есть в различии
                    if (difference != null)
                    {
                        // если добавляем такую карту игроку у которого таких карт больше
                        if (difference.holder.equals(player))
                        {
                            difference.quantity++;
                            currNumOfDiff++;
                        }
                        // добавляем игроку, у которого не хватает таких карт
                        else
                        {
                            if (difference.quantity != 1)
                                difference.quantity--;
                            else
                                cardTypeToDifference.remove(typeOfCard);

                            currNumOfDiff--;
                        }

                    }
                    // если таких карт нет в различии
                    else
                    {
                        cardTypeToDifference.put(typeOfCard, new Difference(player, 1));
                        currNumOfDiff++;
                    }
                }
                // если удаляем карту у игрока
                else
                {
                    // если такие карты уже есть в различии
                    if (difference != null)
                    {
                        // если удаляем такую карту у игрока, у которого таких карт больше
                        if (difference.holder.equals(player))
                        {
                            if (difference.quantity != 1)
                                difference.quantity--;
                            else
                                cardTypeToDifference.remove(typeOfCard);

                            currNumOfDiff--;
                        }
                        // если удаляем такую карту у игрока, у которого таких карт меньше
                        else
                        {
                            difference.quantity++;
                            currNumOfDiff++;
                        }
                    }
                    // если удаляем карту, которой ещё нет в различии.
                    else
                    {
                        // В этом случае данный тип карты есть у обоих игроков.
                        // Следовательно, при удалении карты у игрока А, карта игрока B попадает в различие.
                        if (player.equals(FIRST_PLAYER_NAME))
                            cardTypeToDifference.put(typeOfCard, new Difference(SECOND_PLAYER_NAME, 1));
                        else
                            cardTypeToDifference.put(typeOfCard, new Difference(FIRST_PLAYER_NAME, 1));

                        currNumOfDiff++;
                    }
                }

                writer.append(Integer.toString(currNumOfDiff));
                writer.append(" ");
            }
        }
    }

    private static class Difference
    {
        private final String holder;
        private int quantity;

        private Difference(String holder, int quantity)
        {
            this.holder = holder;
            this.quantity = quantity;
        }
    }
}
