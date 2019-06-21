public class RingBuffer
{
    private static final int N = 6;

    private Integer[] buffer = new Integer[N];

    private int flag = 0;

    private int head;

    private int tail;

    public boolean isEmpty()
    {
        return head == tail && flag == 0;
    }

    public boolean isFull()
    {
        return head == tail && flag == 1;
    }

    public void add(Integer item)
    {

        buffer[tail] = item;

        tail = (tail + 1) % N;
        if (tail == head) {
            flag = 1;
        }
    }

    public Integer getBuffer()
    {
        Integer item = buffer[head];
        head = (head + 1) % N;
        if (head == tail) {
            flag = 0;
        }

        return item;
    }

    public void show()
    {
        head = 5;
        System.out.println(getBuffer());
        System.out.println(isEmpty());
        System.out.println(getBuffer());
        System.out.println(getBuffer());
        System.out.println(getBuffer());
        System.out.println(getBuffer());
        System.out.println(getBuffer());

    }
}
