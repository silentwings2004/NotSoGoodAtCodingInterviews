package S15_ArrayStringII;
import java.util.*;

/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: RemoveDuplicateLetters
 * Creater: Silentwings
 * Date: Mar, 2020
 * Description: 316. Remove Duplicate Letters
 */
public class LC316_RemoveDuplicateLetters {
    /**
     * Given a string which contains only lowercase letters, remove duplicate letters so that every letter appears once
     * and only once. You must make sure your result is the smallest in lexicographical order among all possible results.
     *
     * Example 1:
     *
     * Input: "bcabc"
     * Output: "abc"
     * Example 2:
     *
     * Input: "cbacdcbc"
     * Output: "acdb"
     * Note: This question is the same as 1081:
     * https://leetcode.com/problems/smallest-subsequence-of-distinct-characters/
     *
     * 难点：如何保证字典序最小？？？
     *
     * @param s
     * @return
     */
    public String removeDuplicateLetters(String s) {
        // corner case
        if (s == null || s.length() < 2) return s;

        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (stack.empty() || stack.peek() != c) stack.push(c);
        }
    }

}
