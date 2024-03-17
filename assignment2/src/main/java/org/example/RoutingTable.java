package org.example;

public class RoutingTable {
    BinarySearchTree<RoutingInfo> binarySearchTree;

    public RoutingTable(int mode) {
        if (mode == 1)
            this.binarySearchTree = new AVL<>();
        else
            this.binarySearchTree = new BinarySearchTree<>();
    }

    public void addRoute(RoutingInfo routingInfo) {
        binarySearchTree.add(routingInfo);
    }

    public boolean remove(RoutingInfo routingInfo) {
        return binarySearchTree.remove(routingInfo);
    }

    public void search(String routingTarget) {
        binarySearchTree.search(new RoutingInfo(routingTarget, null));
    }

}