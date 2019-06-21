public class WeightQuickUnion
{
    private int size;

    private int[] id;

    private int[] treeSize;

    public WeightQuickUnion(int N)
    {
        size = N;
        this.id = new int[N];
        this.treeSize = new int[N];

        for (int i = 0; i < N; i++) {
            this.id[i] = i;
            this.treeSize[i] = 1;
        }
    }

    public int find(int num)
    {
        while (id[num] != num) {
            num = id[num];
        }

        return num;
    }

    public boolean connected(int numP, int numQ)
    {
        return find(numP) == find(numQ);
    }

    public int count()
    {
        return size;
    }

    public void union(int numP, int numQ)
    {
        int pid = find(numP);
        int qid = find(numQ);
        if (pid == qid) return;

        if (treeSize[pid] > treeSize[qid]) {
            id[qid] = pid;
            treeSize[pid] += treeSize[qid];
        } else {
            id[pid] = qid;
            treeSize[qid] += treeSize[pid];
        }
        size--;
    }
}
