package main

import (
	"fmt"
	"sort"
)


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
}

func TwoSumDoubleFor(nums []int, target int) []int {
	for i := 0; i < len(nums); i++ {
		for j := i+1; i < len(nums); j++ {
			if target == (nums[i] + nums[j]) {
				return []int{i, j}
			}
		}
	}
	return nil
}

func TwoSumDichotomy(nums []int, target int) []int {
	numsClone := make([]int, len(nums))
	copy(numsClone, nums)
	sort.Sort(sort.IntSlice(nums))

	for i := 0; i < len(nums); i++ {
		val := target - nums[i]
		isFind, index := dichotomy(nums, val, i+1, len(nums)-1)
		if isFind {
			res := make([]int, 2)
			ok, findI := arrayFind(numsClone, nums[i])
			if ok {
				res[0] = findI
			}
			ok, findJ := arrayFind(numsClone, nums[index])
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

func TwoSumDichotomyOpt(nums []int, target int) []int {

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


func main() {
    data := []int{3,3}
    fmt.Println(data, len(data))
    res := TwoSum(data, 6)
    fmt.Println(res)
}