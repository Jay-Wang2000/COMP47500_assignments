import org.example.AVL;
import org.example.BinarySearchTree;
import org.example.TreeNode;
import org.junit.Before;
import org.junit.Test;

public class TreeTest {
    @Before
    public void TreeSetting() {

    }

    @Test
    public void BSTTest() {
        BinarySearchTree<Integer> binarySearchTree = new BinarySearchTree<>();
        binarySearchTree.add(new TreeNode<>(2));
        binarySearchTree.add(new TreeNode<>(1));
        binarySearchTree.add(new TreeNode<>(1));
        binarySearchTree.add(new TreeNode<>(3));
        System.out.println(binarySearchTree);
        System.out.println("Search node 1: " + binarySearchTree.search(1));
        if (!binarySearchTree.remove(4))
            System.out.println("4 doesn't exist");
        if (binarySearchTree.remove(1))
            System.out.println("remove successfully");
        System.out.println(binarySearchTree);
    }

    @Test
    public void AVLTest() {
        AVL<Integer> avl = new AVL<>();

    }
}
