package org.example;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class AVL<T> {

    AVLTreeNode<T> root;

    public AVL() {
        root = null;
    }

    public void add(T[] values) {
        for (T value : values)
            add(value);
    }

    public void add(T value) {
        if (root == null) {
            root = new AVLTreeNode<>(value);
            return;
        }
        Comparable<? super T> k = (Comparable<? super T>) value;
        add(this.root, value, k.compareTo(root.value) < 0 ? -1 : 1);
    }

    private void add(AVLTreeNode<T> root, T value, int direction) {
        AVLTreeNode<T> newNode = new AVLTreeNode<>(value);
        if (direction < 0 && root.leftChild == null) {
            newNode.parent = root;
            root.leftChild = newNode;
            newNode.height = 1;
            return;
        }
        if (direction > 0 && root.rightChild == null) {
            root.rightChild = newNode;
            newNode.parent = root;
            newNode.height = 1;
            return;
        }
        Comparable<? super T> k = (Comparable<? super T>) value;
        int cmp = k.compareTo(root.value);
        if (cmp < 0) {
            add(root.leftChild, value, cmp);
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
            add(root.rightChild, value, cmp);
            updateHeight(root);
            if (getBalanceFactor(root) == -2) {
                if (getBalanceFactor(root.rightChild) == -1)
                    LRotation(root);
                else if (getBalanceFactor(root.rightChild) == 1) {
                    RRotation(root.rightChild);
                    LRotation(root);
                }
            }
        }
    }

    public Boolean remove(T value) {
        return remove(this.root, value);
    }

    //a node's former node is the node with the largest value in the left child tree.
    //a node's following node is the node with the smallest value in the right child tree.
    private Boolean remove(AVLTreeNode<T> root, T value) {
        root = search(root, value);
        if (root == null)
            return false;
        AVLTreeNode<T> parent = root.parent;
        //if the node doesn't is a leaf
        if (root.leftChild == null && root.rightChild == null)
            if (parent.leftChild == root)
                parent.leftChild = null;
            else
                parent.rightChild = null;

        AVLTreeNode<T> p = root;
        //in java an object cannot be deleted directly
        if (root.leftChild != null) {
            p = p.leftChild;
            while (p.rightChild != null) {
                p = p.rightChild;
            }
            root.value = p.value;
            remove(root.leftChild, p.value);
        } else if (root.rightChild != null) {
            //if the node doesn't have a former node
            p = p.rightChild;
            while (p.leftChild != null) {
                p = p.leftChild;
            }
            root.value = p.value;
            remove(root.rightChild, p.value);
        }

        return true;
    }

    public AVLTreeNode<T> search(T value) {
        return search(root, value);
    }

    private AVLTreeNode<T> search(AVLTreeNode<T> root, T value) {
        if (root == null)
            return null;
        Comparable<? super T> v = (Comparable<? super T>) value;
        AVLTreeNode<T> p = root;
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
        if (temp.rightChild != null)
            temp.rightChild.parent = root;
        temp.rightChild = root;
        root.parent = temp;
        updateHeight(root);
        updateHeight(temp);
        if (root == this.root)
            this.root = temp;
        else
        if (root.parent.leftChild == root)
            root.parent.leftChild = temp;
        else
            root.parent.rightChild = temp;
    }

    private void LRotation(AVLTreeNode<T> root) {
        AVLTreeNode<T> temp = root.rightChild;
        root.rightChild = temp.leftChild;
        if (temp.leftChild != null)
            temp.leftChild.parent = root;
        temp.leftChild = root;
        root.parent = temp;
        updateHeight(root);
        updateHeight(temp);
        if (root == this.root)
            this.root = temp;
        else if (root.parent.leftChild == root)
            root.parent.leftChild = temp;
        else
            root.parent.rightChild = temp;
    }

    public List<T> levelOrderTraverse() {
        Queue<AVLTreeNode<T>> queue = new LinkedList<>();
        List<T> outcome = new ArrayList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            AVLTreeNode<T> head = queue.peek();
            outcome.add(queue.poll().value);
            if (head.leftChild != null)
                queue.offer(head.leftChild);
            if (head.rightChild != null)
                queue.offer(head.rightChild);
        }
        return outcome;
    }
    private int getBalanceFactor(AVLTreeNode<T> root) {
        return getHeight(root.leftChild) - getHeight(root.rightChild);
    }

    @Override
    public String toString() {
        return levelOrderTraverse().toString();
    }
}
