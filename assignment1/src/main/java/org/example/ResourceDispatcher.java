package org.example;

public class ResourceDispatcher {

    private PriorityQueue<Integer> waitingThreads;
    private int signal;

    public ResourceDispatcher(int s){
        this.signal=s;
        this.waitingThreads=new PriorityQueue<>();
    }


    public void P(int priority) {
        signal--;
        if (signal < 0) {
            waitingThreads.offer(priority);
            System.out.println("Threads with priority of " + priority + "is put into the waiting queue");
        }else
            System.out.println("Threads with priority of " + priority +"is executed");
        System.out.println(this.toString());
    }

    public void V(){
        System.out.println("resource is released by a thread");
        signal++;
        if(signal<=0) {
            System.out.println("Threads with priority of " + waitingThreads.poll() + "is dispatched with signal and got executed");
        }
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        return "Current signal num: "+signal;
    }
}
