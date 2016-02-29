package com.courses.spalah;

import java.util.Arrays;

/**
 * @author Ievgen Tararaka
 */
public class Topic6 {
    public static void main(String[] args) {
        // Классический случай
        int[] array = {1, 2, 3, 4, 5};
        System.out.println(Arrays.toString(reverse(array)));
    }

    public static int[] reverse(int[] array) {
        for (int i = 0; i < array.length / 2; i++) {
            int tmp = array[i];
            array[i] = array[array.length - i];
            array[array.length - i] = tmp;
        }
        return array;
    }
}
