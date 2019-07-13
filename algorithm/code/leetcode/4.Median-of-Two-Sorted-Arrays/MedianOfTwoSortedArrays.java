import java.util.Arrays;

public class MedianOfTwoSortedArrays {

    public double run(int[] nums1, int[] nums2) {
        // [2 5 7]
        // [3 6 9 10]
        // 3.0
        int end = nums1.length + nums2.length;
        int middle = end / 2;
        int[] list = mergeSort(nums1, nums2);
        if (end % 2 != 0) {
            return list[middle];
        }

        return (list[middle] + list[middle-1]) / 2.0;
    }

    private int[] mergeSort(int[] nums1, int[] nums2) {
        int left = 0;
        int middle = nums1.length;
        int right = 0;
        int end = nums2.length;
        int i = 0;
        int[] item = new int[end+middle];
        while(left < middle && right < end) {
            item[i++] = nums1[left] < nums2[right] ? nums1[left++] : nums2[right++];
        }

        while(left < middle) {
            item[i++] = nums1[left++];
        }
        while(right < end) {
            item[i++] = nums2[right++];
        }

        return item;
    }
}
