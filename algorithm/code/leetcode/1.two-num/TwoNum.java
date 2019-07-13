import java.util.HashMap;

public class TwoNum {

    public int[] run(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int val = target - nums[i];
            if (map.get(val) != null) {
                return new int[]{map.get(val), i};
            }
            map.putIfAbsent(nums[i], i);
        }

        return new int[2];
    }
}
