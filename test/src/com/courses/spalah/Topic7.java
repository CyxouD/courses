package com.courses.spalah;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

/**
 * @author Ievgen Tararaka
 */
public class Topic7 {
    public static void whyCollections() {
        int[] array = {1, 2, 3, 4};
    }

    public static void main(String[] args) {
        iterableAndIterator1();
        iterableAndIterator2();
    }

    public static void iterableAndIterator1() {
        Iterable<Integer> iterable = Arrays.asList(new Integer[]{1, 2, 3});
        iterateAndRemoveSecond(iterable);
    }

    public static void iterableAndIterator2() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        Iterable<Integer> iterable = list;
        iterateAndRemoveSecond(iterable);
    }

    public static void iterateAndRemoveSecond(Iterable<Integer> iterable) {
        System.out.println("WAS=" + iterable);
        Iterator<Integer> iterator = iterable.iterator();
        while (iterator.hasNext()) {
            Integer next = iterator.next();
            System.out.println("Next=" + next);
            if (next == 2) {
                iterator.remove();
                System.out.println("Remove=" + next);
            }
        }
        System.out.println("NOW=" + iterable);
    }
}
