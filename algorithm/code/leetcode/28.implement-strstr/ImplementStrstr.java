public class ImplementStrstr {

    /**
     * KMP 算法
     * 嗯 没怎么看懂，等会才看
     */
    public int run(String haystack, String needle) {
       return -1;
    }

    /**
     * 最容易想到的方法
     * 时间复杂度 O((m-n)*n) 指针移动 m-n 次，比较 n
     */
    public int old(String haystack, String needle) {
        if (needle.length() == 0) return 0;

        int pointer = 0;
        int step = needle.length();

        while (pointer <= haystack.length() - step) {
            if (haystack.substring(pointer, pointer+step).equals(needle)) {
                return pointer;
            }
            pointer++;
        }

        return -1;
    }
}
