<?php

/**
 * 左旋转字符串
 * 将字符串左侧前n个字符转移到字符串尾部
 * 例如：
 * abcedfg  2  => cedfgab
 * ghtrkddfawsd 5  => ddfawsdghtrk
 *
 * Class ReverseLeftWords
 */
class ReverseLeftWords
{
    public function run($s, $n)
    {
        $length = strlen($s);
        if ($length == $n) {
            return $s;
        }
        $str = '';
        for ($j = $n; $j < $length; $j++) {
            $str .= $s[$j];
        }
        for ($i = 0; $i < $n; $i++) {
            $str .= $s[$i];
        }

        return $str;
    }
}

echo (new ReverseLeftWords())->run('lrloseumgh', 6);