package S10_ArrayString;
import java.util.*;

/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: ValidAnagram
 * Creater: Silentwings
 * Date: Mar, 2020
 * Description: 242. Valid Anagram
 */
public class LC242_ValidAnagram {
    /**
     * Given two strings s and t , write a function to determine if t is an anagram of s.
     *
     * Example 1:
     *
     * Input: s = "anagram", t = "nagaram"
     * Output: true
     * Example 2:
     *
     * Input: s = "rat", t = "car"
     * Output: false
     * Note:
     * You may assume the string contains only lowercase alphabets.
     *
     * Follow up:
     * What if the inputs contain unicode characters? How would you adapt your solution to such case?
     * @param s
     * @param t
     * @return
     */
    // S1: Sort
    // time = O(nlogn), space = O(n)
    public boolean isAnagram(String s, String t) {
        // corner case
        if (s == null) return t == null;
        if (s.length() != t.length()) return false;

        char[] str1 = s.toCharArray();
        char[] str2 = t.toCharArray();

        Arrays.sort(str1);
        Arrays.sort(str2);

        return Arrays.equals(str1, str2);
    }

    // S2: HashMap
    // time = O(n), space = O(1)
    public boolean isAnagram2(String s, String t) {
        // corner case
        if (s == null) return t == null;
        if (s.length() != t.length()) return false;

        int[] count = new int[26];
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
            count[t.charAt(i) - 'a']--;
        }
        for (int n : count) {
            if (n != 0) return false;
        }
        return true;
    }
}
