package com.barabanov.yandex.trainee;

import java.io.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;


public class NewHistory
{

    private static final int SECONDS_IN_A_DAY = 24 * 60 * 60;

    public static void main(String[] args) throws IOException
    {
        try (BufferedReader inputReader = new BufferedReader(new FileReader("input.txt"));
             FileWriter writer = new FileWriter("output.txt"))
        {
            String startTimeData = inputReader.readLine();
            int[] startTimeNums = Arrays.stream(startTimeData.split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            LocalDateTime startTime = LocalDateTime.of(startTimeNums[0], startTimeNums[1], startTimeNums[2], startTimeNums[3], startTimeNums[4], startTimeNums[5]);

            String endTimeData = inputReader.readLine();
            int[] endTimeNums = Arrays.stream(endTimeData.split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            LocalDateTime endTime = LocalDateTime.of(endTimeNums[0], endTimeNums[1], endTimeNums[2], endTimeNums[3], endTimeNums[4], endTimeNums[5]);

            long secWithLeap = calcDiffInSeconds(startTime, endTime);
            long secDueToLipYears = secDueToLipYears(startTime, endTime);

            long timeOfTheLizards = secWithLeap - secDueToLipYears;

            writer.append(Long.toString(timeOfTheLizards / SECONDS_IN_A_DAY));
            writer.append(" ");
            writer.append(Long.toString(timeOfTheLizards % SECONDS_IN_A_DAY));
        }
    }


    public static long calcDiffInSeconds(LocalDateTime start, LocalDateTime end)
    {
        Duration duration = Duration.between(start, end);
        return duration.getSeconds();
    }


    // TODO: 10.01.2024 только с таким решением мне придЄтс€ пройти по всем годам в промежутке.
    //  ¬озможно, этого можно избежать.
    public static long secDueToLipYears(LocalDateTime startDate, LocalDateTime endDate)
    {
        long sumSec = 0;
        int endYear = endDate.getYear();
        for (int year = startDate.getYear(); year < endYear; year++)
        {
            if (year % 400 == 0)
                sumSec += SECONDS_IN_A_DAY;
            else
            {
                if (year % 4 == 0 && year % 100 != 0)
                    sumSec += SECONDS_IN_A_DAY;
            }
        }

        if (endYear % 400 == 0
            || (endYear % 4 == 0 && endYear % 100 != 0))
        {
            if (endDate.getMonth().getValue() > 2)
                sumSec += SECONDS_IN_A_DAY;
            else
            {
                if (endDate.getMonth().getValue() == 2
                        && endDate.getDayOfMonth() == 29)
                {
                    sumSec += endDate.getSecond();
                    sumSec += endDate.getMinute() * 60;
                    sumSec += endDate.getHour() * 1200;
                }
            }
        }

        return sumSec;
    }
}
