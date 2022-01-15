<?php


class MultiplesOfNumber
{

    /**
     * @param int $number
     * @return int
     */
    public function solution(int $number) : int
    {
        if ($number < 0) {
            return  0;
        }
        $total = 0;
        $a = 3;$b = 5;
        while ($a < $number || $b < $number) {
            if ($a < $number) {
                $total += $a;
                $a += 3;
            }
            if ($b < $number) {
                if ($b % 3 != 0) {
                    $total += $b;
                }
                $b += 5;
            }
        }
        return $total;
    }

}