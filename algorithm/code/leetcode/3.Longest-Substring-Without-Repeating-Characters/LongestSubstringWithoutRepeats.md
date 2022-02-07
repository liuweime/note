# Longest substring without repeating characters | 无重复字符的最长子串

## 题目

给定一个字符串`s`，获取其中无重复字符的最长子串的长度

```text
输入：abcabcbb
输出：3（abc是最长子串）

输入：bbbbbbb
输出：1

输入：pwwkew
输出：3(wke)

输入：
输出：0（输入是一个空字符串）
```

## 分析

**方法一**

最简单的方法就是循环字符串s，使用一个变量保存无重复字符的子串，每次循环判断当前字符在子串中是否存在，如果存在就将重复字符之后的字符串+当前字符作为新的子串，并使用一个变量记录子串长度，每次更新成功最大。

该方法需要循环整个字符串，因此时间复杂度是 `O(len(s))`

**方法二**

使用指针法，设置两个指针，相关于一个指向子串的首位置p1，一个指向子串的尾位置p2，p2 在字符串s中循环移动；针对每次循环p2指向的字符判断在p1-p2之间的子串是否有重复，如果重复就将p1移动到重复的后一位置；同样需要一个变量保存最大子串长度，每次移动p1均需要更新；注意，如果p2循环完毕，指向的字符在 p1-p2间均无重复，那么最长长度就需要和p1到s最后字符长度比较一下大小。

## 代码

方法一：

```Go
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
```

方法二：

```Go
func LongestSubstringWithoutRepeatingCharacters(s string) int {
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
```