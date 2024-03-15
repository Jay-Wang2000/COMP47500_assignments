package org.example;

public class TreeNode<T> {
    T value;
    TreeNode<T> leftChild;
    TreeNode<T> rightChild;

    public TreeNode(T data) {
        value = data;
        leftChild = null;
        rightChild = null;
    }
}
