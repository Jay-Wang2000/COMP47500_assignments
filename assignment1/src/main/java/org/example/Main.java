package org.example;
public class Main {
    public static void main(String[] args) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        priorityQueue.offer(1);
        System.out.println(priorityQueue.toString());
        priorityQueue.offer(2);
        priorityQueue.offer(3);
        System.out.println(priorityQueue);
        System.out.println(priorityQueue.poll().toString() + priorityQueue);
    }
}