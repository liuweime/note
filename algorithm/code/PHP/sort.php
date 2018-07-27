<?php
/**
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
function mysort(array $data) : array
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

$argv = $_SERVER['argv'];
$data = explode(',', $argv[1]);
print_r($argv[1]);
$data = mysort($data);
echo PHP_EOL;
print_r(implode(',', $data));