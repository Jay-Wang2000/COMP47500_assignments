package org.example;

import java.util.Arrays;

/**
 * A customised simple priority queue implemented by queue
 * @param <E> the type of the element
 */
public class PriorityQueue<E>{
    private Object[] queue;

    private int size = 0;

    private static final int DEFAULT_CAPACITY=8;

    public PriorityQueue(){
        this.queue = new Object[DEFAULT_CAPACITY];
    }

    public int size(){return this.size;}

    public void offer(E element){
        if(size==queue.length)
            grow();
        if (size == 0)
            queue[0] = element;
        else
            siftUP(size, element);
        size++;
    }

    /**
     * insert a element to the queue
     * @param k the location of the element
     */
    public void siftUP(int k, E x) {
        Comparable<? super E> key = (Comparable<? super E>) x;
        while (k > 0) {
            int parent = (k - 1) >>> 1;
            Object e = queue[parent];
            if (key.compareTo((E) e) < 0)
                break;
            queue[k] = e;
            k = parent;
        }
        queue[k] = key;
    }

    /**
     * Delete an element from the queue
     * @param k the location of the deleted element
     */
    public void siftDown(int k, E x) {
        Comparable<? super E> key = (Comparable<? super E>) x;
        int half = size >>> 1;        // loop while a non-leaf
        while (k < half) {
            int child = (k << 1) + 1; // assume left child is least
            Object c = queue[child];
            int right = child + 1;
            if (right < size &&
                    ((Comparable<? super E>) c).compareTo((E) queue[right]) <= 0)
                c = queue[child = right];
            if (key.compareTo((E) c) > 0)
                break;
            queue[k] = c;
            k = child;
        }
        queue[k] = key;
    }

    /**
     * if the queue meets the full capacity, then grow it
     */
    private void grow() {
        int oldCapacity = queue.length;
        // Double size if small; else grow by 50%
        int newCapacity = oldCapacity + ((oldCapacity < 64) ?
                (oldCapacity + 2) :
                (oldCapacity >> 1));
        // overflow-conscious code
        queue = Arrays.copyOf(queue, newCapacity);
    }

    public E poll(){
        if(size==0)
            return null;
        E ans = (E) queue[0];
        if (size != 0)
            siftDown(0, (E) queue[size - 1]);
        size--;
        queue[size] = null;
        return ans;
    }

    public E head(){
        if(size==0)
            return null;
        return (E)queue[0];
    }

    @Override
    public String toString() {
        return "{" +
                "queue=" + Arrays.toString(queue) +
                ", size=" + size +
                '}';
    }
}
