import org.example.AVL;
import org.example.AVL2;
import org.example.AvlNode2;
import org.example.BinarySearchTree;
import org.junit.Before;
import org.junit.Test;

public class TreeTest {
    @Before
    public void TreeSetting() {

    }

    @Test
    public void BSTTest() {
        BinarySearchTree<Integer> binarySearchTree = new BinarySearchTree<>();
        binarySearchTree.add(new Integer[]{1, 2, 3, 5, 6, 7});
        System.out.println(binarySearchTree);
        System.out.println("Search node 1: " + binarySearchTree.search(1));
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
        System.out.println(avl);
        avl.remove(2);
        avl.remove(6);
        System.out.println(avl);
    }

    @Test
    public void AVL2Test() {
        AVL2<Integer> avl2 = new AVL2<>();
        AvlNode2<Integer> avlNode2 = avl2.insert(new Integer[]{1, 2, 3, 4, 6, 5, 7}, null);
        System.out.println(avl2.levelOrderTraverse(avlNode2));
        avl2.remove(2, avlNode2);
        avl2.remove(6, avlNode2);
        System.out.println(avl2.levelOrderTraverse(avlNode2));
    }
}
