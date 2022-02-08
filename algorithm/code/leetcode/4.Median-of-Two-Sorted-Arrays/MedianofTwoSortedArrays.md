# 04 Median Of Two Sorted Arrays | 寻找两个正序数组中位数



## 题目

给定两个长度为 m 、 n 的正序整数数组，求两个数组的中位数。要求时间复杂度 O(log(m+n))

```text
输入：[1,2]  [3,4]
输出：2.5

输入：[] [2]
输出：2

输入：[2] [1,3]
输出：2
```

中位数的计算方式：

```text
奇数：
中位数=data[(n+1)/2]

偶数：
中位数=(data[n/2]+data[(n+2)/2])/2
```

## 分析

**方法一**

首先想到的是循环两个数组，按顺序存到一个新数组中，在新数组中按照中位数算法计算中位数即可；循环的时候注意顺序，比较两个数组中的值，只有放入新数组的那一个数组才移动指针；最后如果还有数组指针没有移动到结尾，将该指针到结尾的元素直接放入新数组即可。

可显然这个方法的时间复杂度是O(m+n)，没有达到 O(log(m+n)) 的标准，虽然在LeetCode中可以提交通过。

**还没想好方法，先放到这儿，以后再来。**

## 代码

```php
// 该方式的时间复杂度是 O(m+n)
func MedianOfTwoSortedArrays(nums1, nums2 []int) float64 {
  mLen := len(nums1)
  nLen := len(nums2)
  mergeLen := mLen + nLen  // 4
  data := make([]int, mergeLen)

  i := 0
  m,n := 0,0
  for ; m < mLen && n < nLen;{
    if nums1[m] > nums2[n] {
      data[i] = nums2[n]
      n++
    } else {
      data[i] = nums1[m]
      m++
    }
    i++
  }
  for m < mLen {
    data[i] = nums1[m]
    m++
    i++
  }
  for n < nLen {
    data[i] = nums2[n]
    n++
    i++
  }

  var num float64
  if mergeLen % 2 == 0 {
    middleNum := float64(data[mergeLen/2-1] + data[(mergeLen+2)/2-1])
    num =  middleNum / float64(2)
  } else {
    num = float64(data[mergeLen/2])
  }
  return num
}
```