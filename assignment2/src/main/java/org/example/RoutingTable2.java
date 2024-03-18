package org.example;

public class RoutingTable2 {
    BinarySearchTree<RoutingInfo> binarySearchTree;
    AVL<RoutingInfo> avl;

    int mode;

    public RoutingTable2(int mode) {
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
        if (mode == 0) {
            TreeNode<RoutingInfo> node = binarySearchTree.search(new RoutingInfo(routingTarget, null));
            if (node != null) {
                return node.value;
            }
        } else {
            AVLTreeNode<RoutingInfo> node = avl.search(new RoutingInfo(routingTarget, null));
            if (node != null) {
                return node.value;
            }
        }
        return null; // 如果未找到路由信息，则返回null
    }
}
