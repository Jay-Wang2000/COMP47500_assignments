package org.example;

import sun.reflect.generics.tree.Tree;

public class BinarySearchTree<T> {
    private TreeNode<T> root;
    private int depth;

    public TreeNode<T> getRoot() {
        return this.root;
    }

    public BinarySearchTree(T firstValue) {
        root = new TreeNode<>(firstValue);
        depth = 0;
    }

    public BinarySearchTree() {
        root = null;
        depth = 0;
    }

    public void add(TreeNode<T> newNode) {
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
    public Boolean remove(TreeNode<T> treeNode) {
        if (treeNode == null)
            return false;

        //if the node doesn't is a leaf
        if (treeNode.rightChild == null && treeNode.leftChild == null) {
            treeNode.value = null;
            return true;
        }

        TreeNode<T> p = treeNode;
        TreeNode<T> parent = p;
        //in java an object cannot be deleted directly
        if (treeNode.leftChild != null) {
            p = p.leftChild;
            while (p.rightChild != null) {
                parent = p;
                p = p.rightChild;
            }
            treeNode.value = p.value;
            parent.rightChild = null;
        } else {
            //if the node doesn't have a former node
            p = p.rightChild;
            while (p.leftChild != null) {
                parent = p;
                p = p.leftChild;
            }
            treeNode.value = p.value;
            p.leftChild = null;
        }
        return true;
    }

    public int getDepth() {
        return depth;
    }
}
