package main

import "fmt"

func romanToInt(s string) int {
	roman := map[byte]int{
		'I': 1,
		'V': 5,
		'X': 10,
		'L': 50,
		'C': 100,
		'D': 500,
		'M': 1000,
	}
	length := len(s)
	if length == 1 {
		return roman[s[0]]
	}
	var number int
	var prev int
	for i := length-1; i >= 0; i-- {
		step := s[i]
		if val, status := roman[step]; status == true {
			if val < prev {
				number = number - val
			} else {
				number = number + val
			}
			prev = val
		}
	}
	return number
}

func main()  {
	fmt.Println(romanToInt("II"))
	fmt.Println(romanToInt("VI"))
	fmt.Println(romanToInt("IV"))
	fmt.Println(romanToInt("IX"))
	fmt.Println(romanToInt("MCMXCIV"))
	fmt.Println(romanToInt("XXVII"))
}
