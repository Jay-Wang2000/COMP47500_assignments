package main;

public class Main {
    public static void main(String[] args) {
        // 创建一个优先级队列
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        
        // 测试优先级队列
        priorityQueue.offer(3);
        priorityQueue.offer(1);
        priorityQueue.offer(5);
        System.out.println("PriorityQueue: " + priorityQueue);
        System.out.println("Head element: " + priorityQueue.head()); // 1
        System.out.println("Polling element: " + priorityQueue.poll()); // 1
        System.out.println("PriorityQueue after poll: " + priorityQueue);
        System.out.println("Head element: " + priorityQueue.head()); // 3

        // 创建一个资源调度器
        ResourceDispatcher dispatcher = new ResourceDispatcher(2); // 初始信号为2

        // 测试资源调度器
        dispatcher.P(1);
        dispatcher.P(2);
        dispatcher.P(3); // 信号不足，线程3进入等待队列
        dispatcher.V(); // 释放资源，线程1执行
        dispatcher.V(); // 释放资源，线程2执行
    }
}
