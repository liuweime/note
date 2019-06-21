public class Josephus
{
    private class Node
    {
        public int item;
        public Node next;
        public Node prev;
    }

    private Node pointer;

    private int size;

    public Josephus()
    {
        pointer = new Node();
    }

    public boolean isEmpty()
    {
        return size == 0;
    }

    public void show()
    {
        int n = 0;
        Node node = pointer;
        while (n < 7) {
            System.out.println(node.item);
            node = node.next;
            n++;
        }
    }

    public void build(int N)
    {
        while (this.size < N) {
            Node node = new Node();
            node.item = this.size;
            if (isEmpty()) {
                node.next = node;
                node.prev = node;

                pointer = node;
            } else {
                node.next = pointer.next;
                node.prev = pointer;

                pointer.next = node;

                Node tmp = pointer;
                while (tmp.prev != pointer) {
                    tmp = tmp.prev;
                }
                tmp.prev = node;

                pointer = node;
            }
            this.size++;
        }
    }

    public void pop(int item)
    {
        while (pointer.item != item) {
            pointer = pointer.next;
        }
        pointer.prev.next = pointer.next;
        pointer.next.prev = pointer.prev;
        size--;
    }

    public void run()
    {
        pointer = pointer.next;
    }

    public int calu(int N, int m)
    {
        build(N);
        // 初始化指针
        pointer = pointer.next;
        // 进行计算

        // 从 1 报数
        int num = 1;
        while (size > 0) {
            // 报到指定数 对应编号从链表移出
            if (num == m) {
                pop(pointer.item);
                // 下一个重新保号
                num=1;
            } else {
                num++;
            }
            // 循环链表
            run();
        }

        return pointer.item;
    }
}
