package org.example;

import java.util.Random;

public class TestDataGenerator {
    private static final Random RANDOM = new Random();

    // 生成指定范围内的随机整数数组
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

    // 生成指定大小的递增整数数组
    public static Integer[] generateSortedArray(int size) {
        Integer[] array = new Integer[size];
        for (int i = 0; i < size; i++) {
            array[i] = i;
        }
        return array;
    }

    // 生成指定大小的递减整数数组
    public static Integer[] generateReverseSortedArray(int size) {
        Integer[] array = new Integer[size];
        for (int i = 0; i < size; i++) {
            array[i] = size - i;
        }
        return array;
    }
}
