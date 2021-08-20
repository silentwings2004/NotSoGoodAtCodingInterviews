/*
 * @lc app=leetcode id=5 lang=java
 *
 * [5] Longest Palindromic Substring
 */

// @lc code=start
class Solution {
    // S1: Manacher
    // time = O(n), space = O(n)
    public String longestPalindrome(String s) {
        // corner case
        if (s == null || s.length() == 0) return "";

        StringBuilder sb = new StringBuilder();
        sb.append('#');
        for (char c : s.toCharArray()) {
            sb.append(c).append('#');
        }

        int n = sb.length();
        int[] p = new int[n];
        int maxCenter = -1, maxRight = -1;

        for (int i = 0; i < n; i++) {
            int r = 0;
            if (i <= maxRight) {
                int j = 2 * maxCenter - i;
                p[i] = Math.min(r, maxRight - i);
                while (i - r >= 0 && i + r < n && sb.charAt(i - r) == sb.charAt(i + r)) r++;
            } else {
                r = 0;
                while (i - r >= 0 && i + r < n && sb.charAt(i - r) == sb.charAt(i + r)) r++;
            }
            p[i] = r - 1;
            if (p[i] + 1 > maxRight) {
                maxRight = p[i] + 1;
                maxCenter = i;
            }
        }
        
        int maxLen = 0, center = 0;
        for (int i = 0; i < n; i++) {
            if (p[i] > maxLen) {
                maxLen = p[i];
                center = i;
            }
        }
        int start = (center - maxLen) / 2;
        return s.substring(start, start + maxLen);
    }
}
// @lc code=end

