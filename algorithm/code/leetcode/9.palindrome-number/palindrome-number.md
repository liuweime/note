# palindrome-number | 回文数

题目描述

> 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
>
> 示例 1:
> > 输入: 121
> > 输出: true
>
> 示例 2:
> > 输入: -121
> > 输出: false
> > 解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
>
> 示例 3:
> > 输入: 10
> > 输出: false
> > 解释: 从右向左读, 为 01 。因此它不是一个回文数。
> 
> 进阶:
> 你能不将整数转为字符串来解决这个问题吗？



思路一：将数字转换为字符串进行操作，较为简单

```go
func IsPalindrome(x int) bool {
	if x < 0 {
		return false
	}
	if x < 10 {
		return true
	}
	y := strconv.Itoa(x)
	length := len(y)
	for i, j := 0, length - 1; i < j; i, j = i + 1, j - 1 {
		if y[i] != y[j] {
			return false
		}
	}
	return true
}
```



思路二利用整数反转，回文串反转后的数字与原来相等，反转的过程参考`reverse-integer 整数反转`，考虑到反转的溢出问题，可以只反转一般，根据回文的特点，偶数位时反转的一半等于剩余的数字，奇数位时反转的一半等于剩余数除以10的结果，代码如下：

```go
func IsPalindrome(x int) bool  {
  if (x < 0) || (x != 0 && x % 10 == 0) {
		return false
	}
	y := 0
	for x > y {
		y = y * 10 + x % 10
		x = x / 10
	}
	if x == y || y / 10 == x {
		return true
	}
	return  false
}
```

