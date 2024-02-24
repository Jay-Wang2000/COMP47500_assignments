package org.example;

import java.util.Arrays;

public class PriorityQueue<E>{
    private Object[] stack;

    private int size = 0;

    private static final int DEFAULT_CAPACITY=8;

    public PriorityQueue(){
        this.stack = new Object[DEFAULT_CAPACITY];
    }

    public int size(){return this.size;}

    public void offer(E element){
        if(size==stack.length)
            grow();
        stack[size-1]=element;
        size++;
        siftUP(size);
    }

    public void siftUP(int k){
        Comparable<? super E> key = (Comparable<? super E>) stack[k-1];
        while (k > 0) {
            int parent = (k - 1) >>> 1;
            Object e = stack[parent];
            if (key.compareTo((E) e) >= 0)
                break;
            stack[k] = e;
            k = parent;
        }
        stack[k] = key;
    }
    
    public void siftDown(int k){
        Comparable<? super E> key = (Comparable<? super E>) stack[size-1];
        int half = size >>> 1;        // loop while a non-leaf
        while (k < half) {
            int child = (k << 1) + 1; // assume left child is least
            Object c = stack[child];
            int right = child + 1;
            if (right < size &&
                    ((Comparable<? super E>) c).compareTo((E) stack[right]) > 0)
                c = stack[child = right];
            if (key.compareTo((E) c) <= 0)
                break;
            stack[k] = c;
            k = child;
        }
        stack[k] = key;
    }
    private void grow() {
        int oldCapacity = stack.length;
        // Double size if small; else grow by 50%
        int newCapacity = oldCapacity + ((oldCapacity < 64) ?
                (oldCapacity + 2) :
                (oldCapacity >> 1));
        // overflow-conscious code
        stack = Arrays.copyOf(stack, newCapacity);
    }

    public E poll(){
        if(size==0)
            return null;
        size--;
        siftDown(0);
        return (E)stack[0];
    }

    public E head(){
        if(size==0)
            return null;
        return (E)stack[0];
    }
}
