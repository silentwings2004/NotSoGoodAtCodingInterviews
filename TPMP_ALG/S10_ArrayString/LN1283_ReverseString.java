package S10_ArrayString;
import java.util.*;

/**
 * Project Name: Lintcode
 * Package Name: lintcode
 * File Name: ReverseString
 * Creater: Silentwings
 * Date: Mar, 2020
 * Description: 1283. Reverse String
 */
public class LN1283_ReverseString {
    /**
     * Write a function that takes a string as input and returns the string reversed.
     *
     * Example
     * Example 1：
     *
     * Input："hello"
     * Output："olleh"
     * Example 2：
     *
     * Input："hello world"
     * Output："dlrow olleh"
     */
    // S1: while loop
    public String reverseString(String s) {
        if (s == null || s.length() < 2) return s;
        char[] chars = s.toCharArray();
        int i = 0, j = chars.length - 1;
        while (i < j) {
            swap(chars, i++, j--);
        }
        return new String(chars);
    }

    private void swap(char[] chars, int i, int j) {
        char count = chars[i];
        chars[i] = chars[j];
        chars[j] = count;
    }

    // S2: 先办事后recursion
    public String reverseString2(String s) {
        if (s == null || s.length() <= 1) return s;
        char[] chars = s.toCharArray();
        helper(chars, 0, chars.length - 1);
        return new String(chars, 0, chars.length);
    }

    private void helper(char[] chars, int start, int end) {
        if (start >= end) return;
        swap(chars, start, end);
        helper(chars, ++start, --end);
    }

    // S3: 先recursion后办事
    public String reverseString3(String s) {
        if (s == null || s.length() <= 1) return s;
        char[] chars = s.toCharArray();
        helper2(chars, 0, chars.length - 1);
        return new String(chars, 0, chars.length);
    }

    private void helper2(char[] chars, int start, int end) {
        if (start >= end) return;
        helper2(chars, start + 1, end - 1);
        swap(chars, start, end);
    }
}
