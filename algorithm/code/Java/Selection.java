public class Selection extends SortTpl
{

    /**
     *
     * @param data
     */
    public static void sort(Comparable[] data)
    {
        int len = data.length;

        for(int i = 0; i < len; i++) {
            int min = i;
            for (int j = i + 1; j < len; j++) {
                if (less(data[j], data[min])) {
                    min = j;
                }
            }

            exch(data, i, min);
        }
    }

    public static void main(String[] args)
    {
        Integer[] data = randomData(20);
        show(data);
        sort(data);

        System.out.println("Sort after:");
        show(data);
    }
}