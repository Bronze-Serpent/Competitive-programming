package com.barabanov.leetcode.top.interview150;


import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class RandomizedSet {

    public static void main(String[] args) {
        RandomizedSetRealization set = new RandomizedSetRealization();
        System.out.println(set.remove(0));
        System.out.println(set.remove(0));
        System.out.println(set.insert(0));
        System.out.println(set.getRandom());
        System.out.println(set.remove(0));
        System.out.println(set.insert(0));
    }


    static class RandomizedSetRealization {

        private final Map<Integer, Integer> valToListIdx = new HashMap<>();
        private final List<Integer> listOfVal = new ArrayList<>();

        public RandomizedSetRealization() {
        }


        public boolean insert(int val) {
            if (valToListIdx.containsKey(val))
                return false;

            listOfVal.add(val);
            valToListIdx.put(val, listOfVal.size() - 1);
            return true;
        }


        public boolean remove(int val) {
            if (!valToListIdx.containsKey(val))
                return false;

            Integer removingValIdx = valToListIdx.get(val);
            int lastElement = listOfVal.get(listOfVal.size() - 1);

            listOfVal.set(removingValIdx, lastElement);
            valToListIdx.put(lastElement, removingValIdx);

            valToListIdx.remove(val);
            listOfVal.remove(listOfVal.size() - 1);
            return true;
        }

        public int getRandom() {
            return listOfVal.get(ThreadLocalRandom.current().nextInt(listOfVal.size()));
        }
    }
}
