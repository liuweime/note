import java.util.HashMap;

public class RomanToInteger {

    public int run(String s) {
        HashMap<Character, Integer> romanMap = getRomanMap();

        if (s.length() == 1 ) {

            return romanMap.getOrDefault(s.charAt(0), 0);
        }

        int num = 0;
        int prev = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (romanMap.getOrDefault(s.charAt(i), 0) < prev) {
                num = num - romanMap.getOrDefault(s.charAt(i), 0);
            } else {
                num = num + romanMap.getOrDefault(s.charAt(i), 0);
            }
            prev = romanMap.getOrDefault(s.charAt(i), 0);
        }

        return num;
    }

    private HashMap<Character, Integer> getRomanMap() {
        HashMap<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
        return map;
    }

}
