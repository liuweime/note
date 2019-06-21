import java.util.Arrays;

public class Insertion extends SortTpl
{

    public static void sort(Comparable[] data)
    {
        int len = data.length;
        for (int i = 1; i < len; i++) {
            for(int j = i; j > 0 && less(data[j], data[j-1]); j--) {
                exch(data, j, j-1);
            }
        }
    }

    public static void sortSec(Comparable[] data)
    {
        for (int i = 1; i < data.length; i++) {
            int j = i;
            Comparable tmp = data[i];
            for (; j > 0 && less(tmp, data[j-1]); j--) {
                data[j] = data[j-1];
            }
            data[j] = tmp;
        }
    }

    public static void main(String[] args)
    {
        Integer a = 1;
        Integer b = 5;

        System.out.println(less(a,b));

        Integer[] data = randomData(20);
        show(data);
        sortSec(data);

        System.out.println("Sort after");
        show(data);
    }
}
