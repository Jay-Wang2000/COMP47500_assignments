import org.junit.Test;

/**
 * 这个类里的测试都用AVL和BinarySearchTree类来测，基础示例参见TreeTest，像上次一样，每个测试都用5个不同量级的数据去测（e.g. 10,100,1000,10000,100000）
 * **/
public class PerformanceTest {

    /**
     * 这个测试需要比较BST和AVL的add方法，像以前一样，测试插入五组不同数据量，需要随机插入数据，随机数最大值 = 数据量
     * **/
    @Test
    public void addTest() {

    }

    /**
     * 这个测试是BST和AVL的search方法的对照组，需要先随机插入数据（像上一个一样，但是不要用同一个树对象，避免干扰），然后搜索额定的数据
     * （我觉得都搜1条就可以，如果时间太短就统一搜100条或者更多）
     * 测试搜索注意不要把插入的时间计算进去，只计搜索用时
     * **/
    @Test
    public void searchTest_control_group() {

    }

    /**
     * 这个测试是BST和AVL的search方法的实验组，这里插入数据需要很讲究，也是拿五组不同的数据测试
     * 比如在第四组中，先插入5000，让5000成为root节点，然后递减插入4999 ~ 1，BST会极度左倾，AVL会自平衡，理论上AVL会比BST快，实验组也会比对照组快
     * 然后搜索额定的数据，测试搜索注意不要把插入的时间计算进去，只计搜索用时
     * **/
    @Test
    public void searchTest_experimental_group() {

    }

    /**
     * 这个测试需要比较BST和AVL的remove方法，和add方法一样测
     * **/
    @Test
    public void removeTest() {

    }
}
