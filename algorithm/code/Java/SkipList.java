import java.util.Random;

public class SkipList {

    /* 跳表允许的最大层级 */
    private static final int MAX_LEVEL = 16;

    /* 随机因子 */
    private static final double P = 0.5;

    /* 带头链表 */
    private Node head = new Node(null);

    /* 当前跳表的最大层级 */
    private int maxLevel;

    private class Node {

        /* 节点存储的值 */
        Integer value;

        /* 跳表存储的节点数组 */
        Node[] down = new Node[MAX_LEVEL];

        /**
         * 构造方法
         *
         * @param value 节点值
         */
        Node (Integer value) {
            this.value = value;
        }
    }

    /**
     * 生成随机层级
     *
     * @return 返回随机的层级
     */
    private int randomLevel() {
        Random random = new Random();
        int level = 1;
        while (random.nextDouble() < P && level < MAX_LEVEL) {
            level += 1;
        }

        return level;
    }

    /**
     * 跳表添加节点
     * 平均时间复杂度 O(lgn)
     *
     * @param val 要添加节点的值
     */
    public void add(Integer val) {
        // 获取随机层级
        int level = randomLevel();
        // 生成新节点
        Node target = new Node(val);
        // 获取链表头节点
        Node node = head;
        // 用于存储需要更新的节点数组
        // 由跳表原理可知 该数组长度等于新加节点的层级
        Node[] update = new Node[level];
        // 默认需要更新的是head节点
        for (int i = 0; i < level; i++) {
            update[i] = head;
        }

        // 哪些节点需要更新？
        // 小于等于新节点层级的节点需要更新
        for (int i = level - 1; i >=0; i--) {
            // 移动对应层级的节点
            // 当节点值大于新节点值是停止移动
            while (node.down[i] !=null && node.down[i].value.compareTo(val) < 0) {
                node = node.down[i];
            }
            // 新加节点需要添加到该节点之后
            update[i] = node;
        }

        // 获取到的update数组中的节点都需要添加新节点
        for (int i = 0; i < level; i++) {
            // 新节点的对应层级的后继节点 是 当前节点的后继节点
            target.down[i] = update[i].down[i];
            // 添加到当前节点后
            update[i].down[i] = target;
        }

        // 更新最大层级
        if (maxLevel < level) {
            maxLevel = level;
        }
    }

    /**
     * 获取指定值的节点
     *
     * @param val 查询值
     * @return 查询的节点
     */
    public Node find(Integer val) {
        Node node = head;

        // 从最高层次开始找起
        for (int i = maxLevel - 1; i >= 0; i--) {
            // 当遇到大于查询值的节点
            // 就下移节点 继续移动
            while (node.down[i] != null && node.down[i].value.compareTo(val) < 0) {
                node = node.down[i];
            }
        }

        // 找到的节点必定是最底层节点
        // 判断值是否相等 因为查找是判断当节点值大于指定值时停止
        if (node.down[0] != null && node.down[0].value.compareTo(val) == 0) {
            return node.down[0];
        }

        return null;
    }

    /**
     * 移动指定节点
     *
     * @param val 需要移出的节点
     */
    public void remove(Integer val) {
        Node node = head;

        Node[] update = new Node[maxLevel];
        // 从最高层次开始找起
        for (int i = maxLevel - 1; i >= 0; i--) {
            // 当遇到大于查询值的节点
            // 就下移节点 继续移动
            while (node.down[i] != null && node.down[i].value.compareTo(val) < 0) {
                node = node.down[i];
            }
            // 每层找到的节点就是需要更新的节点
            update[i] = node;
        }

        // 更新节点
        for (int i = maxLevel - 1; i >= 0; i--) {
            // 保证节点是选择的节点
            if (update[i].down[i] != null && update[i].down[i].value.compareTo(val) == 0) {
                // 移除节点
                update[i].down[i] = update[i].down[i].down[i];
            }
        }
    }

    /**
     * 转换为字符串
     *
     * @return 返回列表值的字符串
     */
    public String toString() {

        Node node = head;
        StringBuilder str = new StringBuilder();
        while (node.down[0] != null) {
            str.append(node.down[0].value);
            str.append("->");

            node = node.down[0];
        }
        str.append("nil");
        return str.toString();
    }
}
