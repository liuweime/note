package main

import "fmt"

// 3121 -> 1213 ((1 * 10 + 2 ) * 10 + 1) * 10 + 3   
func reverse(x int) int {
	maxInt := 1<<31 - 1
	minInt := -1 << 31	
	y := 0
	for x != 0 {
		num := x % 10
		if y > maxInt/10 || (y == maxInt/10 && num > 7) {
			return 0
		}
		if y < minInt/10 || (y == minInt/10 && num < -8) {
			return 0
		}
		y = y * 10 + num
		x = x / 10
	}

	return y
}


func main() {
	
	x := 1534236469
	fmt.Println(x)
	res := reverse(x)
	fmt.Println(res)
}