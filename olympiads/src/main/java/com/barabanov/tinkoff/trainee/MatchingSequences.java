package com.barabanov.tinkoff.trainee;

import java.util.Arrays;
import java.util.Scanner;


public class MatchingSequences
{

    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        int seqLen = scanner.nextInt();

        int[] ourSeq = new int[seqLen];
        for (int i = 0; i < seqLen; i++)
            ourSeq[i] = scanner.nextInt();

        int[] winSeq = new int[seqLen];
        for (int i = 0; i < seqLen; i++)
            winSeq[i] = scanner.nextInt();

        System.out.println(isItPsblToMatchSeqsInOneASC(ourSeq, winSeq) ? "YES" : "NO");
    }


    private static boolean isItPsblToMatchSeqsInOneASC(int[] ourSeq, int[] winSeq)
    {
        int startMismatch = -1;
        int endMismatch = -1;
        for (int pointer = winSeq.length -1; pointer > -1; pointer--)
        {
            if (winSeq[pointer] != ourSeq[pointer])
            {
                startMismatch = pointer;

                if (endMismatch == -1)
                    endMismatch = pointer;
            }
        }
        if (startMismatch != -1)
        {
            Arrays.sort(ourSeq, startMismatch, endMismatch + 1);

            return Arrays.equals(ourSeq, startMismatch, endMismatch + 1, winSeq, startMismatch, endMismatch + 1);
        }
        else
            return true;
    }


    private static void test()
    {

        int seqLen = 100_000_000;

        int[] winSeq = new int[seqLen];
        int[] ourSeq = new int[seqLen];
        for (int i = 0; i < seqLen; i++)
        {
            winSeq[i] = i;
            ourSeq[i] = i;
        }
        ourSeq[0] = 99_999_999;
        ourSeq[99_999_999] = 0;

        long s = System.currentTimeMillis();
        System.out.println(isItPsblToMatchSeqsInOneASC(ourSeq, winSeq));
        System.out.println((System.currentTimeMillis() - s) / 1000);
    }
}
