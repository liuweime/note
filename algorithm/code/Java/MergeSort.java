public class MergeSort extends SortTpl
{

    private static Comparable[] item;

    private static void sort(Comparable[] list, int start, int end)
    {
        if (start >= end) {
            return;
        }

        int middle = (start + end)/2;
        sort(list, start, middle);
        sort(list, middle+1, end);

        // merge
        merge(list, start, middle, end);
    }

    private static void merge(Comparable[] list, int start, int middle, int end)
    {
        int left = start;
        int right = middle+1;

        for (int i = start; i <= end; i++) {
            // 左侧列表空 取右侧
            if (left > middle) {
                item[i] = list[right++];
            } else if (right > end) {
                // 右侧列表空 取得左侧
                item[i] = list[left++];
            } else if (less(list[left], list[right])) {
                // 左侧节点小于右侧节点 取出左侧节点
                item[i] = list[left++];
            } else {
                // 取出右侧节点
                item[i] = list[right++];
            }
        }

        // 回填到原数组中
        for (int i = start; i <= end; i++) {
            list[i] = item[i];
        }
    }

    public static void sort(Comparable[] data)
    {
        item = new Comparable[data.length];
        sort(data, 0, data.length-1);
    }
}
