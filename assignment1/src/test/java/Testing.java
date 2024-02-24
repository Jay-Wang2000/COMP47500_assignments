import org.example.PriorityQueue;
import org.example.ResourceDispatcher;
import org.junit.Test;

public class Testing {
    @Test
    public void priorityQueueTest(){
        long start = System.currentTimeMillis();
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
    }

    @Test
    public void resourceDispatcherTest(){
        long start = System.currentTimeMillis();
        ResourceDispatcher resourceDispatcher= new ResourceDispatcher(2);

    }

}
