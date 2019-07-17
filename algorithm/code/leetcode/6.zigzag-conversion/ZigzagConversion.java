public class ZigzagConversion {

    public String run(String s, int numRows)
    {
        char[] list = s.toCharArray();
        String str = "";
        for (int i = 0; i < numRows; i++) {
            int start = i;
            int d = Math.max(2 * (numRows - i) - 2, 1);
            while (start < s.length()) {
                if (list[start] != '#') {
                    str += list[start];
                    list[start] = '#';
                }
                if (start < s.length() -1 && list[start+1] == '#') {
                    start = start+2;
                } else {
                    start += d;
                }
            }
        }

        return str;
    }
}
