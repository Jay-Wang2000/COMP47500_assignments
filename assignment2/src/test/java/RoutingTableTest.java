import org.example.RoutingTable2;
import org.example.RoutingInfo;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

public class RoutingTableTest {
    private final String[] routingTargets = generateRoutingTargets(1000); // 生成测试路由目标
    private RoutingTable2 routingTable;

    private String[] generateRoutingTargets(int size) {
        Random random = new Random();
        String[] targets = new String[size];
        for (int i = 0; i < size; i++) {
            targets[i] = "Target" + random.nextInt(1000); // 生成随机目标
        }
        return targets;
    }

    @Before
    public void setUp() {
        routingTable = new RoutingTable2(1); // 使用 AVL 模式初始化 RoutingTable
        for (String target : routingTargets) {
            routingTable.addRoute(new RoutingInfo(target, null)); // 添加路由信息
        }
    }

    @Test
    public void addRouteTest() {
        // 测试添加路由
        long startTime = System.nanoTime();
        for (String target : routingTargets) {
            routingTable.addRoute(new RoutingInfo(target, null));
        }
        long endTime = System.nanoTime();
        System.out.println("Add route time: " + (endTime - startTime) + " nanoseconds");
    }

    @Test
    public void removeRouteTest() {
        // 测试删除路由
        long startTime = System.nanoTime();
        for (String target : routingTargets) {
            routingTable.remove(new RoutingInfo(target, null));
        }
        long endTime = System.nanoTime();
        System.out.println("Remove route time: " + (endTime - startTime) + " nanoseconds");
    }

    @Test
    public void searchRouteTest() {
        // 测试搜索路由
        long startTime = System.nanoTime();
        for (String target : routingTargets) {
            routingTable.search(target);
        }
        long endTime = System.nanoTime();
        System.out.println("Search route time: " + (endTime - startTime) + " nanoseconds");
    }
}


