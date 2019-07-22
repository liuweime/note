public class PalindromeNumber {

    /**
     * 回文的左右相等
     * 1、以字符串做处理 左指针等于右指针 缺点是需要多余空间
     * 2、不做字符串处理 使用左右指针 缺点是取对应位置的数字效率不高
     * 3、按照回文特点，右侧内容翻转以后应该等于左侧
     */
    public boolean run(int x) {
        if (x < 0 || (x != 0 && x % 10 == 0)) return false;

        int num = 0;
        while (num < x) {
            num = num * 10 + x % 10;
            x = x/10;
        }

        return num == x || num/10 == x;
    }

    public boolean low(int x) {
        if (x < 0) return false;

        int start =  getLength(x);
        int end = 1;
        while (start >= end) {
            int startNum = getNumByIndex(x, start);
            int endNum = getNumByIndex(x, end);
            if (endNum != startNum) {

                return false;
            }
            start--;
            end++;
        }

        return true;
    }

    private int getNumByIndex(int x, int index) {
        return x / (int)Math.pow(10, index - 1) % 10;
    }

    private int getLength(int x) {
        int[] size = {9, 99, 999, 9999, 99999, 999999, 9999999,
                99999999, 999999999, Integer.MAX_VALUE};
        int i = 0;
        while (x > size[i]) {
            ++i;
        }

        return i + 1;
    }
}
