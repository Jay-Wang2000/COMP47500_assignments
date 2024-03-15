package org.example;

public class AVL<T> extends BinarySearchTree<T> {

    AVLTreeNode<T> root;

    public AVL() {
        root = null;
    }

    @Override
    public void add(TreeNode<T> newNode) {
        add(root, new AVLTreeNode<>(newNode));
    }

    private void add(AVLTreeNode<T> root, AVLTreeNode<T> newNode) {
        if (root == null) {
            root = newNode;
            return;
        }
        Comparable<? super T> k = (Comparable<? super T>) newNode;
        if (k.compareTo(root.value) < 0) {
            add(root.leftChild, newNode);
            updateHeight(root);
            if (getBalanceFactor(root) == 2) {
                if (getBalanceFactor(root.leftChild) == 1)
                    RRotation(root);
                else if (getBalanceFactor(root.leftChild) == -1) {
                    LRotation(root.leftChild);
                    RRotation(root);
                }
            }
        } else {
            add(root.rightChild, newNode);
            updateHeight(root);
            if (getBalanceFactor(root) == -2) {
                if (getBalanceFactor(root.leftChild.rightChild) == -1)
                    LRotation(root);
                else if (getBalanceFactor(root.rightChild) == 1) {
                    RRotation(root.rightChild);
                    LRotation(root);
                }
            }
        }
    }


    private int getHeight(AVLTreeNode avlTreeNode) {
        if (avlTreeNode == null)
            return 0;
        else return avlTreeNode.height;
    }

    private void updateHeight(AVLTreeNode avlTreeNode) {
        avlTreeNode.height = Math.max(getHeight(avlTreeNode.leftChild),
                getHeight(avlTreeNode.rightChild)) + 1;
    }

    private void RRotation(AVLTreeNode<T> root) {
        AVLTreeNode<T> temp = root.leftChild;
        root.leftChild = temp.rightChild;
        temp.rightChild = root;
        updateHeight(root);
        updateHeight(temp);
        if (root.parent.leftChild == root)
            root.parent.leftChild = temp;
        else
            root.parent.rightChild = temp;
    }

    private void LRotation(AVLTreeNode<T> root) {
        AVLTreeNode<T> temp = root.rightChild;
        root.rightChild = temp.leftChild;
        temp.leftChild = root;
        updateHeight(root);
        updateHeight(temp);
        if (root.parent.leftChild == root)
            root.parent.leftChild = temp;
        else
            root.parent.rightChild = temp;
    }

    private int getBalanceFactor(AVLTreeNode<T> root) {
        return root.height;
    }

}
