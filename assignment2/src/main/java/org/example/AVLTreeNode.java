package org.example;

public class AVLTreeNode<T> {
    T value;
    AVLTreeNode<T> leftChild;
    AVLTreeNode<T> rightChild;

    AVLTreeNode<T> parent;

    int height;

    public AVLTreeNode(TreeNode<T> treeNode) {
        value = treeNode.value;
        leftChild = null;
        rightChild = null;
        parent = null;
        height = 0;
    }

}
