package main



// 如果某一个位置前元素都是有序的
// 那么只需要将这个元素查到之前有序列表中合适的位置即可
// 减少了比较和交换次数
// 具体实现就是，元素 与 左侧元素比较 如果小于就移动左侧元素
func InsertSort(data []int) {

	for i := 1; i< len(data); i++ {
		j := i
		tmp := data[i]
		for; j > 0; j-- {
			if data[j-1] > tmp {
				data[j] = data[j-1]
			}
		}
		data[j] = tmp
	}	
}


func main() {
    data := []int{4,7,1,5,8,3,9,1,6,3,0,3,4}
    fmt.Println(data)
    InsertSort(data)
    fmt.Println(data)
}