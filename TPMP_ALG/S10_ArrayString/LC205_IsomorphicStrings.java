package S10_ArrayString;
import java.util.*;

/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: IsomorphicStrings
 * Creater: Silentwings
 * Date: Mar, 2020
 * Description: 205. Isomorphic Strings
 */
public class LC205_IsomorphicStrings {
    /**
     * Given two strings s and t, determine if they are isomorphic.
     *
     * Two strings are isomorphic if the characters in s can be replaced to get t.
     *
     * All occurrences of a character must be replaced with another character while preserving the order of characters.
     * No two characters may map to the same character but a character may map to itself.
     *
     * Example 1:
     *
     * Input: s = "egg", t = "add"
     * Output: true
     * Example 2:
     *
     * Input: s = "foo", t = "bar"
     * Output: false
     * Example 3:
     *
     * Input: s = "paper", t = "title"
     * Output: true
     * Note:
     * You may assume both s and t have the same length.
     * @param s
     * @param t
     * @return
     */
    // S1: HashMap
    // time = O(n), space = O(n)
    public boolean isIsomorphic(String s, String t) {
        // corner case
        if (s == null) return t == null;
        if (s.length() != t.length()) return false;

        HashMap<Character, Character> map = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char sc = s.charAt(i);
            char tc = t.charAt(i);
            if (map.containsKey(sc)) {
                if (map.get(sc) == tc) continue;
                else return false;
            } else {
                if (!map.containsValue(tc)) map.put(sc, tc);
                else return false;
            }
        }
        return true;
    }
}
