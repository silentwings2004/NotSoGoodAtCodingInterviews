/*
 * @lc app=leetcode id=647 lang=java
 *
 * [647] Palindromic Substrings
 */

// @lc code=start
class Solution {
    // S1: Manacher
    // time = O(n), space = O(n)
    public int countSubstrings(String s) {
        // corner case
        if (s == null || s.length() == 0) return 0;

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
        int res = 0;
        for (int i = 0; i < n; i++) {
            res += (p[i] + 1) / 2;
        }
        return res;
    }
}
// @lc code=end

