package main

import "fmt"

// 给定一个字符串，该字符串由若干单词组成，并使用空格分割，
// 求最后一个单词的长度，如果没有返回0
func lengthOfLastWord(str string) int {
	strLen := len(str)
	if strLen == 1 && string(str[0]) == " " {
		return 0
	}
	wordLen := 0
	for i := 0; i <= strLen - 1; i++ {
		// 不能遇到空格就将计数置空
		// 如果最后一个字符串后有空格
		// 会导致计数错误
		if string(str[i]) == " " {
			continue
		}

		// 如果遇到一个非空格字符
		// 该字符串的前一个字符是空格
		// 就可以将前面计数重置并重新计数
		if string(str[i-1]) == " " {
			wordLen = 0
		}

		wordLen += 1
	}

	return wordLen
}

func main () {
	fmt.Println(lengthOfLastWord(" w  wl "))
}
