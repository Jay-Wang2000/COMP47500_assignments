import org.example.PriorityQueue;
import org.example.ResourceDispatcher;
import org.example.Thread;
import org.junit.Test;

public class Testing {
    @Test
    public void priorityQueueTest(){
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        System.out.println("Offered 1,2,3,4\n"+priorityQueue);
        priorityQueue.offer(1);
        priorityQueue.offer(2);
        priorityQueue.offer(3);
        priorityQueue.offer(4);
        while (priorityQueue.size() != 0) {
            System.out.println(priorityQueue.poll());
            System.out.println(priorityQueue);
        }
    }

    @Test
    public void resourceDispatcherTest(){
        ResourceDispatcher resourceDispatcher= new ResourceDispatcher(2);
        resourceDispatcher.P(new Thread("1", 1));
        resourceDispatcher.P(new Thread("2", 1));
        System.out.println(resourceDispatcher);
        resourceDispatcher.V();
        System.out.println(resourceDispatcher);
        resourceDispatcher.P(new Thread("3", 1));
        resourceDispatcher.P(new Thread("4", 2));
        resourceDispatcher.P(new Thread("5", 3));
        resourceDispatcher.P(new Thread("6", 3));
        System.out.println(resourceDispatcher);
        while (resourceDispatcher.isWaiting()) {
            resourceDispatcher.V();
        }
    }

}
