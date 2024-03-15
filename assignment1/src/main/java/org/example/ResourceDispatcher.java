    package org.example;

    /**
     * TO mimic the atomic operation of P and V on a synchronized resource with signal in the OS
     */
    public class ResourceDispatcher {
        /**
         * The threads with priority waiting for the resource
         */
        private final PriorityQueue<Thread> waitingThreads;
        /**
         * Signal: indicate the availability of a resource
         *
         * @If its value is positive, it indicates how many thread can apply for the resource
         * @Otherwise it indicates how many threads are waiting for the resource
         */
        private int signal;

        public ResourceDispatcher(int s){
            this.signal=s;
            this.waitingThreads=new PriorityQueue<>();
        }

        /**
         * P: represent the request from a thread to be allocated with a resource
         * @if the signal is less than 0, the thread gets into the waiting queue.
         * @Otherwise the thread gets the resource
         * @param priority the thread has priority represented by an integer
         */
        public void P(Thread thread) {
            signal--;
            if (signal < 0) {
                waitingThreads.offer(thread);
                System.out.println(thread + " is put into the waiting queue");
            }else
                System.out.println(thread + " is allocated with signal and resource");
        }

        /**
         * V: represent the release of a resource by a thread
         * @if the signal is less than 0. It means there are threads waiting for the resource. Then allocate the resource with the priority
         */
        public void V(){
            System.out.println("resource is released by a thread");
            System.out.println("Current signal: "+this.signal);
            signal++;
            if(signal<=0) {
                System.out.println(waitingThreads.poll() + " is allocated with signal and resource");
            }
        }

        public Boolean isWaiting() {
            return waitingThreads.size() != 0;
        }
        @Override
        public String toString() {
            return "Current signal num: "+signal+"\n"+
                    "Wating queue:"+ this.waitingThreads;
        }
    }
