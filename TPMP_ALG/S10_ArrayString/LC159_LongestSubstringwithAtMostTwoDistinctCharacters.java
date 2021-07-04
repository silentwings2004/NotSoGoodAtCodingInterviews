package S10_ArrayString;
import java.util.*;

/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: LongestSubstringwithAtMostTwoDistinctCharacters
 * Creater: Silentwings
 * Date: Mar, 2020
 * Description: 159. Longest Substring with At Most Two Distinct Characters
 */
public class LC159_LongestSubstringwithAtMostTwoDistinctCharacters {
    /**
     * Given a string s , find the length of the longest substring t  that contains at most 2 distinct characters.
     *
     * Example 1:
     *
     * Input: "eceba"
     * Output: 3
     * Explanation: t is "ece" which its length is 3.
     * Example 2:
     *
     * Input: "ccaabbb"
     * Output: 5
     * Explanation: t is "aabbb" which its length is 5.
     * @param s
     * @return
     */
    // time = O(n), space = O(1)
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        // corner case
        if (s == null || s.length() == 0) return 0;

        int len = s.length();
        int left = 0, right = 0;
        int max = 1;
        HashMap<Character, Integer> map = new HashMap<>();

        while (right < len) {
            if (map.size() < 3) {
                map.put(s.charAt(right), right++);
            }
            if (map.size() == 3) {
                int index = Collections.min(map.values());
                map.remove(s.charAt(index));
                left = index + 1;
            }
            max = Math.max(max, right - left);
        }
        return max;
    }
}
