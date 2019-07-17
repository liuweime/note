public class LongestPalindrome {

    /**
     * 中心扩散法求最长回文子串
     * 时间复杂度为 n*n
     */
    public String run(String s) {
        if (s.length() == 1) {
            return s;
        }

        String str = "";
        for (int i = 0; i < s.length() - 1; i++) {

            // 偶数串
            int left = i;
            int right = i;
            while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                // 从元素位置开始左移
                left--;
                // 右移
                right++;
            }
            if ((right - left - 1) > str.length()) {
                str = s.substring(left + 1, right);
            }

            // 奇数串
            left = i;
            right = i + 1;
            while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                // 从元素位置开始左移
                left--;
                // 右移
                right++;
            }
            if ((right - left - 1) > str.length()) {
                str = s.substring(left + 1, right );
            }
        }

        return str;
    }

}
