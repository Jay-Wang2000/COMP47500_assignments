import org.example.AVL;
import org.example.BinarySearchTree;
import org.junit.Test;

public class TreeTest {

    @Test
    public void BSTTest() {
        BinarySearchTree<Integer> binarySearchTree = new BinarySearchTree<>();
        binarySearchTree.add(new Integer[]{1, 2, 3, 5, 6, 7});
        System.out.println(binarySearchTree);
        System.out.println("Search node 1: " + binarySearchTree.search(1));
        System.out.println("Search node 8: " + binarySearchTree.search(8));
        if (!binarySearchTree.remove(4))
            System.out.println("4 doesn't exist");
        if (binarySearchTree.remove(3))
            System.out.println("remove successfully");
        System.out.println(binarySearchTree);
    }

    @Test
    public void AVLTest() {
        AVL<Integer> avl = new AVL<>();
        avl.add(new Integer[]{1, 2, 3, 4, 6, 5, 7});
        System.out.println(avl.levelOrderTraverse());
        System.out.println(avl.search(1));
        System.out.println(avl.search(2));
        System.out.println(avl.search(8) != null ? avl.search(8) : "8 doesn't exist");
        avl.remove(2);
        avl.remove(6);
        System.out.println(!avl.remove(8) ? "8 removed" : "8 doesn't exist");
        System.out.println(avl.levelOrderTraverse());
    }
}
