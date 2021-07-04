package S10_ArrayString;
import java.util.*;

/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: Minimum_Window_Substring
 * Creater: Silentwings
 * Date: Mar, 2020
 * Description: 76. Minimum Window Substring
 */
public class LC76_Minimum_Window_Substring {
    /**
     * Given a string S and a string T, find the minimum window in S which will contain all the characters in T in
     * complexity O(n).
     *
     * Example:
     *
     * Input: S = "ADOBECODEBANC", T = "ABC"
     * Output: "BANC"
     * Note:
     *
     * If there is no such window in S that covers all characters in T, return the empty string "".
     * If there is such window, you are guaranteed that there will always be only one unique minimum window in S.
     * @param s
     * @param t
     * @return
     */
    public String minWindow(String s, String t) {
        // corner case
        if (s == null || s.length() == 0 || t == null || t.length() == 0 || s.length() < t.length()) {
            return "";
        }

        // 记录最短子串的开始位置和长度
        int start = 0;
        int minLen = Integer.MAX_VALUE;
        int left = 0, right = 0;

        Map<Character, Integer> window = new HashMap<>();
        Map<Character, Integer> target = new HashMap<>();

        for (char c : t.toCharArray()) {
            target.put(c, target.getOrDefault(c, 0) + 1);
        }

        int match = 0;

        while (right < s.length()) {
            char c1 = s.charAt(right);
            if (target.containsKey(c1)) {
                window.put(c1, window.getOrDefault(c1, 0) + 1);
                if (window.get(c1).compareTo(target.get(c1)) == 0) match++; // 注意：常量池！！！
            }
            right++;

            while (match == target.size()) {
                if (right - left < minLen) {
                    // 更新最小子串的位置和长度
                    start = left;
                    minLen = right - left;
                }
                char c2 = s.charAt(left);
                if (target.containsKey(c2)) {
                    window.put(c2, window.get(c2) - 1);
                    if (window.get(c2) < target.get(c2)) match--;
                }
                left++;
            }
        }
        return minLen == Integer.MAX_VALUE ? "" : s.substring(start, start + minLen);
    }
}
