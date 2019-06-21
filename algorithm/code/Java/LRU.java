public class LRU implements Cache {
    private LinkedList<String, String> linkedList;


    /**
     * LRU 算法 最近最少使用淘汰算法
     */
    public LRU() {
        this.linkedList = new LinkedList<>();
    }

    /**
     * 转换字符串
     * @return
     */
    public String toString() {
        return linkedList.toString();
    }

    /**
     * 添加缓存
     *
     * @param key 缓存内容
     */
    public void put(String key, String value) {

        // 节点可能已经在链表中 尝试移动到头部
        String val = linkedList.tryMoveItemToHead(key);
        // 节点不存在或链表为空 新加节点
        if (val == null) {
            // 缓存已经满了
            if (linkedList.isFull()) {
                // 删除最近最少使用的节点 其实就是链表的尾部节点
                linkedList.removeFoot();
            }
            // 新加节点
            linkedList.add(key, value);
        }
    }

    @Override
    public String get(String key) {
        return linkedList.tryMoveItemToHead(key);
    }
}
