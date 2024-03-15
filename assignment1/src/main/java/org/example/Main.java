package org.example;

public class Main {
    public static void main(String[] args) {
        long startTime;
        long endTime;

        int size = 1;
        long[][] count = new long[5][2];
        System.out.println("+index-----------------Test PriorityQueue--time----------------------+");
        for (int i = 0; i < 5; i++) {
            size *= 10;
            for (int j = 0; j < 5; j++) {
                startTime = System.nanoTime();
                long[] endTimes = testPriorityQueuePerformance(size);

                System.out.printf("|  %d execution time of put for size: %-6d is %-10d nanoseconds |%n", j + 1, size, (endTimes[0] - startTime));
                System.out.printf("|  %d execution time of poll for size: %-6d is %-10d nanoseconds|%n", j + 1, size, (endTimes[1] - endTimes[0]));
                count[i][0] += endTimes[0] - startTime;
                count[i][1] += endTimes[1] - endTimes[0];
            }
            count[i][0] /= 5;
            count[i][1] /= 5;
            System.out.printf("| null average time of offer for size: %-6d is %-10d nanoseconds |%n", size, count[i][0]);
            System.out.printf("| null average time of poll for size: %-6d is %-10d nanoseconds|%n", size, count[i][1]);
        }
        size = 1;
        for (int i = 0; i < 5; i++) {
            size *= 10;
            System.out.printf("| %-4d average time of offer for size: %-6d is %-10d nanoseconds |%n", i + 1, size, count[i][0]);
            System.out.printf("| %-4d average time of poll for size: %-6d is %-10d nanoseconds|%n", i + 1, size, count[i][1]);
        }
        System.out.println("+--------------------------------------------------------------------+");

        size = 1;
        long[] count1 = new long[5];
        for (int i = 0; i < 5; i++) {
            size *= 10;
            for (int j = 0; j < 5; j++) {
                startTime = System.nanoTime();
                testResourceDispatcherPerformance(size);
                endTime = System.nanoTime();
                count1[i] += endTime - startTime;
            }
            count1[i] /= 5;
        }

        System.out.println("+index----------Test Resource Dispatcher--time--------------+");
        size = 1;
        for (int i = 0; i < 5; i++) {
            size *= 10;
            System.out.printf("| %-4d average time for size: %-6d is %-10d nanoseconds|%n", i + 1, size, count1[i]);
        }
        System.out.println("+-------------------------------------------------------------+");
    }

    // 测试PriorityQueue的性能
    private static long[] testPriorityQueuePerformance(int size) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        // 将上面生成的大规模数据添加到优先级队列中
        Integer[] largeData = TestDataGenerator.generateRandomArray(size, 1, 1000); // 生成随机数据
        for (Integer data : largeData) {
            priorityQueue.offer(data);
        }
        long time1=System.nanoTime();
        // 不断从优先级队列中取出元素直到队列为空
        while (priorityQueue.size() > 0) {
            priorityQueue.poll();
        }
        long time2=System.nanoTime();
        long[] ans = {time1,time2};
        return ans;
    }

    // 测试ResourceDispatcher的性能
    private static void testResourceDispatcherPerformance(int size) {
        ResourceDispatcher dispatcher = new ResourceDispatcher(2); // 初始信号为2
        // 将上面生成的大规模数据中的一部分作为线程的优先级进行测试
        Thread[] largeData = TestDataGenerator.generateRandomThreads(size, 1, 100); // 生成随机数据
        int p = 0;
        while (p < largeData.length - 1) {
            dispatcher.P(largeData[p++]);
            dispatcher.P(largeData[p++]);
            dispatcher.V();
        }
        while (dispatcher.isWaiting())
            dispatcher.V();
    }
}
