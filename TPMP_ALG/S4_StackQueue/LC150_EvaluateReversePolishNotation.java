package S4_StackQueue;
import java.util.*;
/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: EvaluateReversePolishNotation
 * Creater: Silentwings
 * Date: Aug, 2020
 * Description: 150. Evaluate Reverse Polish Notation
 */
public class LC150_EvaluateReversePolishNotation {
    /**
     * Evaluate the value of an arithmetic expression in Reverse Polish Notation.
     *
     * Valid operators are +, -, *, /. Each operand may be an integer or another expression.
     *
     * Note:
     *
     * Division between two integers should truncate toward zero.
     * The given RPN expression is always valid. That means the expression would always evaluate to a result and
     * there won't be any divide by zero operation.
     * Example 1:
     *
     * Input: ["2", "1", "+", "3", "*"]
     * Output: 9
     * Explanation: ((2 + 1) * 3) = 9
     *
     * Example 2:
     *
     * Input: ["4", "13", "5", "/", "+"]
     * Output: 6
     * Explanation: (4 + (13 / 5)) = 6
     *
     * Example 3:
     *
     * Input: ["10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"]
     * Output: 22
     * Explanation:
     *   ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
     * = ((10 * (6 / (12 * -11))) + 17) + 5
     * = ((10 * (6 / -132)) + 17) + 5
     * = ((10 * 0) + 17) + 5
     * = (0 + 17) + 5
     * = 17 + 5
     * = 22
     * @param tokens
     * @return
     */
    // time = O(n), space = O(n)
    public int evalRPN(String[] tokens) {
        // corner case
        if (tokens == null || tokens.length == 0) {
            throw new IllegalArgumentException();
        }

        Stack<Long> stack = new Stack<>();
        int len = tokens.length;

        for (String s : tokens) {
            if (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/")) {
                if (stack.size() < 2) throw new RuntimeException();
                else {
                    long num1 = stack.pop();
                    long num2 = stack.pop();
                    long res = cal(s, num1, num2);
                    stack.push(res);
                }
            } else {
                stack.push(Long.valueOf(s));
            }
        }
        long ans = stack.pop();
        return (int)ans;
    }

    private long cal(String s, long num1, long num2) {
        switch (s) {
            case "+": return num2 + num1;
            case "-": return num2 - num1;
            case "*": return num2 * num1;
            case "/": return num2 / num1;
            default: throw new IllegalArgumentException();
        }
    }
}
