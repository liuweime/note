/**
 * 整数反转
 */
public class ReverseInteger {

    public int run(int x) {
        String s = new StringBuffer(String.valueOf(Math.abs(x))).reverse().toString();
        if (x < 0) {
            s = '-' + s;
        }
        int num;
        try {
            num = Integer.parseInt(s);
        } catch (NumberFormatException e) {
            num = 0;
        }
        return num;
    }

    /**
     * 字符串反转效率比较低
     * 直接对整数进行循环，
     * num = num * 10 + x%10;
     * x /= 10;
     * 注意判断上下越界情况
     *
     * @param x
     * @return
     */
    public int opt(int x) {
        int num = 0;
        while (x != 0) {
            int d = x % 10;
            x = x / 10;
            if (num > Integer.MAX_VALUE / 10 || (Integer.MAX_VALUE / 10 == num && d > 7)) {
                return 0;
            }
            if (num < Integer.MIN_VALUE / 10 || (Integer.MIN_VALUE / 10 == num && d < -8)) {
                return 0;
            }
            num = num * 10 + d;
        }

        return num;
    }
}
