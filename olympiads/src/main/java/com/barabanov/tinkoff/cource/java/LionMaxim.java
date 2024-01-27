package com.barabanov.tinkoff.cource.java;

import java.util.Scanner;


public class LionMaxim
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        String allText = scanner.nextLine();
        String hiddenPasswordsText = allText.replaceAll("code+\\d", "???");
        System.out.println(hiddenPasswordsText);
    }
}
