<?php

/**
 * 好数对
 * 输入一个整数数组 nums
 * 如果一对(i,j) 满足 nums[i] == nums[j] && i < j，则称这对为好数对
 * 返回好数对的数量
 * 例：1,1,1,1
 * [1,3,2,1,4,2] 好数对 2 (0,3) (2,5)
 *
 * Class NumIdenticalPairs
 */
class NumIdenticalPairs
{
    public function run(array $nums) :int
    {
        $length = count($nums);
        if ($length < 2) {
            return 0;
        }
        sort($nums);
        $count = 0;
        for($i = 0, $j = $i + 1; $i < $length;) {
            if ($j < $length && $nums[$i] == $nums[$j]) {
                $count++;
                $j++;
            } else {
                $i++;
                $j = $i+1;
            }
        }

        return $count;
    }

    public function runOpt(array $nums) :int
    {

    }
}


echo (new NumIdenticalPairs())->run([1]), PHP_EOL;