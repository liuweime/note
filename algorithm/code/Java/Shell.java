import java.util.Arrays;

public class Shell extends SortTpl
{

    public static final int Shell = 0;
    public static final int Hibbard = 1;
    public static final int Knuth = 2;
    public static final int Sedgewick = 3;

    /**
     * 希尔增量
     * h = N/2^k
     *
     * @param len
     * @return
     */
    private static int shellGep(int len)
    {
        return len / 2;
    }

    private static int log(double num, double d)
    {
        return (int)(Math.log(num) / Math.log(d));
    }

    /**
     * hibbard 增量
     * h = 2^k - 1
     * h < N
     *
     * @param len
     * @return
     */
    private static int hibbardGep(int len)
    {
        double i = log(len, 2);

        return (int)Math.pow(2, i) - 1;
    }

    /**
     * knuth 增量
     * h = (3^k-1)/2
     * h <= N
     *
     * @param len
     * @return
     */
    private static int knuthGep(int len)
    {
        double i = log(2*len, 3);

        return (int)(Math.pow(3, i) - 1)/2;
    }

    /**
     * Sedgewick 增量
     * h = 8 * 2^k - 6 * 2^((k+1)/2) + 1  [k is odd]
     * h = 9 * (2^k - 2^(k/2)) + 1 [k is even]
     * h < N
     *
     * @param len
     * @return
     */
    private static int sedgewickGep(int len)
    {
        if (len == 1) {
            return 0;
        }
        double h = 1;
        double i = 1;

        double oldh = 1;
        while (h < len)
        {
            oldh = h;
            if (isOdd(i)) {
                h = 8 * Math.pow(2, i)- 6 * Math.pow(2, (i+1)/2) + 1;
            } else {
                h = 9 * (Math.pow(2, i) - Math.pow(2, i/2)) + 1;
            }
            i++;
        }

        return (int)oldh;
    }

    /**
     *
     * @param num
     * @return
     */
    private static boolean isOdd(double num)
    {
        return num % 2 == 0 ? false : true;
    }

    private static int gep(int len, int type)
    {
        int gep = 0;
        switch (type) {
            case Shell:
                gep = shellGep(len);
                break;
            case Hibbard:
                gep = hibbardGep(len);
                break;
            case Knuth:
                gep = knuthGep(len);
                break;
            case Sedgewick:
                gep = sedgewickGep(len);
                break;
        }

        return gep;
    }

    public static void sort(Comparable[] data, int type)
    {
        System.out.println(Arrays.toString(data));
        int len = data.length;
        int gep = gep(len, type);

        while (gep > 0)
        {
            for (int i = gep; i < len; i++)
            {
                for (int j = i; j >= gep && less(data[j], data[j-gep]); j -= gep)
                {
                    exch(data, j, j-gep);
                }
            }

            System.out.println(Arrays.toString(data));
            gep = gep(gep, type);
        }
    }

    protected static void times(int type, int len)
    {
        Integer[] data = randomData(len);

        long a = System.currentTimeMillis();
        sort(data, type);
        long b = System.currentTimeMillis();

        System.out.println(b-a + "ms");
    }

    public static void main(String[] args)
    {
//        int len = 10;
        System.out.println(hibbardGep(10));
//        times(Shell, len);
//        times(Hibbard, len);
//        times(Knuth, len);
//        times(Sedgewick, len);

//        Integer[] data = randomData(len);
//        long a = System.currentTimeMillis();
//        sort(data, Shell);
//        long b = System.currentTimeMillis();
//        System.out.println(b-a + "ms");
//
//        a = System.currentTimeMillis();
//        sort(data, Shell);
//        b = System.currentTimeMillis();
//        System.out.println(b-a + "ms");
//
//
//        a = System.currentTimeMillis();
//        sort(data, Sedgewick);
//        b = System.currentTimeMillis();
//        System.out.println(b-a + "ms");
//        show(data);
    }
}
