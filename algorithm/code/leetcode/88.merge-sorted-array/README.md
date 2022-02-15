# merge sorted array | 合并两个有序数组

## 题目

给你两个按 非递减顺序 排列的整数数组 nums1 和 nums2，另有两个整数 m 和 n ，分别表示 nums1 和 nums2 中的元素数目。

请你 合并 nums2 到 nums1 中，使合并后的数组同样按 非递减顺序 排列。

注意：最终，合并后数组不应由函数返回，而是存储在数组 nums1 中。为了应对这种情况，nums1 的初始长度为 m + n，其中前 m 个元素表示应合并的元素，后 n 个元素为 0 ，应忽略。nums2 的长度为 n 。

## 分析

**方法一**

有序数组合并，直接合并两个数组，把nums2数据放入nums1尾部，再进行排序可以完成，没啥意思；

**方法二**

如果要求放入nums1中，如果遇到nums2中比1小的元素，就需要把要插入位置的元素全部向后移动一位，具体做法是：

- 循环nums1、nums2，比较元素（循环截止于m、n）
- 遇到nums1 > nums2，将当前元素及其之后元素全部向后移动一位，注意此时 m 的值应该增大，m代表的是nums1中元素数目
- 如果 nums1 ≤ nums2，不需要元素移动，nums1的指针移动一位，nums2 不需要动，我们需要在nums1中找到一个大于nums2的元素，才能保证插入时，插入元素大于num1前一位，小于后一位
- 循环结束，如果nums2仍没有循环完毕，就将num2中元素之间移动到nums1尾部，这步不可省略，因为当 nums1一直小于 nums2 ，nums1 会先循环完毕，而 nums2 还没移动

**方法三**

方法二的缺点是遇到 nums1 > nums2 就需要把数组元素向后移动，如果nums1 最小都大于 nums2 最大，那要把nums1 移动 n次；方法二是从开头比较，如果从结尾比较，如果 nums1 中最大比较 nums2 中最大的都大，那么它就是合并数组中的最大的，直接放入nums1 尾部即可；反之，nums2 的放入nums1尾部；注意当 nums2 一直小于 nums1 会导致 nums1 先循环完;

## 代码

**方法二**

```Go
func merge(nums1 []int, m int, nums2 []int, n int)  {
  var mIndex, nIndex int
  for mIndex < m && nIndex < n {
    if nums1[mIndex] > nums2[nIndex] {
      for i := m; i > mIndex; i-- {
        nums1[i] = nums1[i-1]
      }
      nums1[mIndex] = nums2[nIndex]
      nIndex++
      mIndex++
      m++
    } else {
      mIndex++
    }
  }

  for nIndex < n {
    nums1[mIndex] = nums2[nIndex]
    mIndex++
    nIndex++
  }
}

```

**方法三**


```Go
func merge(nums1 []int, m int, nums2 []int, n int)  {
  tail := len(nums1) - 1
  mEnd := m - 1
  nEnd := n - 1
  for nEnd >= 0 {
    // 倒序1 <= 2 说明 2确实大，直接放入nums1尾部
    // 如果1一直>2 1会先循环完毕，就需要把2的元素放入1中
    if mEnd < 0 || nums1[mEnd] <= nums2[nEnd] {
      nums1[tail] = nums2[nEnd]
      tail--
      nEnd--
    } else {
      nums1[tail] = nums1[mEnd]
      tail--
      mEnd--
    }
  }
}
```

