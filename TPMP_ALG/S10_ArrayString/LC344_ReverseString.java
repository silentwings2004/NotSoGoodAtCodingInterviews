package S10_ArrayString;
import java.util.*;

/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: ReverseString
 * Creater: Silentwings
 * Date: Mar, 2020
 * Description: 344. Reverse String
 */
public class LC344_ReverseString {
    /**
     * Write a function that reverses a string. The input string is given as an array of characters char[].
     *
     * Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1)
     * extra memory.
     *
     * You may assume all the characters consist of printable ascii characters.
     *
     *
     *
     * Example 1:
     *
     * Input: ["h","e","l","l","o"]
     * Output: ["o","l","l","e","h"]
     * Example 2:
     *
     * Input: ["H","a","n","n","a","h"]
     * Output: ["h","a","n","n","a","H"]
     * @param s
     */
    // S1: While loop
    public void reverseString(char[] s) {
        // corner case
        if (s == null || s.length < 2) return;

        int left = 0, right = s.length - 1;
        while (left < right) {
            swap(s, left++, right--);
        }
    }

    private void swap(char[] s, int left, int right) {
        char count = s[left];
        s[left] = s[right];
        s[right] = count;
    }

    // S2: 先办事后recursion
    public void reverseString2(char[] s) {
        // corner case
        if (s == null || s.length <= 1) return;

        helper(s, 0, s.length - 1);
    }

    private void helper(char[] s, int start, int end) {
        if (start >= end) return;
        swap(s, start, end);
        helper(s, ++start, --end);
    }

    // S3: 先recursion后办事
    public void reverseString3(char[] s) {
        // corner case
        if (s == null || s.length <= 1) return;

        helper2(s, 0, s.length - 1);
    }

    private void helper2(char[] s, int start, int end) {
        if (start >= end) return;
        helper(s, start + 1, end - 1);
        swap(s, start, end);
    }
}
