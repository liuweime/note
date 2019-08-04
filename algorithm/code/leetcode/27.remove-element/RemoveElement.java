public class RemoveElement {

    /**
     * 写的有点繁琐，有空优化
     */
    public int run(int[] nums, int val) {
        if (nums.length == 0) {
            return 0;
        }

        int low =  0;
        int fast = 0;
        while (fast < nums.length) {
            if (nums[fast] == val) {
                fast++;
            } else {
                if (fast != low) {
                    nums[low] = nums[fast];
                }
                fast++;
                low++;
            }
        }

        return low;
    }
}
