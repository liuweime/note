<?php


class ReverseWords
{


    /**
     * input=hello world!
     * output=olleh !dlrow
     * @param string $str
     * @return string
     */
    public function run(string $str) :string
    {
        $len = strlen($str);
        $output = '';
        $tmp = [];
        for ($i = 0; $i < $len; $i++) {
            if ($str[$i] == ' ') {
                $output .= implode('', $tmp) . ' ';
                $tmp = [];
            } else {
                array_unshift($tmp, $str[$i]);
            }
        }
        if (!empty($tmp)) {
            $output .= implode('', $tmp) . ' ';
            $tmp = [];
        }
        return trim($output);
    }

}
echo 1;
var_dump((new ReverseWords())->run('Hello World!'));