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
        return remove(this.root, value);
    }

    private Boolean remove(TreeNode<T> root, T value) {
        TreeNode<T> nodeToRemove = search(root, value);
        if (nodeToRemove == null)
            return false;

        TreeNode<T> parent = nodeToRemove.parent;
        //if the node doesn't is a leaf
        if (nodeToRemove.leftChild == null && nodeToRemove.rightChild == null) {
            if (parent != null) {
                if (parent.leftChild == nodeToRemove)
                    parent.leftChild = null;
                else
                    parent.rightChild = null;
            } else {
                root = null;
            }
        } else if (nodeToRemove.leftChild != null && nodeToRemove.rightChild != null) {
            // if the node has two children
            TreeNode<T> successor = minimum(nodeToRemove.rightChild);
            nodeToRemove.value = successor.value;
            remove(successor, successor.value);
        } else {
            // if the node has only one child
            TreeNode<T> child = (nodeToRemove.leftChild != null) ? nodeToRemove.leftChild : nodeToRemove.rightChild;
            if (parent != null) {
                if (parent.leftChild == nodeToRemove)
                    parent.leftChild = child;
                else
                    parent.rightChild = child;
            } else {
                root = child;
            }
            if (child != null)
                child.parent = parent;
        }

        return true;
    }

    private TreeNode<T> minimum(TreeNode<T> node) {
        while (node.leftChild != null)
            node = node.leftChild;
        return node;
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
