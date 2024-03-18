import org.example.AVL;
import org.example.AVL2;
import org.example.AvlNode2;
import org.example.BinarySearchTree;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

public class TreeTest {
    private final int[] testData = generateTestData(1000); // 生成测试数据
    private BinarySearchTree<Integer> binarySearchTree;
    private AVL<Integer> avl;

    @Before
    public void TreeSetting() {
        binarySearchTree = new BinarySearchTree<>();
        avl = new AVL<>();
        for (int value : testData) {
            binarySearchTree.add(value);
            avl.add(value);
        }
    }

    @Test
    public void BSTAddTest() {
        long startTime = System.nanoTime(); // 记录测试开始时间
        for (int value : testData) {
            binarySearchTree.add(value);
        }
        long endTime = System.nanoTime(); // 记录测试结束时间
        System.out.println("BST add time: " + (endTime - startTime) + " nanoseconds"); // 输出测试耗时
    }

    @Test
    public void AVLAddTest() {
        long startTime = System.nanoTime(); // 记录测试开始时间
        for (int value : testData) {
            avl.add(value);
        }
        long endTime = System.nanoTime(); // 记录测试结束时间
        System.out.println("AVL add time: " + (endTime - startTime) + " nanoseconds"); // 输出测试耗时
    }

    @Test
    public void BSTSearchTest() {
        long startTime = System.nanoTime(); // 记录测试开始时间
        for (int value : testData) {
            binarySearchTree.search(value);
        }
        long endTime = System.nanoTime(); // 记录测试结束时间
        System.out.println("BST search time: " + (endTime - startTime) + " nanoseconds"); // 输出测试耗时
    }

    @Test
    public void AVLSearchTest() {
        long startTime = System.nanoTime(); // 记录测试开始时间
        for (int value : testData) {
            avl.search(value);
        }
        long endTime = System.nanoTime(); // 记录测试结束时间
        System.out.println("AVL search time: " + (endTime - startTime) + " nanoseconds"); // 输出测试耗时
    }

    @Test
    public void BSTRemoveTest() {
        long startTime = System.nanoTime(); // 记录测试开始时间
        for (int value : testData) {
            binarySearchTree.remove(value);
        }
        long endTime = System.nanoTime(); // 记录测试结束时间
        System.out.println("BST remove time: " + (endTime - startTime) + " nanoseconds"); // 输出测试耗时
    }

    @Test
    public void AVLRemoveTest() {
        long startTime = System.nanoTime(); // 记录测试开始时间
        for (int value : testData) {
            avl.remove(value);
        }
        long endTime = System.nanoTime(); // 记录测试结束时间
        System.out.println("AVL remove time: " + (endTime - startTime) + " nanoseconds"); // 输出测试耗时
    }

    // 生成随机测试数据
    private int[] generateTestData(int size) {
        Random random = new Random();
        int[] data = new int[size];
        for (int i = 0; i < size; i++) {
            data[i] = random.nextInt(1000); // 生成 0 到 1000 之间的随机数
        }
        return data;
    }

    @Test
    public void AVL2Test() {
        AVL2<Integer> avl2 = new AVL2<>();
        AvlNode2<Integer> avlNode2 = avl2.insert(new Integer[]{1, 2, 3, 4, 6, 5, 7}, null);
        System.out.println(avl2.levelOrderTraverse(avlNode2));
        avl2.remove(2, avlNode2);
        avl2.remove(6, avlNode2);
        System.out.println(avl2.levelOrderTraverse(avlNode2));
    }
}
