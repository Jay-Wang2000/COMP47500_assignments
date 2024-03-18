import org.example.RoutingInfo;
import org.example.RoutingTable;
import org.example.TreeType;
import org.junit.Before;
import org.junit.Test;

public class RoutingTableTest {

    RoutingTable bstRoutingTable;
    RoutingTable avlRoutingTable;

    @Before
    public void preTest() {
        bstRoutingTable = new RoutingTable(TreeType.BST);
        avlRoutingTable = new RoutingTable(TreeType.AVL);
    }

    @Test
    public void bstRoutingTest() {
        RoutingInfo routingInfo = new RoutingInfo("", "");
        bstRoutingTable.addRoute(routingInfo);
    }

    @Test
    public void avlRoutingTest() {

    }

}
