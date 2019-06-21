
public class SortTpl {

    public static void sort(Comparable[] data) {}

    protected static boolean less(Comparable v, Comparable w)
    {
        return v.compareTo(w) < 0;
    }

    protected static void exch(Comparable[] data, int i, int j)
    {
        Comparable tmp = data[i];
        data[i] = data[j];
        data[j] = tmp;
    }

    protected static void show(Comparable[] data)
    {
        int len = data.length;
        for (int i = 0; i < len; i++)
        {
            System.out.print(data[i] + " ");
        }
        System.out.println();
    }

    public static boolean isSorted(Comparable[] data)
    {
        int len = data.length;
        for (int i = 1; i < len; i++)
        {
            if (less(data[i], data[i-1])) {
                return  false;
            }
        }

        return true;
    }

    public static Integer[] randomData(int len)
    {
        Integer[] data = new Integer[len];

        for (int i = 0; i < len; i++) {
            data[i] = (int)(Math.random() * len)  + 1;
        }

        return data;
    }

    public static void main(String[] args)
    {

    }
}
