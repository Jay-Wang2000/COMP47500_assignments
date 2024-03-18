import org.example.AVL;
import org.example.BinarySearchTree;
import org.junit.Test;

import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 这个类里的测试都用AVL和BinarySearchTree类来测，基础示例参见TreeTest，像上次一样，每个测试都用5个不同量级的数据去测（e.g. 10,100,1000,10000,100000）
 * **/
public class PerformanceTest {

    /**
     * 这个测试需要比较BST和AVL的add方法，像以前一样，测试插入五组不同数据量，需要随机插入数据，随机数最大值 = 数据量
     * **/
    @Test
    public void avl_addTest() throws IOException {
        Random random = new Random();
        FileWriter csvWriter = new FileWriter("avl_performance_addTest.csv");

        // Write header to CSV file
        csvWriter.append("Data Size,Time (ms)\n");

        for (int dataSize = 1000; dataSize <= 1000000; dataSize += 1000) {
            AVL<Integer> avl = new AVL<>();
            long startTime, endTime;

            startTime = System.currentTimeMillis(); // Start timing

            // AVL tree insertion performance test
            for (int i = 0; i < dataSize; i++) {
                avl.add(random.nextInt(dataSize));
            }

            endTime = System.currentTimeMillis(); // End timing

            long elapsedTime = endTime - startTime;
            System.out.println("AVL insert " + dataSize + " items time: " + elapsedTime + "ms");

            // Write data to CSV file
            csvWriter.append(String.valueOf(dataSize)).append(",").append(String.valueOf(elapsedTime)).append("\n");
        }

        csvWriter.flush();
        csvWriter.close();
    }

    @Test
    public void bst_addTest() throws IOException {
        Random random = new Random();
        FileWriter csvWriter = new FileWriter("bst_performance_addTest.csv");

        // Write header to CSV file
        csvWriter.append("Data Size,Time (ms)\n");

        for (int dataSize = 1000; dataSize <= 1000000; dataSize += 1000) {
            BinarySearchTree<Integer> bst = new BinarySearchTree<>();
            long startTime, endTime;

            startTime = System.currentTimeMillis(); // Start timing

            // Binary Search Tree insertion performance test
            for (int i = 0; i < dataSize; i++) {
                bst.add(random.nextInt(dataSize));
            }

            endTime = System.currentTimeMillis(); // End timing

            long elapsedTime = endTime - startTime;
            System.out.println("BST insert " + dataSize + " items time: " + elapsedTime + "ms");

            // Write data to CSV file
            csvWriter.append(String.valueOf(dataSize)).append(",").append(String.valueOf(elapsedTime)).append("\n");
        }

        csvWriter.flush();
        csvWriter.close();
    }
    /**
     * 这个测试是BST和AVL的search方法的对照组，需要先随机插入数据（像上一个一样，但是不要用同一个树对象，避免干扰），然后搜索额定的数据
     * （我觉得都搜1条就可以，如果时间太短就统一搜100条或者更多）
     * 测试搜索注意不要把插入的时间计算进去，只计搜索用时
     * **/
    @Test
    public void avl_searchTest() throws IOException {
        Random random = new Random();
        FileWriter csvWriter = new FileWriter("avl_performance_searchTest.csv");

        // Write header to CSV file
        csvWriter.append("Data Size,Search Count,Time (ns)\n");

        for (int dataSize = 1000; dataSize <= 1000000; dataSize += 1000) {
            AVL<Integer> avl = new AVL<>();
            long startTime, endTime;

            // AVL insertion for testing search
            for (int i = 0; i < dataSize; i++) {
                avl.add(random.nextInt(dataSize));
            }

            // Perform search operations
            int searchCount = dataSize / 10; // Search count is 10% of the data size

            startTime = System.nanoTime(); // Start timing

            // AVL search performance test
            for (int i = 0; i < searchCount; i++) {
                int searchValue = random.nextInt(dataSize);
                avl.search(searchValue);
            }

            endTime = System.nanoTime(); // End timing

            long elapsedTime = endTime - startTime;
            System.out.println("AVL search " + dataSize + " items time: " + elapsedTime + "ns");

            // Write data to CSV file
            csvWriter.append(String.valueOf(dataSize)).append(",").append(String.valueOf(searchCount)).append(",")
                    .append(String.valueOf(elapsedTime)).append("\n");
        }

        csvWriter.flush();
        csvWriter.close();
    }

