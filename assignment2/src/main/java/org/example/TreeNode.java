package org.example;

public class TreeNode<T> {
    T value;
    TreeNode<T> leftChild;
    TreeNode<T> rightChild;

    TreeNode<T> parent;

    public TreeNode(T data) {
        value = data;
        leftChild = null;
        rightChild = null;
        parent = null;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "value=" + value +
                ", leftChild=" + leftChild +
                ", rightChild=" + rightChild +
                '}';
    }
}
