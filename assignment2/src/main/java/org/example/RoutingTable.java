package org.example;

/**
 * The routing table is a document in the routers that stores the information of how a data package can be transferred to the target address.
 * The basic element of the table consists of the target address and its corresponding next jump where the router passes the package to.
 * Due to the complexity of the network structure, the table usually stores tons of information. Also, the routers are highly demanded by the great amount of requests
 * Thus, it is suitable to use binary search tree, which don't cost extra memory and provides quick seqrch.
 */
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
            return binarySearchTree.search(new RoutingInfo(routingTarget, null)) != null ?
                    binarySearchTree.search(new RoutingInfo(routingTarget, null)).value : null;
        else
            return avl.search(new RoutingInfo(routingTarget, null)) != null ?
                    avl.search(new RoutingInfo(routingTarget, null)).value : null;
    }

    public int size() {
        if (mode == TreeType.BST)
            return binarySearchTree.size();
        else
            return avl.size();
    }

}

