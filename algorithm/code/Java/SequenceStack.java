import java.util.ArrayList;
import java.util.Arrays;

public class SequenceStack {

    private int size;

    private int length;

    private final int MAX_NUM = 200;

    private String[] list;

    public SequenceStack(int n) {
        length = n;
        list = new String[length];
        size = 0;
    }

    private void expand() {
        int newLength = length + (length >> 1);
        if ((newLength - MAX_NUM) >= 0) return;

        list = Arrays.copyOf(list, newLength);
        length = newLength;
    }

    /**
     *
     * @return
     */
    public String pop() {
        if (size == 0) return null;

        String item = list[size-1];
        --size;

        return item;
    }

    /**
     *
     * @param item 入栈元素
     */
    public boolean push(String item) {
        if (size == length) {
            expand();
        }
        list[size] = item;
        ++size;

        return true;
    }
}