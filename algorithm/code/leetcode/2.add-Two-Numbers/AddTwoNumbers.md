# Add two numbers | 两数相加 

## 题目

给定两个非空链表，用来表示两个非负整数 。其中每个数据都以逆序方式存在，每个节点仅存储一位数字。请将两个数字相加，并以相同形式返回一个表示和的链表。

除数字0意外，其他均不会以0开头。

```text
输入：[3,2,4]  [7,7,1]
输出：[0,0,6]
原因：423+177=600
```

## 分析

由题例，逆序就是数字的顺序是反的，同时加也变成了从左向右依次相加，那么最首先想到的方法就是，迭代两个链表，将相加结果溢出部分累计到下一个节点的相加中，直接两个链表均迭代完毕，需要注意的是迭代完成后，如果进位值不为0，需要将进位值作为节点。

那么，这种方法的时间复杂度显然是`O(max(m,n))`

## 代码

```Go
func addTwoNumbers(l1, l2 *ListNode) *ListNode {
  var num int
  var node = new(ListNode)
  pointer := node
  for l1 != nil || l2 != nil {
    var l1Val, l2Val int
    if l1 != nil {
      l1Val = l1.Val
      l1 = l1.Next
    }
    if l2 != nil {
      l2Val = l2.Val
      l2 = l2.Next
    }
    val := l1Val + l2Val + num
    pointer.Next = &ListNode{Val: val % 10}
    pointer = pointer.Next
    num = val / 10
  }
  if num > 0 {
    pointer.Next = &ListNode{Val: num}
  }
  return node.Next
}
```