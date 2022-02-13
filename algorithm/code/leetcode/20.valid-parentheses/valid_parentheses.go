package main

import "fmt"

func isValid(s string) bool {
	sLen := len(s)
	if sLen % 2 != 0 {
		return false
	}
	closeMap := map[byte]byte{
		')': '(',
		']': '[',
		'}': '{',
	}
	if _,ok := closeMap[s[0]]; ok {
		return false
	}
	stock := make([]byte, sLen)
	top := 0
	stock[top] = s[0]
	for i := 1; i < sLen; i++ {
		// 找到关闭括号
		if open, ok := closeMap[s[i]]; ok {
			// 括号匹配成功 出栈
			if top != -1 && open == stock[top] {
				top--
			} else {
				// 找出关闭括号 且没有对应 说明不匹配
				return false
			}
		} else {
			top++
			stock[top] = s[i]
		}
	}
	if top != -1 {
		return false
	}
	return true
}

func main() {
	s := "(){}}{"
	fmt.Println(isValid(s))
}
