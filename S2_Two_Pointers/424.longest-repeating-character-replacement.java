/*
 * @lc app=leetcode id=424 lang=java
 *
 * [424] Longest Repeating Character Replacement
 */

// @lc code=start
class Solution {
    // time = O(n), space = O(1)
    public int characterReplacement(String s, int k) {
        // corner case
        if (s == null || s.length() == 0 || k < 0) return 0;
        
        int n = s.length(), j = 0, res = 0;
        int[] count = new int[26];
        for (int i = 0; i < n; i++) {
            while (j < n && helper(s, k, j, count, j - i + 1)) j++;
            res = Math.max(res, j - i);
            count[s.charAt(i) - 'A']--;
        }
        return res;
    }

    private boolean helper(String s, int k, int j, int[] count, int len) {
        count[s.charAt(j) - 'A']++;
        int maxCount = 0;
        for (int i = 0; i < 26; i++) maxCount = Math.max(maxCount, count[i]);
        if (len - maxCount <= k) return true;
        count[s.charAt(j) - 'A']--;
        return false;
    }
}
// @lc code=end

