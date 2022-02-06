package main

import "fmt"

type ListNode struct {
	Val int
	Next *ListNode
}

func addNode(node *ListNode, num int) {
	pointer := node
	for pointer.Next != nil {
		pointer = pointer.Next
	}

	tmpNode := new(ListNode)
	tmpNode.Val = num

	pointer.Next = tmpNode
}

func eachNode(node *ListNode) {
	head := node
	for head.Next != nil {
		fmt.Printf("%d ", head.Val)
		head = head.Next
	}
	fmt.Printf("%d ", head.Val)
}

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

func main()  {
	l1 := new(ListNode)
	l1.Val = 3
	addNode(l1, 2)
	addNode(l1, 3)
	eachNode(l1)
fmt.Println()
	l2 := new(ListNode)
	l2.Val = 7
	addNode(l2, 7)
	addNode(l2, 3)
	eachNode(l1)
	fmt.Println()
	eachNode(l2)

	fmt.Println()
	node := addTwoNumbers(l1,l2)
	fmt.Println(node.Val)
	eachNode(node)
}