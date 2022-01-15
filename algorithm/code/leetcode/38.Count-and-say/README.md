# Count And Say | 外观数列

> 「外观数列」是一个整数序列，从数字 1 开始，序列中的每一项都是对前一项的描述。前五项如下：
> > 1.     1
> > 2.     11
> > 3.     21
> > 4.     1211
> > 5.     111221
>
> `1` 被读作  `"one 1" ` ("一个一") , 即 11。
> `11 `被读作 `"two 1s"` ("两个一"）, 即 21。
> `21 `被读作 `"one 2"`,` "one 1"` （"一个二" ,  "一个一") , 即 1211。
> 给定一个正整数 `n（1 ≤ n ≤ 30）`，输出外观数列的第 n 项。
> 注意：整数序列中的每一项将表示为一个字符串。
> **示例 1:**
>
> > 输入: 1
> > 输出: "1"
> > 解释：这是一个基本样例。
>
> **示例 2:**
>
> > 输入: 4
> > 输出: "1211"
> > 解释：当 n = 3 时，序列是 "21"，其中我们有 "2" 和 "1" 两组，"2" 可以读作 "12"，也就是出现频次 = 1 而 值 = 2；类似 "1" 可以读作 "11"。所以答案是 "12" 和 "11" 组合在一起，也就是 "1211"。



理解了题目的意思后，题目还是比较简单的。每一项是对前一项的描述，第一想到的就是使用递归了；拿到前一项的值后，就需要描述该值，描述的结果就是当前项目的值，这里描述的规则也比较简单，使用一个`for`可以完成

代码如下：

```go
func countAndSay(n int) string  {
	if n <= 1 {
		return "1"
	}
	list := map[int]string{
		1: "1",
		2: "11",
		3: "21",
		4: "1211",
		5: "111221",
	}
	if v, ok := list[n]; ok {
		return v
	}
	res := countAndSay(n-1)
	pointer := res[0]
	length := len(res)
	count := 1
	var str strings.Builder
	for i := 1; i < length; i++ {
		if pointer == res[i] {
			count++
		} else {
			str.WriteString(strconv.Itoa(count))
			str.WriteByte(pointer)
			pointer = res[i]
			count = 1
		}
	}
	str.WriteString(strconv.Itoa(count))
	str.WriteByte(pointer)
	return str.String()
}
```

