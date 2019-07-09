public class LinkedList<K extends Comparable, V>
{
    /**
     * @var int 链表长度
     */
    private int size = 0;

    /**
     * @var int 链表最大长度
     */
    private int length = 10;

    /**
     * @var Node 链表头指针
     */
    private Node head;

    private class Node {
        /**
         * @var T 链表值
         */
        K key;
        V value;

        /**
         * @var Node 链表指针，指向下一个元素
         */
        Node next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }

    public LinkedList() {
        head = new Node(null,null);
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    /**
     * 判断链表是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return size == 0 && head.next == null;
    }

    /**
     * 判断链表是否已满
     *
     * @return
     */
    public boolean isFull() {
        return size == length;
    }

    /**
     * 获取链表根节点
     *
     * @return
     */
    public Node getFoot() {
        if (isEmpty()) return null;

        Node node = head.next;
        while (node.next != null) {
            node = node.next;
        }
        return node;
    }

    public Node getHead() {
        return head;
    }

    public Node getNode(K key) {
        if (isEmpty()) {
            return null;
        }

        Node node = head.next;
        while (node.next != null) {
            if (node.next.key.equals(key)) {
                return node.next;
            }
            node = node.next;
        }
        return null;
    }

    /**
     * 往链表中添加节点
     *
     * @param key 节点值
     */
    public void add(K key, V value){
        if (isFull()) {
            return;
        }

        Node node = new Node(key, value);

        node.next = head.next;
        head.next = node;

        ++size;
    }

    /**
     * 移动指定节点到头部 其他节点瞬移一位
     *
     * @param key 节点值
     * @return
     */
    public V tryMoveItemToHead(K key) {
        // 空链表返回null
        if (isEmpty()) {
            return null;
        }

        Node node = head.next;
        while (node.next != null) {
            if (node.next.key == key) {
                // 目标节点
                Node target = node.next;
                // 移动目标节点到链表头部
                node.next = target.next;
                target.next = head.next;
                head.next = target;

                return target.value;
            }
            node = node.next;
        }
        return null;
    }

    /**
     * 删除节点
     */
    public void remove() {
        if (isEmpty()) {
            return;
        }

        head.next = head.next.next;
        --size;
    }

    /**
     * 删除指定节点
     *
     * @param key 节点值
     */
    public void remove(K key) {
        if (isEmpty()) {
            return;
        }

        Node node = head.next;
        while (node.next != null && node.next.next != null) {
            if (node.next.key.equals(key)) {
                break;
            }
            node = node.next;
        }

        node.next = node.next.next;
        --size;
    }

    /**
     * 删除根节点
     */
    public void removeFoot() {
        if (isEmpty()) {
            return;
        }

        Node node = head.next;
        while (node.next != null && node.next.next != null) {
            node = node.next;
        }

        node.next = null;
        --size;
    }

    /**
     * 删除右侧(倒数)第n个节点
     *
     * @param n 右侧第n节点
     */
    public void removeRgiht(int n) {

    }

    public String toString() {
        if (isEmpty()) {
            return "";
        }

        Node node = head.next;
        StringBuilder str = new StringBuilder();
        str.append("{\"");
        while (node.next != null) {
            String tmp = node.key + "\":\"" + node.value + "\",\"";
            str.append(tmp);
            node = node.next;
        }
        String tmp = node.key + "\":\"" + node.value + "\"}";
        str.append(tmp);
        return str.toString();
    }

    /**
     * 链表反转
     */
    public void reverse() {
        if (isEmpty()) return;

        Node node = head.next;
        Node linkedlist = null;
        while (node != null) {
            // 保存指针 链表的下一次移动
            Node pointer = node.next;

            // 将节点放入列表中
            node.next = linkedlist;
            linkedlist = node;

            // 移动列表指针
            node = pointer;
        }

        head.next = linkedlist;
    }

    /**
     * 两个有序链表合并一个有序链表 
     * TODO 写的不对
     *
     * @param list 另一个链表
     * @return
     */
    public void merge(LinkedList list) {
        if (list.isEmpty()) return;
        if (isEmpty()) {
            head.next = list.getHead().next;
            return;
        }

        Node aPointer = head.next;
        Node bHead = list.getHead();
        Node bPointer = bHead.next;

        while (aPointer.next != null) {

            // a > b
            if (aPointer.next.key.compareTo(bPointer.key) > 0) {
                // 保存 b 节点
                Node tmp = new Node(bPointer.key, bPointer.value);

                // b 的当前节点插入 a 中
                tmp.next = aPointer.next;
                aPointer.next =tmp;

                bPointer = bPointer.next;
            }

            aPointer = aPointer.next;
        }


        if (bPointer != null) {
            aPointer.next = bPointer;
        }
    }

    /**
     * 变成一个有序链表
     */
    public void sort() {

    }

    /**
     * 检测列表是否有环
     * 快慢指针法
     *
     * @return
     */
    public boolean isRing() {
        Node slowPointer = head.next;
        Node fastPointer = head.next;

        while (fastPointer.next != null && fastPointer.next.next != null) {

            if (slowPointer.key.compareTo(fastPointer.key) == 0) {

                return true;
            }

            slowPointer = slowPointer.next;
            fastPointer = fastPointer.next.next;
        }

        return false;
    }

    /**
     * 获取链表中间节点
     *
     * @return
     */
    public Node getMiddleNode() {
        return null;
    }
}