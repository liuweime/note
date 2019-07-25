import java.util.HashMap;

public class LongestCommonPrefix {

    /**
     * 嗯 写的比较乱 还可以改进一点
     */
    public String run(String[] strs) {
        if (strs.length == 0) return "";
        if (strs.length == 1) return strs[0];

        String prefix = strs[0];
        int num = 1;
        for (int i = 1; i < strs.length; i++) {
            String s = strs[i];
            int len = s.length();
            while (len > 0) {
                if (prefix.length() >= len) {
                    if (prefix.indexOf(s.substring(0, len)) == 0) {
                        prefix = s.substring(0, len);
                        num++;
                        break;
                    }
                }
                len--;
            }
        }
        if (num == strs.length) {
            return prefix;
        }

        return "";
    }

    /**
     *  事实上并不需要map
     */
    public String old(String[] strs) {
        if (strs.length == 0) return "";
        if (strs.length == 1) return strs[0];

        HashMap<String, Integer> map = new HashMap<>();
        String prefix = "";
        int max = 0;
        for (int i = 0; i < strs.length; i++) {
            String s = strs[i];
            int len = s.length();

            while (len > 0) {
                String k = s.substring(0, len);
                if (map.containsKey(k)) {
                    if (map.get(k) + 1 > max) {
                        max = map.get(k) + 1;
                        prefix = k;
                    }
                    map.put(k, map.get(k) + 1);
                } else {
                    max = Math.max(max, 1);
                    map.put(k, 1);
                }
                len--;
            }
        }
        if (max != strs.length) {
            return "";
        }

        return prefix;
    }
}
