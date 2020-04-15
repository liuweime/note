/*
 * @Author: your name
 * @Date: 2020-03-25 17:02:30
 * @LastEditTime: 2020-04-15 18:39:03
 * @LastEditors: your name
 * @Description: In User Settings Edit
 * @FilePath: \goc:\Users\liuw\Documents\note\algorithm\code\leetcode\1.two-sum\TwoSum.java
 */
import java.util.HashMap;

public class TwoSum {

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
