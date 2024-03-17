package org.example;

public class RoutingTable {
    BinarySearchTree<RoutingInfo> binarySearchTree;
    AVL<RoutingInfo> avl;

    int mode;

    public RoutingTable(int mode) {
        this.mode = mode;
        if (mode == 1)
            this.avl = new AVL<>();
        else
            this.binarySearchTree = new BinarySearchTree<>();
    }

    public void addRoute(RoutingInfo routingInfo) {
        if (mode == 0)
            binarySearchTree.add(routingInfo);
        else
            avl.add(routingInfo);
    }

    public boolean remove(RoutingInfo routingInfo) {
        if (mode == 0)
            return binarySearchTree.remove(routingInfo);
        else
            return avl.remove(routingInfo);
    }

    public RoutingInfo search(String routingTarget) {
        if (mode == 0)
            return binarySearchTree.search(new RoutingInfo(routingTarget, null)).value;
        else
            return avl.search(new RoutingInfo(routingTarget, null)).value;
    }

}
