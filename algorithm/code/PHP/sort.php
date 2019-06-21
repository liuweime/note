<?php
/**
 * 几种常见的排序算法
 *
 * @Author: Marte
 * @Date:   2018-07-25 10:19:43
 * @Last Modified by:   Marte
 * @Last Modified time: 2018-07-25 11:19:56
 */

/**
 * 选择排序 O(n^2)
 * @param  array  $data 待排序列表
 * @return array
 */
function select_sort(array $data) : array
{
    $len = count($data);
    for($i = 0; $i < $len; $i++) {
        $min = $i;

        // 获取最小元素
        for($j = $i+1; $j < $len; $j++) {

            // 比较 n(n-1)/2次
            if ($data[$min] > $data[$j]) {
                $min = $j;
            }
        }

        // 交换 n次
        $tmp = $data[$i];
        $data[$i] = $data[$min];
        $data[$min] = $tmp;
    }

    return $data;
}

/**
 * 插入排序 O(n^2)
 *
 * @param array $data
 * @param array
 * @return array
 */
function insert_sort(array $data) : array
{

    $len = count($data);
    for ($i = 1; $i <= $len - 1; $i++) {
        $tmp = $data[$i];
        for ($j=$i; $j > 0 ; $j--) {
            if ($data[$j-1] < $tmp) {
                break;
            }
            $data[$j] = $data[$j-1];
        }
        $data[$j] = $tmp;
    }

    return $data;
}

/**
 * 希尔排序
 *
 * @param  array $data 待排序数组
 * @return array
 */
function shell_sort(array $data) : array
{
    $len = count($data);
    $d = (int)floor($len / 2);

    while($d > 0) {

        // 插入排序
        for($i = $d; $i <= $len - 1; $i++) {
            
            for($j = $i; $j >= $d && $data[$j-$d] < $tmp; $j -= $d) {
                $tmp = $data[$i];
                $data[$j] = $data[$j-$d];
                $data[$j] = $tmp;
            }
        }

        $d = (int)floor($d/2);
    }

    return $data;
}

/**
 * 冒泡排序
 *
 * @param  array $data
 * @return array
 */
function bubble_sort(array $data) : array
{
    $len = count($data);

    for($i = 0; $i < $len-1; $i++) {

        for($j = 0; $j < $len-1-$i; $j++) {

            // 较大数一直冒泡 到最后
            if($data[$j] > $data[$j+1]) {
                $tmp = $data[$j];
                $data[$j] = $data[$j+1];
                $data[$j+1] = $tmp;
            }
        }
    }

    return $data;
}

function bubble_sort_second(array $data)
{
    $len = count($data);
    $flag = true;

    for($i = 0; $i < $len-1 && $flag; $i++) {

        $flag = false;
        for($j = 0; $j < $len-1-$i; $j++) {

            // 较大数一直冒泡 到最后
            if($data[$j] > $data[$j+1]) {
                $tmp = $data[$j];
                $data[$j] = $data[$j+1];
                $data[$j+1] = $tmp;

                $flag = true;
            }
        }
    }

    return $data;
}

/*
1、从数列中挑出一个元素，称为"基准"（pivot），
2、重新排序数列，所有比基准值小的元素摆放在基准前面，
   所有比基准值大的元素摆在基准后面（相同的数可以到任何一边）。在这个分区结束之后，
   该基准就处于数列的中间位置。这个称为分区（partition）操作。
3、递归地（recursively）把小于基准值元素的子数列和大于基准值元素的子数列排序。
 */

/**
 * 快速排序 非原地排序 递归法
 *
 * @param  array $data
 * @return array
 */
function quick_sort(array $data) : array
{
    $len = count($data);
    if ($len <= 1) {
        return $data;
    }

    $pivot = $data[0];
    $left = $right = [];
    for($i = 1; $i < $len; $i++) {
        if ($data[$i] > $pivot) {
            $right[] = $data[$i];
        } else {
            $left[] = $data[$i];
        }
    }

    $left = quick_sort($left);
    $right = quick_sort($right);

    return array_merge($left, [$pivot], $right);
}

