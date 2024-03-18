package org.example;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinarySearchTree<T> {
    private TreeNode<T> root;
    private int depth;

    public BinarySearchTree() {
        root = null;
        depth = 0;
    }

    public void add(T[] values) {
        for (T value : values)
            add(value);
    }

    public void add(T value) {
        TreeNode<T> newNode = new TreeNode<>(value);
        if (root == null) {
            root = newNode;
            return;
        }

        int step = 0; //calculate the depth of the newNode
        Comparable<? super T> v = (Comparable<? super T>) newNode.value;
        TreeNode<T> p = root;
        TreeNode<T> parent = null;
        int cmp = 0;
        while (p != null) {
            parent = p;
            cmp = v.compareTo(p.value);
            if (cmp < 0)
                p = p.leftChild;
            else if (cmp > 0)
                p = p.rightChild;
            else
                //cmp==0 means that the value has already existed
                return;
            step++;
        }
        if (cmp < 0)
            parent.leftChild = newNode;
        else
            parent.rightChild = newNode;
        newNode.parent = parent;
        if (depth < step)
            depth = step;
    }

    public TreeNode<T> search(T value) {
        return search(root, value);
    }

    private TreeNode<T> search(TreeNode<T> root, T value) {
        if (root == null)
            return null;
        Comparable<? super T> v = (Comparable<? super T>) value;
        TreeNode<T> p = root;
        while (p != null) {
            int cmp = v.compareTo(p.value);
            if (cmp < 0)
                p = p.leftChild;
            else if (cmp > 0)
                p = p.rightChild;
            else
                return p;
        }
        return null;
    }


    public Boolean remove(T value) {
        return remove(this.root, value) != null;
    }
    //a node's former node is the node with the largest value in the left child tree.
    //a node's following node is the node with the smallest value in the right child tree.
    private TreeNode<T> remove(TreeNode<T> root, T value) {
        if (root == null)
            return null;

        Comparable<? super T> v = (Comparable<? super T>) value;
        if (v.compareTo(root.value) < 0) {
            root.leftChild = remove(root.leftChild, value);
        } else if (v.compareTo(root.value) > 0) {
            root.rightChild = remove(root.rightChild, value);
        } else {
            // Case 1: No child or only one child
            if (root.leftChild == null)
                return root.rightChild;
            else if (root.rightChild == null)
                return root.leftChild;

            // Case 2: Node with two children
            root.value = findMin(root.rightChild).value;
            root.rightChild = remove(root.rightChild, root.value);
        }
        return root;
    }

    // Helper method to find the minimum value node in a subtree
    private TreeNode<T> findMin(TreeNode<T> node) {
        if (node.leftChild == null)
            return node;
        return findMin(node.leftChild);
    }

    public int getDepth() {
        return depth;
    }

    public List<T> levelOrderTraverse() {
        Queue<TreeNode<T>> queue = new LinkedList<>();
        List<T> outcome = new ArrayList<>();
        queue.offer(this.root);
        while (!queue.isEmpty()) {
            TreeNode<T> head = queue.peek();
            outcome.add(queue.poll().value);
            if (head.leftChild != null)
                queue.offer(head.leftChild);
            if (head.rightChild != null)
                queue.offer(head.rightChild);
        }
        return outcome;
    }

    @Override
    public String toString() {
        return levelOrderTraverse().toString();
    }
}
