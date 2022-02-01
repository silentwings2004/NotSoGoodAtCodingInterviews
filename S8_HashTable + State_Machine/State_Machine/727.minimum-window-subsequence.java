/*
 * @lc app=leetcode id=727 lang=java
 *
 * [727] Minimum Window Subsequence
 */

// @lc code=start
class Solution {
    // S1: state machine
    // time = O(s * n), space = O(26m)
    public String minWindow(String s1, String s2) {
        int m = s1.length();
        s1 = "#" + s1;
        int[][] next = new int[m + 1][26];
        Arrays.fill(next[m], -1);

        for (int i = m; i >= 1; i--) {
            for (int j = 0; j < 26; j++) {
                next[i - 1][j] = next[i][j];
            }
            next[i - 1][s1.charAt(i) - 'a'] = i;
        }

        List<Integer> start = new ArrayList<>();
        for (int i = 1; i <= m; i++) {
            if (s1.charAt(i) == s2.charAt(0)) {
                start.add(i);
            }
        }

        String res = "";
        for (int i : start) {
            int j = i - 1;
            boolean flag = true;
            for (char ch : s2.toCharArray()) {
                j = next[j][ch - 'a'];
                if (j == -1) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                int len = j - i + 1;
                if (res == "" || len < res.length()) {
                    res = s1.substring(i, j + 1);
                }
            }
        }
        return res;
    }

    // S2: DP
    // time = O(m * n), space = O(m * n)
    public String minWindow2(String s1, String s2) {
        int m = s1.length(), n = s2.length();
        s1 = "#" + s1;
        s2 = "#" + s2;

        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) Arrays.fill(dp[i], Integer.MAX_VALUE / 2); // 涉及到+1, -1 => max/2

        for (int j = 1; j <= n; j++) dp[0][j] = Integer.MAX_VALUE / 2;
        for (int i = 0; i <= m; i++) dp[i][0] = 0;
        // dp[x][0] = 0
        // dp[0][0] = 0

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i) == s2.charAt(j)) dp[i][j] = dp[i - 1][j - 1] + 1;
                else dp[i][j] = dp[i - 1][j] + 1;
            }
        }
        int len = Integer.MAX_VALUE / 2, pos = -1;
        for (int i = 1; i <= m; i++) {
            if (dp[i][n] < len) {
                len = dp[i][n];
                pos = i;
            }
        }
        return len == Integer.MAX_VALUE / 2 ? "" : s1.substring(pos - len + 1, pos + 1);
    }
}
// @lc code=end

