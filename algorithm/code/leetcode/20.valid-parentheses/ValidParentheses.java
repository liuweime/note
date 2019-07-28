import java.util.HashMap;
import java.util.Stack;

public class ValidParentheses {

    /**
     * 利用栈解决问题 时间和空间复杂度都是 O(n)
     */
    public boolean run(String s) {
        if (s.length() == 0) return true;
        if (s.length() % 2 != 0) return false;

        HashMap<Character, Character> map =  new HashMap<>();
        map.put('{', '}');
        map.put('[', ']');
        map.put('(', ')');

        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                stack.push(map.get(s.charAt(i)));
                continue;
            } else if (stack.isEmpty()) {
                return false;
            }
            if (s.charAt(i) != stack.pop()) {
                return false;
            }
        }

        return stack.isEmpty();
    }
}