/**
 * 快速排序 原地排序 递归法
 *
 * @param  array   $data
 * @param  integer $start
 * @param  integer $end
 */
function quick_sort_in_place(&$data, int $start, int $end)
{
    if ($start >= $end) {
        return ;
    }

    $left = $start;
    $right = $end - 1;
    $pivot = $data[$end];

    // 和基准元素比较
    // 将较小元素置换到左侧
    // 将较大元素置换到右侧
    while($left < $right) {
        // 由于最后元素作为基准 所以先找左侧
        // 找到左侧中较大的元素
        while($data[$left] < $pivot && $left < $right) {
            $left++;
        }
        // 找到右侧中较小元素
        while($data[$right] >= $pivot && $left < $rig=ht) {
            $right--;
        }
        if ($left === $right) {
            break;
        }
        // 进行置换
        $tmp = $data[$left];
        $data[$left] = $data[$right];
        $data[$right] = $tmp;
    }
    // 找到的left趋于end left=right
    // 互换后的data.left应该小于end 保证左分区中任意一数小于右分区
    // 否则进行置换
    if ($data[$left] >= $data[$end]) {
        $tmp = $data[$left];
        $data[$left] = $data[$end];
        $data[$end] = $tmp;
    }

    // 递归左侧
    if($left) {
        quick_sort_two($data, $start, $left);
    }
    // 递归右侧
    quick_sort_two($data, $left + 1, $end);
}

/*
Wiki中对归并操作的说明：
1、申请空间，使其大小为两个已经排序序列之和，该空间用来存放合并后的序列
2、设定两个指针，最初位置分别为两个已经排序序列的起始位置
3、比较两个指针所指向的元素，选择相对小的元素放入到合并空间，并移动指针到下一位置
4、重复步骤3直到某一指针到达序列尾
5、将另一序列剩下的所有元素直接复制到合并序列尾
代码实现：
递归：
1、递归将无序序列分成左右区间，直到最小区间
2、左右区间元素比较大小，将较小元素放入临时区间，直到其中一个序列末尾
3、合并剩下区间到临时空间
4、将临时空间替换到无序序列的相同位置上
先递归分区间，在归并序列；最先归并的序列必是最小区间
 */
/**
 * 归并排序 需要额外空间 O(nlogn)
 *
 * @param array $data
 * @param int $start
 * @param int $end
 * @return void [type] [description]
 */
function merge_sort(array &$data, int $start, int $end)
{
    if ($start >= $end) {
        return ;
    }
    // 计算左右区间
    $middle = (int)floor(($start + $end) / 2);
    $intervalFirstStart = $start;
    $intervalFirstEnd = $middle;
    $intervalSecondStart = $middle + 1;
    $intervalSecondEnd = $end;

    // 递归
    merge_sort($data, $intervalFirstStart, $intervalFirstEnd);
    merge_sort($data, $intervalSecondStart, $intervalSecondEnd);

    $item = [];
    $i = $intervalFirstStart;
    // 比较较小元素 存入临时空间
    while ($intervalFirstStart <= $intervalFirstEnd
            && $intervalSecondStart <= $intervalSecondEnd) {
        $item[$i++] = $data[$intervalFirstStart] < $data[$intervalSecondStart] ? $data[$intervalFirstStart++] : $data[$intervalSecondStart++];
    }

    // c
    while ($intervalFirstStart <= $intervalFirstEnd) {
        $item[$i++] = $data[$intervalFirstStart++];
    }
    while ($intervalSecondStart <= $intervalSecondEnd) {
        $item[$i++] = $data[$intervalSecondStart++];
    }
    // 替换到无序序列相同位置中
    $len = count($data);
    for ($j=$start; $j <= $end; $j++) {
        $data[$j] = $item[$j];
    }
}



$a = [2,10,3,5,2,5,3,6,3,8,3];
$s = bubble_sort_second($a);

echo implode($a, ',') , PHP_EOL;
echo implode($s, ',') , PHP_EOL;
