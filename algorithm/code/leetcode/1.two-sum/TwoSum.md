---
title: two num
difficulty: easy
---



# TwoSum | 两数之和

### 题目描述

> 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那两个整数，并返回他们的数组下标。
> 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
>
> 示例:
> > 给定 nums = [2, 7, 11, 15], target = 9
> > 因为 nums[0] + nums[1] = 2 + 7 = 9
> > 所以返回 [0, 1]

### 分析

题目描述已经很清楚了，并且排除了目标值存在多种答案的情况，也就是说不会有`[1,2,4,5] target=6`，同时`[3,3] target=6`不能返回`[0,0]`或`[1,1]`



**1、暴力循环法**

最先想到的方法，两重循环，检测元素和是否等于目标值，时间复杂度`O(n^2)`，空间复杂度`O(1)`。



**2、二分法**

基于题目条件，根据仅有一对满足且同一元素不能重复，可以得到：对于`num[i]`，在nums中存在`target-num[i]`，那么就可以转换成二分法查询元素；使用该方式的前提是数组有序，那么就需要在查找前排序，同时由于排序后元素下标变化了，需要额外空间保存排序前数组下标，因此该方式不推荐使用。



**3、首尾移动**

该方法需要nums是有序数组，将数组首尾相加，于目标值对比，如果：

- 大于目标值，说明尾部元素大了，可以前取一位稍小的值于首部相加
- 小于目标值，说明首部元素小了，可以后取一位稍大的值与尾部相加
- 直到找到目标值

该方法一次循环即可找到目标，优化了二分查询，但是和二分查询一样，需要排序。



**4、二分法优化-利用map**

二分法时间复杂度`logn`，由于需要循环nums，所以整体时间复杂度时`nlogn`；对其的一种优化思路是利用map，nums的元素做key，下标做值；如果`target-nums[i]`在map中找到，返回其值和i即可；由于只有一次循环，map查询元素时间复杂度`O(1)`，所以整体时间复杂度`O(n)`，使用了map，空间复杂度`O(n)`



### 代码

**1、暴力循环法**

```Go
func TwoSum(nums []int, target int) []int {
  for i := 0; i < len(nums); i++ {
    for j := i+1; i < len(nums); j++ {
      if target == (nums[i] + nums[j]) {
        return []int{i, j}
      }
    }
  }
  return nil  
}
```

**2、二分法**

```Go
func TwoSum(nums []int, target int) []int {
  numsClone := make([]int, len(nums))
  copy(numsClone, nums)
  sort.Sort(sort.IntSlice(nums))

  for i := 0; i < len(nums); i++ {
    val := target - nums[i]
    isFind, index := dichotomy(nums, val, i+1, len(nums)-1)
    if isFind {
      res := make([]int, 2)
      ok, findI := arrayFind(numsClone, i)
      if ok {
        res[0] = findI
      }
      ok, findJ := arrayFind(numsClone, index)
      if ok {
        res[1] = findJ
      }

      return res
    }
  }
  return nil
}

func dichotomy(nums []int, target, start, end int) (bool, int) {
  sort.Sort(sort.IntSlice(nums))
  if start < 0 {
    start = 0
  }
  if end > len(nums) - 1 {
    end = len(nums) - 1
  }
  var middle int
  for start <= end {
    middle = (start + end) / 2
    if nums[middle] == target {
      break
    }
    if nums[middle] > target {
      end = middle - 1
    } else {
      start = middle + 1
    }
  }
  if start > end {
    return false, 0
  }
  return true, middle
}

func arrayFind(nums[]int, target int) (bool,int) {
  for i := 0; i < len(nums); i++ {
    if nums[i] == target {
      return true, i
    }
  }
  return false, 0
}
```

**3、首尾移动**

```Go
func TwoSum(nums []int, target int) []int {
  numsClone := make([]int, len(nums))
  copy(numsClone, nums)
  sort.Sort(sort.IntSlice(nums))
  left := 0
  right := len(nums) - 1

  for left <= right {
    if target == (nums[left] + nums[right]) {
      res := make([]int, 2)
      ok, leftIndex := arrayFind(numsClone, nums[left])
      if ok {
        res[0] = leftIndex
      }
      ok, rightIndex := arrayFind(numsClone, nums[right])
      if ok {
        res[1] = rightIndex
      }
      return res
    }

    if target > (nums[left] + nums[right]) {
      left++
    } else {
      right--
    }
  }
  return nil
```

**4、二分法优化-利用map**

```Go
func TwoSum(nums []int, target int) []int {

  numMap := make(map[int]int)
  for i := 0; i < len(nums); i++ {
    val := target - nums[i]
    if num, ok := numMap[val]; ok {
      return []int{i, num}
    }
    if _, ok := numMap[nums[i]]; !ok {
      numMap[nums[i]] = i
    }
  }
  return nil
}
```