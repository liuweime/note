# valid parentheses | 有效的括号

## 题目

给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。

有效字符串需满足：

左括号必须用相同类型的右括号闭合。 左括号必须以正确的顺序闭合。

## 分析

经典题目，考栈的使用，在遇到右括号前，左括号一直入栈；遇到右括号后，出栈与左括号比较是否闭合；显然，两两相对才能闭合，因此字符串长度必然是偶数；可以用map来存储括号的闭合关系

## 代码

```Go
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
```