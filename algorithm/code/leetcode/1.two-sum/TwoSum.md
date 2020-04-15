---
title: two num
difficulty: easy
---



# TwoSum | 两数之和

**题目描述**

> 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那两个整数，并返回他们的数组下标。
> 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
>
> 示例:
> > 给定 nums = [2, 7, 11, 15], target = 9
> > 因为 nums[0] + nums[1] = 2 + 7 = 9
> > 所以返回 [0, 1]



题目描述已经很清楚了，并且排除了目标值存在多种答案的情况，也就是说不会有`[1,2,4,5] target=6`，同时`[3,3] target=6`不能返回`[0,0]`或`[1,1]`

看完题目，最容易想到的应该是两重循环的暴力破解法，时间复杂度 O(n^2)，空间复杂度 O(1)。一种优化思路是使用二分法，对于数组中的每一个元素 nums[i]，对其 target - nums[i] 在 nums 中进行二分查找。

另一种相似的优化思路取自于二分法，叫首尾指针法或双指针法，在假设数组有序情况下，将首元素成为left，尾元素称为 right，如果 left + right > target，就需要使 left + right 之和变小，那么就需要让 left 右移，反之就需要让 right 左移。

这两种优化都有个前提，即该数组必须是有序数组，因此，在循环之前必须将数组进行排序，按照常用的快速排序的时间复杂度来计算，那么整体的时间复杂度是 O(nlogn)。

另外，由于数组经过排序，索引发生变化，因此还需要多余空间将原索引保存，空间复杂度：O(n)

代码如下：

```php
function twoSum($nums, $target) {
	$data = $nums;
	sort($nums);
    $len = count($nums);
    for ($i = 0, $j = $len-1; $i < $j;) {
    	$sum = $nums[$i] + $nums[$j];
        if ($sum === $target) {
        	$tmpi = array_search($nums[$i], $data);
        	unset($data[$tmpi]);
        	$tmpj = array_search($nums[$j], $data);
            return [$tmpi, $tmpj];
        } 
        if ($sum > $target) {
            $j--;
        } else {
            $i++;
        }
    }
}
```

另一种思路是基于 hashmap，要寻找的