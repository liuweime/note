# longest common prefix | 最长公共前缀



## 题目

编写一个函数来查找字符串数组中的最长公共前缀。 如果不存在公共前缀，返回空字符串 ""。

示例 1:

> 输入: ["flower","flow","flight"] 输出: "fl"

示例 2:

> 输入: ["dog","racecar","car"] 输出: ""

说明: 所有输入只包含小写字母 a-z 。

## 分析

**方法一：暴力循环法** 思路是循环整个数组及其内部的字符串，比对出相同的前缀；具体做法是取出数组第一个元素，循环该元素的每个字符，将该字符与数组中其他元素一一对比，直到遇到不相等的字符，返回之前一直相等的字符即可。

时间复杂度：因为需要两重循环，因此时间复杂度是 O(mn)

空间复杂度：仅引入一个变量用于保存字符前缀，因此空间复杂度是 O(1)

**方法二：分治法**

思考：获取数组所有字符串的公共前缀，加上已知前 n -1 个字符串的公共前缀是 x，那么是不是只要比对出 x 和 第 n 个字符串之前的公共前缀就可以了？

那前 n - 1 个字符串的公共前缀同样可以是根据前 n - 2 个字符串与第 n - 1 个字符串比对得出。。。。最后就得出比较前两个字符串的前缀就可以得到整个字符串数组的字符前缀了。

时间复杂度：在最后比较两个字符串的前缀，需要循环字符串比较，时间复杂度是 O(m)，而n 个字符串数组需要迭代 n 次，因此时间复杂度也是 O(mn)

## 代码

**暴力循环法**

```Go
func longestCommonPrefix(strs []string) string {
    if len(strs) == 0 {
    return ""
  }
  commonPrefix := ""
  length := len(strs)

  for _, char := range strs[0] {
    prefix := commonPrefix + string(char)
    for i := 1; i < length; i++ {
      if strings.Index(strs[i], prefix) != 0 {
        return commonPrefix
      }
    }
    commonPrefix = prefix
  }

  return commonPrefix
}
```

**分治法**

```Go
func longestCommonPrefix(strs []string) string {
  strsLen := len(strs)
  if strsLen == 0 {
    return ""
  }
  if strsLen == 1 {
    return strs[0]
  }
  return findPrefix(strs, len(strs))
}

func findPrefix(strs []string, n int) string {
  var langStr, shortStr string
  if n > 2 {
    prefix := findPrefix(strs, n-1)
    langStr, shortStr = prefix, strs[n-1]
    if len(prefix) < len(strs[n-1]) {
      langStr, shortStr = strs[n-1], prefix
    }
    fmt.Printf("n=%d, prefix=%s, end=%s\n", n, prefix, strs[n-1])
  } else {
    langStr, shortStr = strs[0], strs[1]
    if len(strs[0]) < len(strs[1]) {
      langStr, shortStr = strs[1], strs[0]
    }
  }

  commonPrefix := ""
  for i := 0; i < len(shortStr); i++ {
    if shortStr[i] != langStr[i] {
      break
    }

    commonPrefix = commonPrefix + string(shortStr[i])
  }
  return commonPrefix
}
```