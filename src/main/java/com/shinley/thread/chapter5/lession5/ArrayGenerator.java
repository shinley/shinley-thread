package com.shinley.thread.chapter5.lession5;

import java.util.Random;

/**
 * Created by shinley on 17-7-28.
 */
public class ArrayGenerator {
    public int[] generateArray(int size) {
        int array[] = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(10);
        }
        return array;
    }
}