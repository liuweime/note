import java.util.Arrays;

public class QuickUnion
{
    private int size;

    public int[] id;

    public QuickUnion(int N)
    {
        size = N;
        this.id = new int[N];

        for (int i = 0; i < N; i++) {
            this.id[i] = i;
        }
    }

    /**
     * 找到指定值的标志符
     *
     * @param num
     * @return
     */
    public int find(int num)
    {
        while (id[num] != num) {
            num = id[num];
        }
        return num;
    }

    /**
     * 判断 q、p 是否连接
     *
     * @param numP
     * @param numQ
     * @return
     */
    public boolean connected(int numP, int numQ)
    {
        return find(numP) == find(numQ);
    }

    /**
     * 将 p、q 连通
     * @param numP
     * @param numQ
     */
    public void union(int numP, int numQ)
    {
        int pid = find(numP);
        int qid = find(numQ);

        if (pid == qid) {
            System.out.println();
            System.out.println(Arrays.toString(id));
            System.out.println(numP+":"+pid+"="+numQ+":"+qid);
            System.out.println();
            return;
        }

        id[pid] = qid;
        System.out.println(numP+":"+qid);
        System.out.println(numQ+":"+qid);
        System.out.println(Arrays.toString(id));
        System.out.println();
        size--;
    }

    /**
     * 返回等价类数量
     *
     * @return
     */
    public int count()
    {
        return size;
    }
}
