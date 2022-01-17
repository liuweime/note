package main

import (
	"fmt"
)

// bubble sort
// O(n^2)
func BubbleSort(data []int)  {
	for i := 0; i < len(data); i++ {
		for j := 0; j < len(data) - 1 - i; j++ {
			if data[j] > data[j+1] {
				data[j], data[j+1] = data[j+1], data[j]
			}
		}
	}
}

// 冒泡排序的一个问题
// 即使数组后面是有序的 仍需要冒泡上去
// 如果判断后面有序 就停止冒泡 可以减少循环次数
// 如果每次比较 后面值均比前面大，那显然是有序的
func BubbleSortOpt(data []int) {
	flag := true
	for i := 0; i < len(data) && flag; i++ {
		flag = false
		for j := 0; j < len(data) - 1 - i; j++ {
			if data[j] > data[j+1] {
				data[j], data[j+1] = data[j+1], data[j]
				flag = true
			}
		}
	}
}

func main() {
    data := []int{4,7,1,5,8,3,9,1,6,3,0,3,4}
    fmt.Println(data)
    BubbleSortOpt(data)
    fmt.Println(data)
}