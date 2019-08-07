public class SearchInsertPosition {

    /**
     * 递归的二分查找法
     */
    public int run(int[] nums, int target) {
        return search(nums, target, 0, nums.length);
    }

    public int search(int[] nums, int target, int start, int end) {
        if (start >= end) return start;

        int middle = (start + end) / 2;

        if (nums[middle] == target) return middle;
        if (nums[middle] > target) {
            return search(nums, target, start, middle);
        }
        return search(nums, target, middle+1, end);
    }

    /**
     * 最简单的 最坏 O(n) 最好 O(1)
     */
    public int old(int[] nums, int target) {
        if (nums.length == 0 || nums[0] >= target) return 0;

        for (int i = 0; i < nums.length-1; i++) {
            if (nums[i+1] >= target) {
                return i+1;
            }
        }

        return nums.length;
    }
}
