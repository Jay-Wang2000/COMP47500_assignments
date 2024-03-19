import org.example.AVL;
import org.example.BinarySearchTree;
import org.junit.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class PerformanceTest {

    @Test
    public void avl_addTest() throws IOException {
        Random random = new Random();
        FileWriter csvWriter = new FileWriter("avl_performance_addTest.csv");

        // Write header to CSV file
        csvWriter.append("Data Size,Time (ms)\n");

        for (int repeat = 0; repeat < 100; repeat++) {
            AVL<Integer> avl = new AVL<>();
            long startTime, endTime, elapsedTime;
            int dataSize = 1000000;
            int step = 0;
            // Binary Search Tree insertion performance test
            for (int i = 0; i < dataSize; i++, step++) {
                if (step == 100) {
                    startTime = System.currentTimeMillis(); // Start timing
                    avl.add(random.nextInt(dataSize));
                    endTime = System.currentTimeMillis(); // End timing
                    elapsedTime = endTime - startTime;
                    csvWriter.append(String.valueOf(i)).append(",").append(String.valueOf(elapsedTime));
                    step = 0;
                } else
                    avl.add(random.nextInt(dataSize));
            }
            csvWriter.append("/n");
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
        for (int repeat = 0; repeat < 100; repeat++) {
            BinarySearchTree<Integer> bst = new BinarySearchTree<>();
            long startTime, endTime, elapsedTime;
            int dataSize = 1000000;
            int step = 0;
            // Binary Search Tree insertion performance test
            for (int i = 0; i < dataSize; i++, step++) {
                if (step == 100) {
                    startTime = System.currentTimeMillis(); // Start timing
                    bst.add(random.nextInt(dataSize));
                    endTime = System.currentTimeMillis(); // End timing
                    elapsedTime = endTime - startTime;
                    csvWriter.append(String.valueOf(i)).append(",").append(String.valueOf(elapsedTime));
                    step = 0;
                } else
                    bst.add(random.nextInt(dataSize));
            }
            csvWriter.append("/n");
        }
        csvWriter.flush();
        csvWriter.close();
    }

    @Test
    public void avl_searchTest() throws IOException {
        Random random = new Random();
        FileWriter csvWriter = new FileWriter("avl_performance_searchTest.csv");
        // Write header to CSV file
        csvWriter.append("Data Size,Search Count,Time (ns)\n");
        int dataSize = 1000000;
        for (int repeat = 0; repeat < 100; repeat++) {
            int step = 0;
            AVL<Integer> avl = new AVL<>();
            // Perform search operations
            // AVL search performance test
            for (int i = 0; i < dataSize; i++) {
                avl.add(random.nextInt(dataSize));
                if (step == 10) {
                    long startTime = System.nanoTime(); // Start timing
                    int searchValue = random.nextInt(i);
                    avl.search(searchValue);
                    long endTime = System.nanoTime(); // End timing
                    long elapsedTime = endTime - startTime;
                    csvWriter.append(String.valueOf(i)).append(",").append(String.valueOf(i)).append(",")
                            .append(String.valueOf(elapsedTime));
                }
                csvWriter.append("/n");
            }
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
        int dataSize = 1000000;
        for (int repeat = 0; repeat < 100; repeat++) {
            int step = 0;
            BinarySearchTree<Integer> bst = new BinarySearchTree<>();
            // Perform search operations
            // BST search performance test
            for (int i = 0; i < dataSize; i++) {
                bst.add(random.nextInt(dataSize));
                if (step == 10) {
                    long startTime = System.nanoTime(); // Start timing
                    int searchValue = random.nextInt(i);
                    bst.search(searchValue);
                    long endTime = System.nanoTime(); // End timing
                    long elapsedTime = endTime - startTime;
                    csvWriter.append(String.valueOf(i)).append(",").append(String.valueOf(i)).append(",")
                            .append(String.valueOf(elapsedTime));
                }
                csvWriter.append("/n");
            }
        }
        csvWriter.flush();
        csvWriter.close();
    }

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

    @Test
    public void bst_special_data() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        AVL<Integer> avl = new AVL<>();

        for (int i = 100000; i > 0; i--) {
            bst.add(i);
            avl.add(i);
        }

        int target = 1;

        long startTime, endTime;
        startTime = System.currentTimeMillis();
        System.out.println("BST found: " + bst.search(target));
        endTime = System.currentTimeMillis();
        System.out.println("BST spend " + (endTime - startTime) + " ns");

        startTime = System.currentTimeMillis();
        System.out.println("AVL found: " + avl.search(target));
        endTime = System.currentTimeMillis();
        System.out.println("AVL spend " + (endTime - startTime) + " ns");
    }
}


