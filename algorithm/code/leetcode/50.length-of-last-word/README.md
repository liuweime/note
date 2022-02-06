# Length of last word | 最后一个单词长度

### 题目

给定一个字符串，字符串由若干单词组成，单词之间使用空格分隔，返回字符串中最后一个单词的长度，如果没有返回0

例子：

```text
输入：Hello World
输出：5 
```

### 分析

一个直观的思路，从开始遍历字符串，遇到一个字符，加1，当遇到空格时，将字符长度置0，代码大致如下：

```go
function lengthOfLastWord(string $s) :int
{
    $length = strlen($s);
    $wordLen = 0;
    for ($i = 0; $i < $length; $i++) {
        if ($s[$i] == ' ') {
            $wordLen = 0;
        } else {
            $wordLen++;
        }
    }
    return $wordLen;
}
```

这种常规情况是满足要求的，如据的例子`Hello world`，但是这里需要考虑一些特殊情况，如全是空格，多个空格分隔单词，字符串最后全是空格等，上面的代码就需要进行改造。

首先考虑，输入一个空格进来，那判断只有一个字符，且是空格，直接可以返回0；再考虑多个空格的情况，修改一个重新单词长度逻辑即可，如果遍历的字符是空格，直接下一次循环，知道一个非空格的字符；当遇到一个非空格的字符，如果该字符的前一个字符是空格，那么就将目前的字符长度置0；修改后的代码如下：

### 代码

```go
function lengthOfLastWord(string $s) :int
{
    $length = strlen($s);
    if ($length == 1 && $s[0] == ' ') {
        return 0;
    }
    $wordLen = 0;
    for ($i = 0; $i < $length; $i++) {
        if ($s[$i] == ' ') {
            continue;
        }
        if ($s[$i-1] == ' ') {
            $wordLen = 0;
        }
        $wordLen++;
    }
    return $wordLen;
}
```