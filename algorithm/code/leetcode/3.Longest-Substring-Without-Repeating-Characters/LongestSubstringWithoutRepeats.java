public class LongestSubstringWithoutRepeats {

    public int run(String s) {

        String tmp = "";
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            if (tmp.length() == 0) {
                tmp = s.charAt(i) + "";
                continue;
            }

            if (tmp.indexOf(s.charAt(i)) > -1) {
                max = Math.max(max, tmp.length());
                tmp = tmp.substring(tmp.indexOf(s.charAt(i))+1) + s.charAt(i);
            } else {
                tmp = tmp + s.charAt(i);
            }
        }

        return Math.max(max, tmp.length());
    }

    // vbvh
    public int runOpt(String s) {
        int max = 0;
        int slow = 0;
        // 快指针先动
        for (int quick = 1; quick < s.length(); quick++) {
            // 这里移动临时指针 判断快慢指针是否相等
            for (int j = slow; j < quick; j++) {
                // 慢指针只有等于快指针时才移动
                if (s.charAt(quick) == s.charAt(j)) {
                    // 此时的字符串长度是快慢指针的距离
                    max = Math.max(max, quick - slow);
                    // 慢指针移动
                    slow = j + 1;
                    break;
                }
            }
        }

        return Math.max(max, s.length() - slow);
    }
}
