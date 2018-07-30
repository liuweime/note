<?php
/**
 * @Author: Marte
 * @Date:   2018-07-30 19:31:10
 * @Last Modified by:   Marte
 * @Last Modified time: 2018-07-30 20:50:49
 */

function insert_sort(array $list) : array
{
    $data = [];
    while (!empty($list)) {

        $tmp = array_shift($list);
        $len = count($data);

        if (empty($data) || $data[$len-1] < $tmp) {

            array_push($data, $tmp);
            continue;
        }

        for($i = $len - 1; $i >= 0; $i--) {

            if ($data[$i] > $tmp) {

                $data[$i+1] = $data[$i];
                continue;
            }
            break;
        }

        $data[$i+1] = $tmp;
    }

    return $data;
}

function insert_sort_two(array $data) : array
{
    $len = count($data);
    for ($i=1; $i <= $len - 1; $i++) {
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

$data = insert_sort_two([4,3,6,2,5,1,8,10,7]);
print_r($data);
