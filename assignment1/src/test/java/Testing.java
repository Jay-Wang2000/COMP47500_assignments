import org.example.PriorityQueue;
import org.example.ResourceDispatcher;
import org.junit.Test;

public class Testing {
    @Test
    public void priorityQueueTest(){
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        priorityQueue.offer(1);
        priorityQueue.offer(2);
        priorityQueue.offer(3);
        priorityQueue.offer(4);
        while (priorityQueue.size() != 0) {
            System.out.println(priorityQueue);
            System.out.println(priorityQueue.poll());
        }
    }

    @Test
    public void resourceDispatcherTest(){
        ResourceDispatcher resourceDispatcher= new ResourceDispatcher(2);
        resourceDispatcher.P(1);
        resourceDispatcher.P(1);
        resourceDispatcher.V();
        resourceDispatcher.P(1);
        resourceDispatcher.P(2);
        resourceDispatcher.P(3);
        resourceDispatcher.P(3);
        while (resourceDispatcher.isWaiting())
            resourceDispatcher.V();
    }

}
