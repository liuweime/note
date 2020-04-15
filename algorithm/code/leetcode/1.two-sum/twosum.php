<?php

class Solution {

    /**
     * @param Integer[] $nums
     * @param Integer $target
     * @return Integer[]
     */
    function twoSum($nums, $target) {
    	
    	$data = $nums;
    	sort($nums);
        $len = count($nums);


        for ($i = 0, $j = $len-1; $i < $j;) {
        	$sum = $nums[$i] + $nums[$j];

            if ($sum === $target) {

            	$tmpi = array_search($nums[$i], $data);
            	unset($data[$tmpi]);
            	$tmpj = array_search($nums[$j], $data);
                return [$tmpi, $tmpj];
            } 

            if ($sum > $target) {
                $j--;
            } else {
                $i++;
            }
        }
    }

    function twoSumOpt($nums, $target) {

    	$map = array_flip($nums);

    	$length = count($nums);
    	for($i = 0; $i < $length - 1; $i++) {
    		$num = $target - $nums[$i];
    		if (!empty($map[$num]) && $map[$num] != $i) {
    			return [$i, $map[$num]];
    		}
    	}
    	return false;
    }
}

$a = new Solution();
print_r($a->twoSumOpt([1,3,2,4], 6));
print_r($a->twoSumOpt([3,3,4,1], 6));
print_r($a->twoSumOpt([3,3], 6));