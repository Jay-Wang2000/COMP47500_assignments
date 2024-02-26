package org.example;

import java.util.Random;

public class TestDataGenerator {
    private static final Random RANDOM = new Random();

    // Generate an array of random integers within a specified range
    public static Integer[] generateRandomArray(int size, int min, int max) {
        Integer[] array = new Integer[size];
        for (int i = 0; i < size; i++) {
            array[i] = RANDOM.nextInt(max - min + 1) + min;
        }
        return array;
    }

    public static Thread[] generateRandomThreads(int size, int min, int max) {
        Thread[] array = new Thread[size];
        for (int i = 0; i < size; i++) {
            array[i] = new Thread("Thread" + i, RANDOM.nextInt(max - min + 1) + min);
        }
        return array;
    }

    // Generates an increasing array of integers of a specified size
    public static Integer[] generateSortedArray(int size) {
        Integer[] array = new Integer[size];
        for (int i = 0; i < size; i++) {
            array[i] = i;
        }
        return array;
    }

    // Generates a descending array of integers of a specified size
    public static Integer[] generateReverseSortedArray(int size) {
        Integer[] array = new Integer[size];
        for (int i = 0; i < size; i++) {
            array[i] = size - i;
        }
        return array;
    }
}
