<?php


class Solution {

    /**
     * @param String $s
     * @return Integer
     */
    function lengthOfLongestSubstring($s) {

        $length = strlen($s);
        $max = 0;
        $str = '';
        for($i = 0; $i < $length; $i++) {
        	$char = $s[$i];
        	if (empty($str)) {
        		$str .= $char;
        	} else {
        		if (strpos($str, $char) !== false) {
	        		$str = substr($str, strpos($str, $char)+1) . $char;
	        	} else {
	        		$str .= $char;
	        	}
        	}
        	
        	$max = strlen($str) > $max ? strlen($str) : $max;
        }

        return $max ;
    }
}

$s = new Solution();
echo $s->lengthOfLongestSubstring(" "), PHP_EOL;     // 1
echo $s->lengthOfLongestSubstring("axbxbxcdxd"), PHP_EOL; // 4
echo $s->lengthOfLongestSubstring("xxb"), PHP_EOL; // 2
echo $s->lengthOfLongestSubstring("xb"), PHP_EOL; // 2
echo $s->lengthOfLongestSubstring('bbbbb'), PHP_EOL; // 1
echo $s->lengthOfLongestSubstring('pwwkew'), PHP_EOL; // 3
