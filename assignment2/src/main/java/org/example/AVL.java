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
        if (root == null) {
            return; // 如果根节点为空，直接返回
        }
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
            balance(root);
        } else {
            add(root.rightChild, value, cmp);
            updateHeight(root);
            balance(root);
        }
    }


    private void balance(AVLTreeNode<T> node) {
        int balanceFactor = getBalanceFactor(node);
        if (balanceFactor > 1) {
            if (getBalanceFactor(node.leftChild) < 0) {
                LRotation(node.leftChild);
            }
            RRotation(node);
        } else if (balanceFactor < -1) {
            if (getBalanceFactor(node.rightChild) > 0) {
                RRotation(node.rightChild);
            }
            LRotation(node);
        }
    }

    public boolean remove(T value) {
        if (root == null)
            return false;

        // 找到要删除的节点
        AVLTreeNode<T> nodeToRemove = search(root, value);
        if (nodeToRemove == null)
            return false;

        // 删除节点
        removeNode(nodeToRemove);

        return true;
    }

    private void removeNode(AVLTreeNode<T> node) {
        if (node.leftChild == null && node.rightChild == null) {
            // 如果是叶子节点，直接删除
            if (node.parent != null) {
                if (node.parent.leftChild == node)
                    node.parent.leftChild = null;
                else
                    node.parent.rightChild = null;
                updateHeight(node.parent);
                balance(node.parent);
            } else {
                root = null;
            }
        } else if (node.leftChild != null && node.rightChild != null) {
            // 如果有两个子节点
            // 找到右子树中的最小节点
            AVLTreeNode<T> successor = minimum(node.rightChild);
            // 用后继节点的值替换当前节点的值
            node.value = successor.value;
            // 删除后继节点
            removeNode(successor);
        } else {
            // 如果只有一个子节点
            AVLTreeNode<T> child = (node.leftChild != null) ? node.leftChild : node.rightChild;
            if (node.parent != null) {
                if (node.parent.leftChild == node)
                    node.parent.leftChild = child;
                else
                    node.parent.rightChild = child;
                child.parent = node.parent;
                updateHeight(node.parent);
                balance(node.parent);
            } else {
                root = child;
                child.parent = null;
            }
        }
    }


    private void balanceAfterRemoval(AVLTreeNode<T> node) {
        while (node != null) {
            balance(node);
            node = node.parent;
        }
    }



    private AVLTreeNode<T> minimum(AVLTreeNode<T> node) {
        while (node.leftChild != null)
            node = node.leftChild;
        return node;
    }

    private AVLTreeNode<T> findSuccessor(AVLTreeNode<T> node) {
        AVLTreeNode<T> current = node.rightChild;
        while (current.leftChild != null) {
            current = current.leftChild;
        }
        return current;
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

    private int getHeight(AVLTreeNode<T> avlTreeNode) {
        if (avlTreeNode == null)
            return 0;
        else return avlTreeNode.height;
    }

    private void updateHeight(AVLTreeNode<T> avlTreeNode) {
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

    private int getBalanceFactor(AVLTreeNode<T> root) {
        if (root == null) return 0;
        return getHeight(root.leftChild) - getHeight(root.rightChild);
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

    @Override
    public String toString() {
        return levelOrderTraverse().toString();
    }
}
