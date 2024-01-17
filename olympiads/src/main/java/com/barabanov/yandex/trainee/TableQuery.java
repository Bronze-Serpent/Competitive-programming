package com.barabanov.yandex.trainee;


import java.io.*;
import java.util.HashMap;
import java.util.Map;


public class TableQuery
{
    private final static String BIGGER_THAN_SYM = ">";

    public static void main(String[] args) throws IOException
    {
        try (BufferedReader inputReader = new BufferedReader(new FileReader("input.txt"));
             FileWriter writer = new FileWriter("output.txt"))
        {
            // считываем данные
            String[] metaData = inputReader.readLine().split(" ");
            int numOfRows = Integer.parseInt(metaData[0]);
            int constraintQuantity = Integer.parseInt(metaData[2]);

            String[] idxToColumnName = inputReader.readLine().split(" ");

            String[] rowsAsStr = new String[numOfRows];
            for (int idx = 0; idx < numOfRows; idx++)
                rowsAsStr[idx] = inputReader.readLine();

            // создаём мапу ограничений колонок
            Map<String, ColumnConstraint> columnConstraintMap = new HashMap<>();
            for (int constraintNum = 0; constraintNum < constraintQuantity; constraintNum++)
            {
                String[] constraintMetaData = inputReader.readLine().split(" ");
                String columnName = constraintMetaData[0];
                String sym = constraintMetaData[1];
                Integer num = Integer.valueOf(constraintMetaData[2]);

                ColumnConstraint currConstraint = columnConstraintMap.get(constraintMetaData[0]);
                if (currConstraint == null)
                {
                    if (sym.equals(BIGGER_THAN_SYM))
                        columnConstraintMap.put(columnName, new ColumnConstraint(num, null));
                    else
                        columnConstraintMap.put(columnName, new ColumnConstraint(null, num));
                }
                else
                {
                    if (sym.equals(BIGGER_THAN_SYM))
                    {
                        if (currConstraint.biggerThan == null)
                            currConstraint.biggerThan = num;
                        else
                            currConstraint.biggerThan = num > currConstraint.biggerThan
                                    ? num
                                    : currConstraint.biggerThan;
                    }
                    else
                    {
                        if (currConstraint.lessThan == null)
                            currConstraint.lessThan = num;
                        else
                            currConstraint.lessThan = num < currConstraint.lessThan
                                    ? num
                                    : currConstraint.lessThan;
                    }
                }
            }

            // считаем сумму значений подходящих строк по ограничениям
            int sumOfSuitableRows = 0;
            for (String rowAsStr : rowsAsStr)
            {
                String[] rowNumbersAsStr = rowAsStr.split(" ");
                int rowSum = 0;
                for (int numIdx = 0; numIdx < rowNumbersAsStr.length; numIdx++)
                {
                    ColumnConstraint currConstraint = columnConstraintMap.get(idxToColumnName[numIdx]);
                    int num = Integer.parseInt(rowNumbersAsStr[numIdx]);

                    if ((currConstraint.biggerThan == null || num > currConstraint.biggerThan)
                            && (currConstraint.lessThan == null || num < currConstraint.lessThan))
                        rowSum += num;
                    else
                    {
                        rowSum = 0;
                        break;
                    }
                }
                sumOfSuitableRows += rowSum;
            }

            writer.append(Integer.toString(sumOfSuitableRows));
        }
    }


    private static class ColumnConstraint
    {
        private Integer biggerThan;
        private Integer lessThan;

        public ColumnConstraint(Integer biggerThan, Integer lessThan)
        {
            this.biggerThan = biggerThan;
            this.lessThan = lessThan;
        }
    }
}
