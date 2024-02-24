package main;

import java.util.Arrays;

public class Main_test {
    public static void main(String[] args) {
        long startTime, endTime, duration;

        // 测量生成小规模数据的运行时间
        startTime = System.nanoTime();
        Integer[] smallData = TestDataGenerator.generateRandomArray(10, 1, 100);
        endTime = System.nanoTime();
        duration = endTime - startTime;
        System.out.println("Small Data Generated in " + duration + " nanoseconds");

        // 测量生成中等规模数据的运行时间
        startTime = System.nanoTime();
        Integer[] mediumData = TestDataGenerator.generateRandomArray(1000, 1, 10000);
        endTime = System.nanoTime();
        duration = endTime - startTime;
        System.out.println("Medium Data Generated in " + duration + " nanoseconds");

        // 测量生成大规模数据的运行时间
        startTime = System.nanoTime();
        Integer[] largeData = TestDataGenerator.generateRandomArray(1000000, 1, 10000000);
        endTime = System.nanoTime();
        duration = endTime - startTime;
        System.out.println("Large Data Generated in " + duration + " nanoseconds");
    }
}
