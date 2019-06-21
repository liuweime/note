import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {

    private static int a = 0;

    public static long fibonacci(int n) {
        ++a;
        if (n <= 1) {
            return n;
        }

        return fibonacci(n-1) + fibonacci(n-2);
    }

    public static long fibonacciTwo(int n, long before, long after) {
        ++a;
        if (n <= 1) {
            return before;
        }
        return fibonacciTwo(n-1, after, before+after);
    }

    public static int[] twoSum(int[] nums, int target)
    {
        int[] data = new int[2];
        Arrays.sort(nums);
        int left = 0;
        int right = nums.length-1;
        while(left < right) {
            if (nums[left] + nums[right] > target) {
                right--;
            } else if (nums[left] + nums[right] < target) {
                left++;
            } else {
                data[0] = left;
                data[1] = right;
                break;
            }
        }
        return data;
    }

    public static int[] twoSumOpt(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement) && map.get(complement) != i) {
                return new int[] { i, map.get(complement) };
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    public static void main(String[] args) throws Exception {

//        HashMap<Integer, Integer> map = new HashMap<>();
        int[] list = new int[]{4,3,1,2};

//        for (int i = 0; i < list.length; i++) {
//            map.put(list[i], i);
//        }
//        map.forEach((k, v) -> System.out.println(v));
        int[] data = twoSumOpt(list, 4);
//        fibonacciTwo(30, 1, 1); // 30

//        int[] list = new int[]{3,2,4}; // 1,2
////        fibonacci(30); // 2692537
//        int[] data = twoSum(list, 6);
        System.out.println(Arrays.toString(data));

//        long d = System.currentTimeMillis();
//        System.out.println(fibonacciTwo(50, 1, 1));
//        long e = System.currentTimeMillis();
//        System.out.println("fibonacci:" + (e-d));
    }
}