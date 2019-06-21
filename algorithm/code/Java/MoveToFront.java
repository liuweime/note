import org.jetbrains.annotations.Contract;

public class MoveToFront
{
    private Node head;

    private int size;

    private class Node {
        public String item;
        public Node next;
    }

    private Node getBeforePointer(String item)
    {
        Node pointer = head.next;
        while (pointer.next != null && !pointer.next.item.equals(item)) {
            pointer = pointer.next;
        }
        return pointer;
    }

    public MoveToFront()
    {
        this.head = new Node();
        this.size = 0;
    }

    public boolean isEmpty()
    {
        return this.size == 0;
    }

    public void write(String item)
    {
        Node node = new Node();
        node.item = item;
        if (isEmpty()) {
            this.head.next = node;
            size++;
        } else {
            node.next = head.next;
            head.next = node;

            Node pointer = getBeforePointer(item);
            if (pointer.next == null) {
                size++;
            } else {
                pointer.next = pointer.next.next;
            }
        }
    }

    public String read()
    {
        if (isEmpty()) {
            return "";
        }

        return head.next.item;
    }

    public int size()
    {
        return size;
    }

    public String toString()
    {
        if (isEmpty()) {
            return "";
        }
        String item = "";
        Node pointer = head.next;
        while (pointer != null) {
            item += pointer.item + "->";
            pointer = pointer.next;
        }

        return item;
    }
}
