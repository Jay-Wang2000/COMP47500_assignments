package org.example;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class AVL<T> {

    private AvlNode<T> root;

    private static final int ALLOWED_IMBALANCE = 1;

    private int height(AvlNode<T> node) {
        return node == null ? -1 : node.height;
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
        add(value, this.root);
    }

    private AvlNode<T> add(T value, AvlNode<T> node) {
        if (node == null)
            return new AvlNode<>(value, null, null);

        Comparable<? super T> k = (Comparable<? super T>) value;
        int compareResult = k.compareTo(node.value);

        if (compareResult < 0)
            node.left = add(value, node.left);
        else if (compareResult > 0)
            node.right = add(value, node.right);

        return balance(node);
    }

    private AvlNode<T> balance(AvlNode<T> node) {
        if (node == null)
            return null;

        if (height(node.left) - height(node.right) > ALLOWED_IMBALANCE)
            if (height(node.left.left) >= height(node.left.right))
                node = rotateWithLeftChild(node);
            else
                node = doubleWithLeftChild(node);
        else if (height(node.right) - height(node.left) > ALLOWED_IMBALANCE)
            if (height(node.right.right) >= height(node.right.left))
                node = rotateWithRightChild(node);
            else
                node = doubleWithRightChild(node);
        node.height = Math.max(height(node.left), height(node.right)) + 1;
        return node;
    }

    private AvlNode<T> rotateWithLeftChild(AvlNode<T> n2) {
        AvlNode<T> n1 = n2.left;
        n2.left = n1.right;
        n1.right = n2;
        n2.height = Math.max(height(n2.left), height(n2.right)) + 1;
        n1.height = Math.max(height(n1.left), n2.height) + 1;
        return n1;
    }

    private AvlNode<T> doubleWithLeftChild(AvlNode<T> n3) {
        n3.left = rotateWithRightChild(n3.left);
        return rotateWithLeftChild(n3);
    }

    private AvlNode<T> rotateWithRightChild(AvlNode<T> n1) {
        AvlNode<T> n2 = n1.right;
        n1.right = n2.left;
        n2.left = n1;
        n1.height = Math.max(height(n1.left), height(n1.right)) + 1;
        n2.height = Math.max(height(n2.right), n1.height) + 1;
        return n2;
    }

    private AvlNode<T> doubleWithRightChild(AvlNode<T> n1) {
        n1.right = rotateWithLeftChild(n1.right);
        return rotateWithRightChild(n1);
    }

    public AvlNode<T> search(T value) {
        return search(root, value);
    }

    private AvlNode<T> search(AvlNode<T> node, T value) {
        if (node == null)
            return null;
        Comparable<? super T> v = (Comparable<? super T>) value;
        AvlNode<T> p = node;
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

    private AvlNode<T> remove(T value, AvlNode<T> node) {
        if (node == null)
            return null;

        Comparable<? super T> k = (Comparable<? super T>) value;
        int compareResult = k.compareTo(node.value);
        if (compareResult < 0)
            node.left = remove(value, node.left);
        else if (compareResult > 0)
            node.right = remove(value, node.right);
        else if (node.left != null && node.right != null) {
            node.value = findMin(node.right).value;
            node.right = remove(node.value, node.right);
        } else
            node = (node.left != null) ? node.left : node.right;
        return balance(node);
    }

    private AvlNode<T> findMin(AvlNode<T> node) {
        if (node == null)
            return null;
        else if (node.left == null)
            return node;
        return findMin(node.left);
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
