package org.example;

public class AvlNode2<T> {
    T value;
    AvlNode2<T> left;
    AvlNode2<T> right;

    int height;

    AvlNode2(T value) {
        this(value, null, null);
    }

    public AvlNode2(T value, AvlNode2<T> lt, AvlNode2<T> rt) {
        this.value = value;
        this.left = lt;
        this.right = rt;
        height = 0;
    }
}
