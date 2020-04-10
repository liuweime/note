package main

import (
	"fmt"
)

/**
 * 选择排序
 * 选择一个元素，并将其和未排序的列表中进行比对，与其中的最小元素进行交换
 * 每次循环都会找出当前无序列中的最小元素，因此循环结束后，整个列表就是有序的
 */
 func Selection(data []int)  {
	length := len(data)
	for i := 0; i < length; i++ {
		min := i
		for j := i + 1; j < length; j++ {
			if data[min] > data[j] {
				min = j
			}
		}
		data[i], data[min] = data[min], data[i]
	}
}


func main() {
    data := []int{4,7,1,5,8,3,9,1,6,3,0,3,4}
    fmt.Println(data)
    Selection(data)
    fmt.Println(data)
}