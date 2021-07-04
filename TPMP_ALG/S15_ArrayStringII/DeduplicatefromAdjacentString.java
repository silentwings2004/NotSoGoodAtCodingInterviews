package S15_ArrayStringII;

import java.util.Stack;

public class DeduplicatefromAdjacentString {
    /**
     * Given sorted?/adjacent array (string), deduplicate with one remaining
     * @param input
     * @return
     */
    // S1: Stack： 先存再倒
    public String deduplicate(String input) {
        // corner case
        if (input == null || input.length() <= 1) return input;

        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < input.length(); i++) {
            if(stack.isEmpty() || stack.peek() != input.charAt(i)) {
                stack.push(input.charAt(i));
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) sb.append(stack.pop());
        return sb.reverse().toString();
    }

    // S2: Stack: 边做边存
    public String deduplicate2(String input) {
        // corner case
        if (input == null || input.length() <= 1) return input;

        Stack<Character> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();

        for (char c : input.toCharArray()) {
            if (stack.empty() || c != stack.peek()) {
                stack.push(c);
                sb.append(c);
            }
        }
        return sb.toString();
    }

    // S3: StringBuilder only!
    public String deduplicate3(String input) {
        // corner case
        if (input == null || input.length() <= 1) return input;

        StringBuilder sb = new StringBuilder();

        for (char c : input.toCharArray()) {
            if (sb.length() == 0 || sb.charAt(sb.length() - 1) != c) sb.append(c);
        }
        return sb.toString();
    }
}
