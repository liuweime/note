import java.util.*;

public class TwoSum
{
    public Integer[] count(int[] list, int target)
    {
        ArrayList<Integer> data = new ArrayList<>();
        // 先对数组进行排序
        Arrays.sort(list);
        for (int i = 0, j = 0; i < list.length; i++) {
            // TODO int溢出问题
            j = rank(list, target-list[i]);
            if (j > i) {
                data.add(list[i]);
                data.add(list[j]);
            }
        }

        return data.toArray(new Integer[data.size()]);
    }

    public int countSecond(int[] list, int target)
    {
        int num = 0;
        Arrays.sort(list);

        int left = 0;
        int right = list.length-1;
        while (left < right) {
            if ((list[left] + list[right]) == target) {
                num++;
                left++;
                right--;
            } else if ((list[left] + list[right]) > target) {
                right--;
            } else {
                left++;
            }
        }
        return num;
    }

    public int[] countThird(int[] nums, int target)
    {
        int[] data = new int[2];

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                System.out.println(map.get(target-nums[i]));
                System.out.println(i);
                data[0] = map.get(target-nums[i]);
                data[1] = i;
            } else {
                map.put(nums[i], i);
            }
        }

        return data;
    }

    public int[] randomData(int len)
    {
        int[] data = new int[len];

        for (int i = 0; i < len; i++) {
            data[i] = (int)(Math.random() * len)  + 1;
        }

        return data;
    }

    public List<List<Integer>> threeSum(int[] nums, int target)
    {
        List<List<Integer>> list = new ArrayList<>();

        Arrays.sort(nums);
        int k;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i+1; j < nums.length; j++) {
                k = rank(nums, target-nums[i]-nums[j]);
                if (k > j) {
                    List<Integer> data = new ArrayList<>();
                    data.add(nums[i]);
                    data.add(nums[j]);
                    data.add(nums[k]);
                    list.add(data);

                    // 去重
                    while (nums[i] == nums[i+1]) { i++;}
                    while (nums[j] == nums[j+1]) { j++;}
                }
            }
        }

        return list;
    }

    /**
     * 首尾指针移动
     * @param nums
     * @param target
     * @return
     */
    public List<List<Integer>> threeSumTwo(int[] nums, int target)
    {

        // -1, 0, 1, 2, -1, -4
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        int left;
        int right;
        for (int i = 0; i < nums.length; i++) {
            left = i + 1;
            right = nums.length-1;

            while (left < right) {
                int sum = nums[i]+nums[right]+nums[left];
                if (sum < target) {
                    left++;
                } else if (sum > target) {
                    right--;
                } else {
                    List<Integer> data = new ArrayList<>();
                    data.add(nums[i]);
                    data.add(nums[left++]);
                    data.add(nums[right--]);
                    list.add(data);



                    // 去重
                    while (nums[i] == nums[i+1]) { i++; }
                    while (nums[left] == nums[left+1]) { left++; }
                    while (nums[right] == nums[right-1]) { right--; }
                }
            }
        }

        return list;
    }

    private int rank(int[] list, int target)
    {
        int start = 0;
        int end = list.length - 1;
        int index = -1;

        int middle;
        while (start <= end) {
            middle = (start + end) / 2;
            if (list[middle] > target) {
                end = middle - 1;
            } else if (list[middle] < target) {
                start = middle + 1;
            } else {
                index = middle;
                break;
            }
        }

        return index;
    }
}
