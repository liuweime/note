public class QuickFind
{
    private int size;

    public int[] id;

    public QuickFind(int N)
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
        return id[num];
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
        if (connected(numP, numQ)) {
            return;
        }

        int pId = find(numP);
        int qId = find(numQ);
        // 将 p q 变成一个等价类
        for (int i = 0; i < id.length; i++) {
            if (id[i] == pId) {
                id[i] = qId;
            }
        }
        // 减少了一个等价类
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
