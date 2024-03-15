package org.example;

public class AVL<T> {
    public void add(TreeNode<T> newNode) {
        super.add(newNode);
    }

    @Override
    public Boolean remove(TreeNode<T> treeNode) {
        return super.remove(treeNode);
    }

    private int getBalanceFactor(){
        return getRoot().height;
    }

    private void LRotation(TreeNode<T> root){
        TreeNode<T> temp = root.rightChild;
        root.rightChild=temp.leftChild;
        temp.leftChild=root;
    }

    private void RRotation(){}
}
