public class FIFO  {
//
//    /**
//     * 链表对象
//     */
//    private LinkedList<Integer> linkedList;
//
//    /**
//     * FIFO 算法，使用链表实现
//     */
//    public FIFO() {
//        this.linkedList = new LinkedList<>();
//    }
//
//    public String toString() {
//        return  linkedList.toString();
//    }
//
//    /**
//     * FIFO 先进先出
//     *
//     * @param item
//     */
//    public void put(Integer item){
//        // 在链表中存在 不操作
//        if (linkedList.getNode(item) != null) {
//            return;
//        }
//        // 链表满
//        if (linkedList.isFull()) {
//            // 删除最早节点
//            linkedList.removeFoot();
//        }
//        linkedList.add(item);
//    }
}
