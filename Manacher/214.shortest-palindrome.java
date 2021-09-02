/*
 * @lc app=leetcode id=214 lang=java
 *
 * [214] Shortest Palindrome
 */

// @lc code=start
class Solution {
    public String shortestPalindrome(String s) {
        // corner case
        if (s == null || s.length() == 0) return "";

        // Manacher Template
        StringBuilder sb = new StringBuilder();
        sb.append('#');
        for (char c : s.toCharArray()) {
            sb.append(c).append('#');
        }

        int n = sb.length();
        int[] p = new int[n];
        int maxCenter = -1, maxRight = -1;
        int L = 0;

        for (int i = 0; i < n; i++) {
            int r = 0;
            if (i <= maxRight) {
                int j = maxCenter * 2 - i;
                r = Math.min(p[j], maxRight - i);
            }
            while (i - r >= 0 && i + r < n && sb.charAt(i - r) == sb.charAt(i + r)) r++;
            p[i] = r - 1;
            if (p[i] + i > maxRight) {
                maxRight = p[i] + i;
                maxCenter = i;
            }

            if (i - p[i] == 0) {
                L = (p[i] * 2 + 1) / 2;
            }
        }
        String temp = s.substring(L);
        return reverse(temp) + s;
    }

    private String reverse(String s) {
        StringBuilder sb = new StringBuilder(s);
        return sb.reverse().toString();
    }
}
// @lc code=end

