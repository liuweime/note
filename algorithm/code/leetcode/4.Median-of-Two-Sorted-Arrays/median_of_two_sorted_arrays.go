package main

import "fmt"

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

func main()  {
	num1 := []int{2}
	num2 := []int{-1,-2}
	fmt.Println(MedianOfTwoSortedArrays(num1, num2))
}