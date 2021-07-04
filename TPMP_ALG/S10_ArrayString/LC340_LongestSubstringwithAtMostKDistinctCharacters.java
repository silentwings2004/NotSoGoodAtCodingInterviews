package S10_ArrayString;
import java.util.*;

/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: LongestSubstringwithAtMostKDistinctCharacters
 * Creater: Silentwings
 * Date: Mar, 2020
 * Description: 340. Longest Substring with At Most K Distinct Characters
 */
public class LC340_LongestSubstringwithAtMostKDistinctCharacters {
    /**
     * Given a string, find the length of the longest substring T that contains at most k distinct characters.
     *
     * Example 1:
     *
     * Input: s = "eceba", k = 2
     * Output: 3
     * Explanation: T is "ece" which its length is 3.
     * Example 2:
     *
     * Input: s = "aa", k = 1
     * Output: 2
     * Explanation: T is "aa" which its length is 2.
     * @param s
     * @param k
     * @return
     */
    // time = O(nk), space = O(k) --> additional space is used only for a hashmap with at most k + 1 elements.
    // For the worst case when the input string contains n distinct characters, at each step one uses O(k) time to find
    // a minimum value in the hashmap with k elements and so the overall time complexity is O(nk).
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        // corner case
        if (s == null || s.length() == 0 || k == 0) return 0;

        int left = 0, right = 0;
        int max = 1;
        HashMap<Character, Integer> map = new HashMap<>();

        while (right < s.length()) {
            if (map.size() <= k) {
                map.put(s.charAt(right), right++);
            }
            if (map.size() == k + 1) {
                int index = Collections.min(map.values());
                map.remove(s.charAt(index));
                left = index + 1;
            }
            max = Math.max(max, right - left);
        }
        return max;
    }
}
