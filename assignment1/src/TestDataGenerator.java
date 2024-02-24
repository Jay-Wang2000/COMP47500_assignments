package main;

import java.util.Random;

public class TestDataGenerator {
    private static final Random RANDOM = new Random();

    // ����ָ����Χ�ڵ������������
    public static Integer[] generateRandomArray(int size, int min, int max) {
        Integer[] array = new Integer[size];
        for (int i = 0; i < size; i++) {
            array[i] = RANDOM.nextInt(max - min + 1) + min;
        }
        return array;
    }

    // ����ָ����С�ĵ�����������
    public static Integer[] generateSortedArray(int size) {
        Integer[] array = new Integer[size];
        for (int i = 0; i < size; i++) {
            array[i] = i;
        }
        return array;
    }

    // ����ָ����С�ĵݼ���������
    public static Integer[] generateReverseSortedArray(int size) {
        Integer[] array = new Integer[size];
        for (int i = 0; i < size; i++) {
            array[i] = size - i;
        }
        return array;
    }
}
