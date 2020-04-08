package main

import (
	"fmt"
)

func BubbleSort(data []int)  {
	for i := 0; i < len(data); i++ {
		for j := 0; j < len(data) - 1 - i; j++ {
			if data[j] > data[j+1] {
				data[j], data[j+1] = data[j+1], data[j]
			}
		}
	}
}

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