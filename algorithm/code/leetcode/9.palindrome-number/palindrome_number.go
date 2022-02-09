package main

import (
	"fmt"
)

//func isPalindrome(x int) bool {
//	if x < 0 {
//		return false
//	}
//
//	s := strconv.Itoa(x)
//	left := 0
//	right := len(s) - 1
//	res := true
//	for left < right {
//		if s[left] != s[right] {
//			res = false
//			break
//		}
//		left++
//		right--
//	}
//	return res
//}

func isPalindrome(x int) bool {
	// 负数和个位是十的不可能是回文数
	if x < 0 || (x != 0 && x % 10 == 0) {
		return false
	}

	// 获取数字的后一半是反转数
	// 当原数比反转数小 说明已经取完一半的数
	// 反转数=原数的前半部分时 就是回文数
	p := 0
	for x > p {
		p = p * 10 + x % 10
		x = x / 10
	}

	// 如果是奇数 将反转数/10 用于去除中位数
	return x == p || x == p/10
}

func main()  {
	fmt.Println(isPalindrome(101))
}

