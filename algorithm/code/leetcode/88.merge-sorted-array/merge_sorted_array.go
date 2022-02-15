package main

import "fmt"

func mergeT(nums1 []int, m int, nums2 []int, n int) {
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

func main() {
	num1 := []int{1,2,3,0,0,0}
	num2 := []int{2,3,4}

	mergeT(num1, 3, num2, len(num2))
	fmt.Println(num1)
}
