package org.example;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class AVL2<T> {

    private static final int ALLOWED_IMBALANCE = 1;

    private int height( AvlNode2<T> t ) {
        return t == null ? -1 : t.height;
    }

    public AvlNode2<T> insert(T[] values, AvlNode2<T> t) {
        for (T value : values)
            t = insert(value, t);
        return t;
    }

    private AvlNode2<T> insert(T x, AvlNode2<T> t) {
        if( t == null )
            return new AvlNode2<>(x, null, null);

        Comparable<? super T> k = (Comparable<? super T>) x;
        int compareResult = k.compareTo(t.value);

        if( compareResult < 0 )
            t.left = insert( x, t.left );
        else if( compareResult > 0 )
            t.right = insert(x, t.right);

        return balance(t);
    }

    private AvlNode2<T> balance(AvlNode2<T> t) {
        if(t == null)
            return null;

        if(height(t.left) - height(t.right) > ALLOWED_IMBALANCE)
            if(height(t.left.left) >= height(t.left.right))
                t = rotateWithLeftChild(t);
            else
                t = doubleWithLeftChild(t);
        else
            if(height(t.right) - height(t.left) > ALLOWED_IMBALANCE)
                if(height(t.right.right) >= height(t.right.left))
                    t = rotateWithRightChild(t);
                else
                    t = doubleWithRightChild(t);
        t.height = Math.max(height(t.left), height(t.right)) + 1;
        return t;
    }

    private AvlNode2<T> rotateWithLeftChild(AvlNode2<T> k2) {
        AvlNode2<T> k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        k2.height = Math.max(height(k2.left), height(k2.right)) + 1;
        k1.height = Math.max(height(k1.left), k2.height) + 1;
        return k1;
    }

    private AvlNode2<T> doubleWithLeftChild(AvlNode2<T> k3) {
        k3.left = rotateWithRightChild(k3.left);
        return rotateWithLeftChild(k3);
    }

    private AvlNode2<T> rotateWithRightChild(AvlNode2<T> k1) {
        AvlNode2<T> k2 = k1.right;
        k1.right = k2.left;
        k2.left = k1;
        k1.height = Math.max(height(k1.left), height(k1.right)) + 1;
        k2.height = Math.max(height(k2.right), k1.height) + 1;
        return k2;
    }

    private AvlNode2<T> doubleWithRightChild(AvlNode2<T> k1) {
        k1.right = rotateWithLeftChild(k1.right);
        return rotateWithRightChild(k1);
    }

    private AvlNode2<T> findMin(AvlNode2<T> t) {
        if (t == null)
            return null;
        else if (t.left == null)
            return t;
        return findMin(t.left);
    }

    public AvlNode2<T> remove(T x, AvlNode2<T> t) {
        if(t == null)
            return null;

        Comparable<? super T> k = (Comparable<? super T>) x;
        int compareResult = k.compareTo(t.value);
        if(compareResult < 0)
            t.left = remove(x, t.left);
        else if(compareResult > 0)
            t.right = remove(x, t.right);
        else if(t.left != null && t.right != null) {
            t.value = findMin(t.right).value;
            t.right = remove(t.value, t.right);
        } else
            t = (t.left != null) ? t.left : t.right;
        return balance(t);
    }

    public List<T> levelOrderTraverse(AvlNode2<T> t) {
        Queue<AvlNode2<T>> queue = new LinkedList<>();
        List<T> outcome = new ArrayList<>();
        queue.offer(t);
        while (!queue.isEmpty()) {
            AvlNode2<T> head = queue.peek();
            outcome.add(queue.poll().value);
            if (head.left != null)
                queue.offer(head.left);
            if (head.right != null)
                queue.offer(head.right);
        }
        return outcome;
    }
}
