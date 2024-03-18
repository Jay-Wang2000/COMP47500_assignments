import org.example.RoutingInfo;
import org.example.RoutingTable;
import org.example.TreeType;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RoutingTableReportTest {

    public static List<RoutingInfo> generateRoutingInfoList(int count) {
        List<RoutingInfo> list = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < count; i++) {
            String targetNetwork = generateRandomIPAddress();
            String nextJump = generateRandomIPAddress();
            list.add(new RoutingInfo(targetNetwork, nextJump));
        }
        return list;
    }

    public static String generateRandomIPAddress() {
        Random rand = new Random();
        StringBuilder ipBuilder = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            ipBuilder.append(rand.nextInt(256));
            if (i < 3) {
                ipBuilder.append(".");
            }
        }
        return ipBuilder.toString();
    }

    RoutingTable bstRoutingTable;
    RoutingTable avlRoutingTable;

    @Test
    public void routeTest() {
        List<RoutingInfo> routingInfoList = generateRoutingInfoList(100000);

        bstRoutingTable = new RoutingTable(TreeType.BST);
        avlRoutingTable = new RoutingTable(TreeType.AVL);

        long startTime;
        long endTime;

        // test add

        System.out.println("Currently, there are: " + bstRoutingTable.size() + " data in BST routing table");
        System.out.println("Currently, there are: " + avlRoutingTable.size() + " data in AVL routing table");
        System.out.println("----------");

        System.out.println("Start add data into BST routing table");
        startTime = System.nanoTime();
        for (RoutingInfo info : routingInfoList) {
            bstRoutingTable.addRoute(info);
        }
        endTime = System.nanoTime();
        System.out.println("Currently, there are: " + bstRoutingTable.size() + " data in BST routing table");
        System.out.println("Spend " + (endTime - startTime) + " ns");

        System.out.println("\nStart add data into AVL routing table");
        startTime = System.nanoTime();
        for (RoutingInfo info : routingInfoList) {
            avlRoutingTable.addRoute(info);
        }
        endTime = System.nanoTime();
        System.out.println("Currently, there are: " + avlRoutingTable.size() + " data in AVL routing table");
        System.out.println("Spend " + (endTime - startTime) + " ns");
        System.out.println("----------");

        // test search

        RoutingInfo info = new RoutingInfo("43.157.39.227", "0.0.0.0");

        System.out.println("Add RoutingInfo to BST routing table: " + info);
        bstRoutingTable.addRoute(new RoutingInfo("43.157.39.227", "0.0.0.0"));
        System.out.println("Currently, there are: " + bstRoutingTable.size() + " data in BST routing table");
        startTime = System.nanoTime();
        System.out.println("Find the RoutingInfo in BST routing table: " + bstRoutingTable.search("43.157.39.227"));
        endTime = System.nanoTime();
        System.out.println("Spend " + (endTime - startTime) + " ns on search");

        System.out.println("\nAdd RoutingInfo to AVL routing table: " + info);
        avlRoutingTable.addRoute(new RoutingInfo("43.157.39.227", "0.0.0.0"));
        System.out.println("Currently, there are: " + avlRoutingTable.size() + " data in AVL routing table");
        startTime = System.nanoTime();
        System.out.println("Find the RoutingInfo in AVL routing table: " + avlRoutingTable.search("43.157.39.227"));
        endTime = System.nanoTime();
        System.out.println("Spend " + (endTime - startTime) + " ns on search");
        System.out.println("----------");

        // test remove
        System.out.println("Currently, there are: " + bstRoutingTable.size() + " data in BST routing table");
        startTime = System.nanoTime();
        System.out.println("Remove " + info + " from BST routing table: " + bstRoutingTable.remove(info));
        endTime = System.nanoTime();
        System.out.println("Spend " + (endTime - startTime) + " ns on remove");

        System.out.println("\nCurrently, there are: " + avlRoutingTable.size() + " data in AVL routing table");
        startTime = System.nanoTime();
        System.out.println("Remove " + info + " from AVL routing table: " + avlRoutingTable.remove(info));
        endTime = System.nanoTime();
        System.out.println("Spend " + (endTime - startTime) + " ns on remove");
    }

}
