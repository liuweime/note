public class RemoveDuplicatesFromSortedArray {

    /**
     * 前后指针移动
     * 前后指针相等 就移动索引指针 直到不相等
     * 并将不相等元素直接移动到目标指针后一个位置
     * 最坏移动n次
     * 备注：可能不需要一个len变量统计长度
     */
    public int run(int[] nums) {
        if (nums.length < 2) return nums.length;

        int len = 1;
        int index = 1;
        int i = 0;
        while (i < nums.length && index < nums.length) {
            if (nums[i] == nums[index]) {
                index++;
            } else {
                nums[i+1] = nums[index];
                i++;
                len++;
            }
        }

        return len;
    }

    /**
     * 首先想到的
     * 移动不重复元素 效率较低 最坏可能需要n*n次
     */
    public int old(int[] nums) {
        if (nums.length == 0) return 0;
        int len = 1;
        int move = 0;
        int num = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (num != nums[i]) {
                for (int j = 0,z = i; j < move; j++,z--) {
                    nums[z-1] = nums[z];
                }
                num = nums[i];
                len++;
            } else {
                move++;
            }
        }

        return len;
    }
}
