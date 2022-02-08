package main

import (
	"strings"
)
//abcabcbb
func LongestSubstringWithoutRepeatingCharactersOpt(s string) int {
	if len(s) <= 1 {
		return len(s)
	}

	p1 := 0
	p2 := 1
	subStrMax := 0
	for ; p2 < len(s); p2++ {
		for i := p1; i < p2; i++ {
			if s[i] == s[p2] {
				if subStrMax < (p2 - p1) {
					subStrMax = p2 - p1
				}
				p1 = i + 1
				break
			}			
		}
	}
	// p2 循环完毕s均未重复
	if subStrMax < len(s) - p1 {
		return len(s) - p1
	}

	return subStrMax
}

func LongestSubstringWithoutRepeatingCharacters(s string) int {
	if len(s) <= 1 {
		return len(s)
	}

	strLen := len(s)
	subStr := ""
	maxStrLen := 0
	for i := 0; i < strLen; i++ {
		if index := strings.Index(subStr, string(s[i])); index >= 0 {
			if maxStrLen < len(subStr) {
				 maxStrLen = len(subStr)
			}
			subStr = subStr[index+1:len(subStr)] + string(s[i])
		} else {
			subStr = subStr + string(s[i])
		}
	}

	if maxStrLen > len(subStr) {
		return maxStrLen
	}

	return len(subStr)
}

//func main()  {
//	//s := "abd"
//	//index := strings.Index(s, "a")
//	//fmt.Println(index, strings.Contains(s, "a"))
//	fmt.Println(LongestSubstringWithoutRepeatingCharacters("vvvvv"))
//	fmt.Println(LongestSubstringWithoutRepeatingCharacters("pwwekw"))
//	fmt.Println(LongestSubstringWithoutRepeatingCharacters(""))
//	fmt.Println(LongestSubstringWithoutRepeatingCharacters("abcabca"))
//	fmt.Println(LongestSubstringWithoutRepeatingCharacters("fffaaaa"))
//	fmt.Println(LongestSubstringWithoutRepeatingCharacters("acdfghja"))
//	fmt.Println(LongestSubstringWithoutRepeatingCharacters("aav"))
//	fmt.Println(LongestSubstringWithoutRepeatingCharacters("dvdf"))
//	fmt.Println(LongestSubstringWithoutRepeatingCharactersOpt("abcabcbb"))
//}