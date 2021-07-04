package S9_BitNumberMath;
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
     * Do not allocate extra space for another array, you must do this by modifying the input array in-place
     * with O(1) extra memory.
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
    // time = O(n), space = O(n)
    public void reverseString(char[] s) {
        // corner case
        if (s == null || s.length == 0) return;
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

    // LN1283. Reverse String --> what if input is given as a String instead of a char[]
    public String reverseString2(String s) {
        // write your code here
        if (s == null || s.length() == 0) return null;
        char[] str = s.toCharArray();   // convert String to char[]
        int left = 0, right = str.length - 1;
        while (left < right) {
            swap(str, left++, right--);
        }
        return new String(str);  // return by creating a new String
    }
}