    @Test
    public void bst_searchTest() throws IOException {
        Random random = new Random();
        FileWriter csvWriter = new FileWriter("bst_performance_searchTest.csv");

        // Write header to CSV file
        csvWriter.append("Data Size,Search Count,Time (ns)\n");

        for (int dataSize = 1000; dataSize <= 1000000; dataSize += 1000) {
            BinarySearchTree<Integer> bst = new BinarySearchTree<>();
            long startTime, endTime;

            // BST insertion for testing search
            for (int i = 0; i < dataSize; i++) {
                bst.add(random.nextInt(dataSize));
            }

            // Perform search operations
            int searchCount = dataSize / 10; // Search count is 10% of the data size

            startTime = System.nanoTime(); // Start timing

            // BST search performance test
            for (int i = 0; i < searchCount; i++) {
                int searchValue = random.nextInt(dataSize);
                bst.search(searchValue);
            }

            endTime = System.nanoTime(); // End timing

            long elapsedTime = endTime - startTime;
            System.out.println("BST search " + dataSize + " items time: " + elapsedTime + "ns");

            // Write data to CSV file
            csvWriter.append(String.valueOf(dataSize)).append(",").append(String.valueOf(searchCount)).append(",")
                    .append(String.valueOf(elapsedTime)).append("\n");
        }

        csvWriter.flush();
        csvWriter.close();
    }
    /**
     * 这个测试是BST和AVL的search方法的实验组，这里插入数据需要很讲究，也是拿五组不同的数据测试
     * 比如在第四组中，先插入5000，让5000成为root节点，然后递减插入4999 ~ 1，BST会极度左倾，AVL会自平衡，理论上AVL会比BST快，实验组也会比对照组快
     * 然后搜索额定的数据，测试搜索注意不要把插入的时间计算进去，只计搜索用时
     * **/
    @Test
    public void avl_searchTest_extra() throws IOException {
        FileWriter csvWriter = new FileWriter("avl_performance_searchTest_extra.csv");

        // Write header to CSV file
        csvWriter.append("Data Size,Search Value,Time (ns)\n");

        // Define the search values
        int[] searchValues = {50000, 5000, 500};

        Random random = new Random();

        for (int searchValue : searchValues) {
            for (int dataSize = 1000000; dataSize >= 100000; dataSize -= 10000) {
                AVL<Integer> avl = new AVL<>();
                long startTime, endTime;

                // AVL insertion for testing search
                for (int i = dataSize; i > 0; i -= 1000) {
                    avl.add(i);
                }

                // Perform search operation
                startTime = System.nanoTime(); // Start timing
                avl.search(random.nextInt(dataSize) + 1); // Pseudo-random search value
                endTime = System.nanoTime(); // End timing

                long elapsedTime = endTime - startTime;

                // Write data to CSV file
                csvWriter.append(String.valueOf(dataSize)).append(",")
                        .append(String.valueOf(searchValue)).append(",")
                        .append(String.valueOf(elapsedTime)).append("\n");
            }
        }

        csvWriter.flush();
        csvWriter.close();
    }

    /**
     * 这个测试需要比较BST和AVL的remove方法，和add方法一样测
     * **/
    @Test
    public void avl_removeTest() throws IOException {
        Random random = new Random();
        FileWriter csvWriter = new FileWriter("avl_performance_removeTest.csv");

        // Write header to CSV file
        csvWriter.append("Data Size,Time (ms)\n");

        for (int dataSize = 1000; dataSize <= 1000000; dataSize += 1000) {
            AVL<Integer> avl = new AVL<>();
            long startTime, endTime;

            // AVL tree insertion for testing removal
            for (int i = 0; i < dataSize; i++) {
                avl.add(random.nextInt(dataSize));
            }

            startTime = System.currentTimeMillis(); // Start timing

            // AVL tree removal performance test
            for (int i = 0; i < dataSize; i++) {
                avl.remove(random.nextInt(dataSize));
            }

            endTime = System.currentTimeMillis(); // End timing

            long elapsedTime = endTime - startTime;
            System.out.println("AVL remove " + dataSize + " items time: " + elapsedTime + "ms");

            // Write data to CSV file
            csvWriter.append(String.valueOf(dataSize)).append(",").append(String.valueOf(elapsedTime)).append("\n");
        }

        csvWriter.flush();
        csvWriter.close();
    }

    @Test
    public void bst_removeTest() throws IOException {
        Random random = new Random();
        FileWriter csvWriter = new FileWriter("bst_performance_removeTest.csv");

        // Write header to CSV file
        csvWriter.append("Data Size,Time (ms)\n");

        for (int dataSize = 1000; dataSize <= 1000000; dataSize += 1000) {
            BinarySearchTree<Integer> bst = new BinarySearchTree<>();
            long startTime, endTime;

            // BST insertion for testing removal
            for (int i = 0; i < dataSize; i++) {
                bst.add(random.nextInt(dataSize));
            }

            startTime = System.currentTimeMillis(); // Start timing

            // BST removal performance test
            for (int i = 0; i < dataSize; i++) {
                bst.remove(random.nextInt(dataSize));
            }

            endTime = System.currentTimeMillis(); // End timing

            long elapsedTime = endTime - startTime;
            System.out.println("BST remove " + dataSize + " items time: " + elapsedTime + "ms");

            // Write data to CSV file
            csvWriter.append(String.valueOf(dataSize)).append(",").append(String.valueOf(elapsedTime)).append("\n");
        }

        csvWriter.flush();
        csvWriter.close();
    }
}


