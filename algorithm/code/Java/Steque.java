public class Steque<T> implements StequeInterface<T>
{
    protected Node head;

    protected Node foot;

    protected int size;

    private class Node
    {
        T item;
        Node next;
    }

    public Steque()
    {
        head = new Node();
        foot = new Node();
    }

    public boolean isEmpty()
    {
        return size == 0;
    }

    public void each()
    {
        Node node = head.next;
        System.out.println(node.item);
        while (node.next != null) {
            node = node.next;
            System.out.println(node.item);
        }
    }

    @Override
    public T pop()
    {
        T data = foot.next.item;

        Node node = head.next;
        while (node.next != null && node.next.next != null) {
            node = node.next;
        }
        node.next = null;
        foot.next = node;
        size--;

        return data;
    }

    @Override
    public void push(T item)
    {
        Node node = new Node();
        node.item = item;

        if (!isEmpty()) {
            foot.next.next = node;
        } else {
            head.next = node;
        }
        foot.next = node;
        size++;
    }

    @Override
    public void enqueue(T item)
    {
        Node node = new Node();
        node.item = item;

        if (isEmpty()) {
            head.next = node;
            foot.next = node;
        } else {
            node.next = head.next;
            head.next = node;
        }
        size++;
    }
}
