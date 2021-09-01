/*
 * @lc app=leetcode id=3 lang=java
 *
 * [3] Longest Substring Without Repeating Characters
 */

// @lc code=start
class Solution {
    // time = O(n), space = O(1)
    public int lengthOfLongestSubstring(String s) {
        // corner case
        if (s == null || s.length() == 0) return 0;

        int[] freq = new int[128];
        Arrays.fill(freq, -1);

        int i = 0, n = s.length(), res = 0;
        for (int j = 0; j < n; j++) {
            char c = s.charAt(j);
            if (freq[c] != -1) {
                if (i <= freq[c]) i = freq[c] + 1;
            }
            freq[c] = j;
            res = Math.max(res, j - i + 1);
        }
        return res;
    }
}
// @lc code=end

