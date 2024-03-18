package org.example;

public class AvlNode<T> {
    T value;
    AvlNode<T> left;
    AvlNode<T> right;

    int height;

    AvlNode(T value) {
        this(value, null, null);
    }

    public AvlNode(T value, AvlNode<T> lt, AvlNode<T> rt) {
        this.value = value;
        this.left = lt;
        this.right = rt;
        height = 0;
    }

    @Override
    public String toString() {
        return "AvlNode{" +
                "value=" + value +
                '}';
    }
}
