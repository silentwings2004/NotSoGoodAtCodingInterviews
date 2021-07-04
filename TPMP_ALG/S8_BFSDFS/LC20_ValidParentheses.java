package S8_BFSDFS;
import java.util.*;

/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: ValidParentheses
 * Creater: Silentwings
 * Date: Feb, 2020
 * Description: 20. Valid Parentheses
 */
public class LC20_ValidParentheses {
    /**
     * Given a string containing just the characters '(', ')', '{', '}', '[' and ']',
     * determine if the input string is valid.
     *
     * An input string is valid if:
     *
     * Open brackets must be closed by the same type of brackets.
     * Open brackets must be closed in the correct order.
     * Note that an empty string is also considered valid.
     *
     * Example 1:
     *
     * Input: "()"
     * Output: true
     * Example 2:
     *
     * Input: "()[]{}"
     * Output: true
     * Example 3:
     *
     * Input: "(]"
     * Output: false
     * Example 4:
     *
     * Input: "([)]"
     * Output: false
     * Example 5:
     *
     * Input: "{[]}"
     * Output: true
     * @param s
     * @return
     */
    // time = O(n), space = O(n)
    public boolean isValid(String s) {
        // corner case
        if (s == null || s.length() == 0) return true;

        int len = s.length();
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '[' || c == '{') stack.push(c);
            else {
                if (stack.isEmpty()) return false;
                char top = stack.peek();
                if (c == ')' && top == '(' || c == ']' && top == '[' || c == '}' && top == '{') {
                    stack.pop();
                } else return false;
            }
        }
        return stack.isEmpty();
    }
}
