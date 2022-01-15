<?php

/**
 * 给定一个数组 计算出该数组的动态和
 * 动态和：
 * array: [-1, 0,2,5,7]
 * result: [-1, -1, 1, 6, 13]
 *
 * Class RunningSumOfDArray
 */
class RunningSumOfDArray
{

    public function run(array $nums) : array
    {
        $length = count($nums);
        for ($i = 1; $i < $length; $i++) {
            $nums[$i] = $nums[$i-1] + $nums[$i];
        }
        return $nums;
    }
}


print_r((new RunningSumOfDArray())->run([3,1,2,10,1]));