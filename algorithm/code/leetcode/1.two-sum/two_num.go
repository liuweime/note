package main

import (
	"fmt"
)

func TwoSum(num []int, target int) []int {
	length := len(num)
	i := 0
	j := length-1
	for i < j {
		if num[i] + num[j] == target {
			return []int{i,j}
		}

		if num[i] + num[j] > target {
			j--
		} else {
			i++
		}
	}
	return nil
}

func TwoSumOpt(nums []int, target int) []int {
	data := make(map[int]int)
	length := len(nums)
	for	i := 0; i < length; i++ {
		val := target - nums[i]
		if res, ok := data[val]; ok == true {
			return []int{res, i}
		}
		if _, ok := data[nums[i]]; ok == false {
			data[nums[i]] = i
		}
	}
	return nil
}

func main() {
    data := []int{3,3}
    fmt.Println(data, len(data))
    res := TwoSumOpt(data, 6)
    fmt.Println(res)
}