package org.example;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinarySearchTree<T> {

    /** The root node of the binary search tree. */
    private TreeNode<T> root;

    /** The maximum depth of the binary search tree. */
    private int depth;

    /**
     * Constructs an empty binary search tree.
     */
    public BinarySearchTree() {
        root = null;
        depth = 0;
    }

    /**
     * Adds an array of values to the binary search tree.
     * @param values An array of values to be added to the binary search tree.
     */
    public void add(T[] values) {
        for (T value : values)
            add(value);
    }

    /**
     * Adds a single value to the binary search tree.
     * @param value The value to be added to the binary search tree.
     */
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

    /**
     * Searches for a value in the binary search tree.
     * @param value The value to search for.
     * @return The node containing the value if found, otherwise null.
     */
    public TreeNode<T> search(T value) {
        return search(root, value);
    }

    /**
     * Recursive method to search for a value in the binary search tree.
     * @param node The root node of the subtree to search.
     * @param value The value to search for.
     * @return The node containing the value if found, otherwise null.
     */
    private TreeNode<T> search(TreeNode<T> node, T value) {
        if (node == null)
            return null;
        Comparable<? super T> v = (Comparable<? super T>) value;
        TreeNode<T> p = node;
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

    /**
     * Removes a value from the binary search tree.
     * @param value The value to remove.
     * @return True if the value was successfully removed, otherwise false.
     */
    public Boolean remove(T value) {
        return remove(root, value) != null;
    }

    /**
     * Recursive method to remove a value from the binary search tree.
     * @param node The root node of the subtree.
     * @param value The value to remove.
     * @return The root node of the updated subtree after removing the value.
     */
    private TreeNode<T> remove(TreeNode<T> node, T value) {
        if (node == null)
            return null;

        Comparable<? super T> v = (Comparable<? super T>) value;
        if (v.compareTo(node.value) < 0) {
            node.leftChild = remove(node.leftChild, value);
        } else if (v.compareTo(node.value) > 0) {
            node.rightChild = remove(node.rightChild, value);
        } else {
            // Case 1: No child or only one child
            if (node.leftChild == null)
                return node.rightChild;
            else if (node.rightChild == null)
                return node.leftChild;

            // Case 2: Node with two children
            node.value = findMin(node.rightChild).value;
            node.rightChild = remove(node.rightChild, node.value);
        }
        return node;
    }

    /**
     * Helper method to find the minimum value node in a subtree.
     * @param node The root node of the subtree.
     * @return The node with the minimum value in the subtree.
     */
    private TreeNode<T> findMin(TreeNode<T> node) {
        if (node.leftChild == null)
            return node;
        return findMin(node.leftChild);
    }

    /**
     * Performs a level order traversal of the binary search tree.
     * @return A list containing the elements of the binary search tree in level order.
     */
    public List<T> levelOrderTraverse() {
        Queue<TreeNode<T>> queue = new LinkedList<>();
        List<T> outcome = new ArrayList<>();
        queue.offer(root);
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
