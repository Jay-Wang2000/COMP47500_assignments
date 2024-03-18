package org.example;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class AVL<T> {

    private AvlNode<T> root;

    private static final int ALLOWED_IMBALANCE = 1;

    private int height(AvlNode<T> t) {
        return t == null ? -1 : t.height;
    }

    public void add(T[] values) {
        for (T value : values)
            root = add(value, root);
    }

    public void add(T value) {
        if (root == null) {
            root = new AvlNode<>(value);
            return;
        }
        Comparable<? super T> k = (Comparable<? super T>) value;
        add(value, this.root);
    }

    private AvlNode<T> add(T x, AvlNode<T> t) {
        if (t == null)
            return new AvlNode<>(x, null, null);

        Comparable<? super T> k = (Comparable<? super T>) x;
        int compareResult = k.compareTo(t.value);

        if (compareResult < 0)
            t.left = add(x, t.left);
        else if (compareResult > 0)
            t.right = add(x, t.right);

        return balance(t);
    }

    private AvlNode<T> balance(AvlNode<T> t) {
        if (t == null)
            return null;

        if (height(t.left) - height(t.right) > ALLOWED_IMBALANCE)
            if (height(t.left.left) >= height(t.left.right))
                t = rotateWithLeftChild(t);
            else
                t = doubleWithLeftChild(t);
        else if (height(t.right) - height(t.left) > ALLOWED_IMBALANCE)
            if (height(t.right.right) >= height(t.right.left))
                t = rotateWithRightChild(t);
            else
                t = doubleWithRightChild(t);
        t.height = Math.max(height(t.left), height(t.right)) + 1;
        return t;
    }

    private AvlNode<T> rotateWithLeftChild(AvlNode<T> k2) {
        AvlNode<T> k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        k2.height = Math.max(height(k2.left), height(k2.right)) + 1;
        k1.height = Math.max(height(k1.left), k2.height) + 1;
        return k1;
    }

    private AvlNode<T> doubleWithLeftChild(AvlNode<T> k3) {
        k3.left = rotateWithRightChild(k3.left);
        return rotateWithLeftChild(k3);
    }

    private AvlNode<T> rotateWithRightChild(AvlNode<T> k1) {
        AvlNode<T> k2 = k1.right;
        k1.right = k2.left;
        k2.left = k1;
        k1.height = Math.max(height(k1.left), height(k1.right)) + 1;
        k2.height = Math.max(height(k2.right), k1.height) + 1;
        return k2;
    }

    private AvlNode<T> doubleWithRightChild(AvlNode<T> k1) {
        k1.right = rotateWithLeftChild(k1.right);
        return rotateWithRightChild(k1);
    }

    public AvlNode<T> search(T value) {
        return search(root, value);
    }

    private AvlNode<T> search(AvlNode<T> root, T value) {
        if (root == null)
            return null;
        Comparable<? super T> v = (Comparable<? super T>) value;
        AvlNode<T> p = root;
        while (p != null) {
            int cmp = v.compareTo(p.value);
            if (cmp < 0)
                p = p.left;
            else if (cmp > 0)
                p = p.right;
            else
                return p;
        }
        return null;
    }

    public Boolean remove(T value) {
        return remove(value, this.root) != null;
    }

    private AvlNode<T> remove(T x, AvlNode<T> t) {
        if (t == null)
            return null;

        Comparable<? super T> k = (Comparable<? super T>) x;
        int compareResult = k.compareTo(t.value);
        if (compareResult < 0)
            t.left = remove(x, t.left);
        else if (compareResult > 0)
            t.right = remove(x, t.right);
        else if (t.left != null && t.right != null) {
            t.value = findMin(t.right).value;
            t.right = remove(t.value, t.right);
        } else
            t = (t.left != null) ? t.left : t.right;
        return balance(t);
    }

    private AvlNode<T> findMin(AvlNode<T> t) {
        if (t == null)
            return null;
        else if (t.left == null)
            return t;
        return findMin(t.left);
    }

    public List<T> levelOrderTraverse() {
        Queue<AvlNode<T>> queue = new LinkedList<>();
        List<T> outcome = new ArrayList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            AvlNode<T> head = queue.peek();
            outcome.add(queue.poll().value);
            if (head.left != null)
                queue.offer(head.left);
            if (head.right != null)
                queue.offer(head.right);
        }
        return outcome;
    }
}
