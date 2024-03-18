package org.example;

public class RoutingTable {
    BinarySearchTree<RoutingInfo> binarySearchTree;
    AVL<RoutingInfo> avl;

    TreeType mode;

    public RoutingTable(TreeType mode) {
        this.mode = mode;
        if (mode == TreeType.AVL)
            this.avl = new AVL<>();
        else
            this.binarySearchTree = new BinarySearchTree<>();
    }

    public void addRoute(RoutingInfo routingInfo) {
        if (mode == TreeType.BST)
            binarySearchTree.add(routingInfo);
        else
            avl.add(routingInfo);
    }

    public boolean remove(RoutingInfo routingInfo) {
        if (mode == TreeType.BST)
            return binarySearchTree.remove(routingInfo);
        else
            return avl.remove(routingInfo);
    }

    public RoutingInfo search(String routingTarget) {
        if (mode == TreeType.BST)
            return binarySearchTree.search(new RoutingInfo(routingTarget, null)).value;
        else
            return avl.search(new RoutingInfo(routingTarget, null)).value;
    }

}

