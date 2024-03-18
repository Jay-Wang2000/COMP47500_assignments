import org.example.RoutingInfo;
import org.example.RoutingTable;
import org.example.TreeType;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class RoutingTableTest {

    RoutingTable bstRoutingTable;
    RoutingTable avlRoutingTable;

    List<RoutingInfo> list = List.of(
            new RoutingInfo("43.157.39.227", null),
            new RoutingInfo("43.157.39.227", "1.1.1.1"),
            new RoutingInfo("43.157.39.227", "12.12.12.12"),
            new RoutingInfo("43.157.39.227", "123.123.123.123"),
            new RoutingInfo("43.157.39.227", "44.44.44.44")
    );
    RoutingInfo willRemove = new RoutingInfo("43.157.39.227", "5.5.5.5");

    @Before
    public void preTest() {
        bstRoutingTable = new RoutingTable(TreeType.BST);
        avlRoutingTable = new RoutingTable(TreeType.AVL);
    }

    @Test
    public void bstRoutingTest() {
        for (RoutingInfo info : list) {
            bstRoutingTable.addRoute(info);
        }
        System.out.println(bstRoutingTable.search("43.157.39.227"));
        System.out.println(bstRoutingTable.search("8.8.8.8"));
        System.out.println(bstRoutingTable.remove(willRemove));
        System.out.println(bstRoutingTable.remove(new RoutingInfo("1.1.1.1", "0.0.0.0")));
    }

    @Test
    public void avlRoutingTest() {
        for (RoutingInfo info : list) {
            avlRoutingTable.addRoute(info);
        }
        System.out.println(avlRoutingTable.search("43.157.39.227"));
        System.out.println(avlRoutingTable.search("8.8.8.8"));
        System.out.println(avlRoutingTable.remove(willRemove));
        System.out.println(avlRoutingTable.remove(new RoutingInfo("1.1.1.1", "0.0.0.0")));
    }

}
