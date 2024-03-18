package org.example;

public class Main {
    public static void main(String[] args) {
        TreeNode<Integer> treeNode1 = new TreeNode<>(1);
        TreeNode<Integer> treeNode2 = new TreeNode<>(2);
        treeNode1.rightChild = treeNode2;
        treeNode2 = null;
        System.out.println(treeNode1.rightChild);

    }
}