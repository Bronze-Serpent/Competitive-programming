package com.barabanov.leetcode.top.interview150;

import java.util.*;

/**
 * 49
 */
public class GroupAnagrams {
    public static void main(String[] args) {
        System.out.println(fasterGroupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}));
    }


    public static List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null)
            return Collections.emptyList();

        Map<String, List<String>> anagramGroups = new HashMap<>();

        for (String str : strs) {
            if (str == null)
                continue;

            int[] charQuantityArr = new int[26];
            for (char ch : str.toCharArray())
                charQuantityArr[(ch) - 97]++;

            StringBuilder anagramKeySb = new StringBuilder();
            for (int charQuantity : charQuantityArr)
                anagramKeySb.append(charQuantity);

            String anagramKey = anagramKeySb.toString();
            List<String> anagramGroup = anagramGroups.get(anagramKey);
            if (anagramGroup == null) {
                List<String> anagrams = new ArrayList<>();
                anagrams.add(str);
                anagramGroups.put(anagramKey, anagrams);
            } else
                anagramGroup.add(str);
        }

        return new ArrayList<>(anagramGroups.values());
    }


    public static List<List<String>> fasterGroupAnagrams(String[] strs) {
        Map<String, List<String>> anagramGroups = new HashMap<>();

        for (String str : strs) {
            int[] charQuantityArr = new int[26];
            for (char ch : str.toCharArray())
                charQuantityArr[(ch) - 97]++;

            String anagramKey = new String(charQuantityArr, 0, 26);
            List<String> anagramGroup = anagramGroups.get(anagramKey);
            if (anagramGroup == null) {
                List<String> anagrams = new ArrayList<>();
                anagrams.add(str);
                anagramGroups.put(anagramKey, anagrams);
            } else
                anagramGroup.add(str);
        }

        return new ArrayList<>(anagramGroups.values());
    }
}
