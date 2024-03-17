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
        TreeNode<T> parent = p;
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


    //a node's former node is the node with the largest value in the left child tree.
    //a node's following node is the node with the smallest value in the right child tree.
    public Boolean remove(T value) {
        TreeNode<T> treeNode = search(value);
        if (treeNode == null)
            return false;


        //if the node doesn't is a leaf
        if (treeNode.rightChild == null && treeNode.leftChild == null) {
            TreeNode<T> parent = treeNode.parent;
            if (parent.leftChild == treeNode)
                parent.leftChild = null;
            else
                parent.rightChild = null;
            return true;
        }

        TreeNode<T> p = treeNode;
        //in java an object cannot be deleted directly
        if (treeNode.leftChild != null) {
            p = p.leftChild;
            while (p.rightChild != null) {
                p = p.rightChild;
            }
            treeNode.value = p.value;
            p.parent.rightChild = null;
        } else {
            //if the node doesn't have a former node
            p = p.rightChild;
            while (p.leftChild != null) {
                ;
                p = p.leftChild;
            }
            treeNode.value = p.value;
            p.parent.leftChild = null;
        }
        return true;
    }

    public int getDepth() {
        return depth;
    }

    public List<T> levelOrderTraverse() {
        Queue<TreeNode<T>> queue = new LinkedList<>();
        List<T> outcome = new ArrayList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode<T> head = queue.peek();
            outcome.add(queue.poll().value);
            if (head.rightChild != null)
                queue.offer(head.rightChild);
            if (head.leftChild != null)
                queue.offer(head.leftChild);
        }
        return outcome;
    }

    @Override
    public String toString() {
        return levelOrderTraverse().toString();
    }
}
