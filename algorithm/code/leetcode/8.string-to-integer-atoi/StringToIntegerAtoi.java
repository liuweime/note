public class StringToIntegerAtoi {

    public int run(String str) {
        // 空字符串直接返回
        if (str.length() == 0)  return 0;

        // 过滤空格
        int i = 0;
        while (i < str.length() && str.charAt(i) == ' ') {
            i++;
        }
        // 是一个空字符串 或 (过滤空格之后)首字母非数字
        // > 57 说明也不会是 + - 等符合
        if (i >= str.length() || str.charAt(i) > 57) {
            return 0;
        }
        // 判断可能存在的 + - 符合
        int num = 0;
        boolean flag = true;
        if (str.charAt(i) < 48) {
            if (str.charAt(i) == '-') {
                flag = false;
            } else if (str.charAt(i) == '+') {
                flag = true;
            } else {
                return 0;
            }
            i++;
        } else {
            num = num * 10 + getNumber((int)str.charAt(i));
            i++;
        }

        // 开始进行转换
        while (i < str.length()) {
            // 非数字
            if (str.charAt(i) > 57 || str.charAt(i) < 48)  {
                break;
            }

            // 注意判断越界情况
            if (Integer.MAX_VALUE / 10 < num || (Integer.MAX_VALUE / 10 == num && getNumber((int)str.charAt(i)) > 7)) {
                if (flag) {
                    return Integer.MAX_VALUE;
                }
                return Integer.MIN_VALUE;
            }

            num = num * 10 + getNumber((int)str.charAt(i));
            i++;
        }

        return flag ? num : -num;
    }

    /**
     * 获取字符对应的整数值
     */
    private int getNumber(int num) {
        return num - 48;
    }
}
