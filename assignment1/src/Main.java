package main;

public class Main {
    public static void main(String[] args) {
        // ����һ�����ȼ�����
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        
        // �������ȼ�����
        priorityQueue.offer(3);
        priorityQueue.offer(1);
        priorityQueue.offer(5);
        System.out.println("PriorityQueue: " + priorityQueue);
        System.out.println("Head element: " + priorityQueue.head()); // 1
        System.out.println("Polling element: " + priorityQueue.poll()); // 1
        System.out.println("PriorityQueue after poll: " + priorityQueue);
        System.out.println("Head element: " + priorityQueue.head()); // 3

        // ����һ����Դ������
        ResourceDispatcher dispatcher = new ResourceDispatcher(2); // ��ʼ�ź�Ϊ2

        // ������Դ������
        dispatcher.P(1);
        dispatcher.P(2);
        dispatcher.P(3); // �źŲ��㣬�߳�3����ȴ�����
        dispatcher.V(); // �ͷ���Դ���߳�1ִ��
        dispatcher.V(); // �ͷ���Դ���߳�2ִ��
    }
}
