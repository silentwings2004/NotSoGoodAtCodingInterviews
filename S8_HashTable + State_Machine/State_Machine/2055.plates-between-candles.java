/*
 * @lc app=leetcode id=2055 lang=java
 *
 * [2055] Plates Between Candles
 */

// @lc code=start
class Solution {
    // time = O(n), space = O(n)
    public int[] platesBetweenCandles(String s, int[][] queries) {
        int n = s.length();
        int[] presum = new int[n];
        int temp = 0;
        for (int i = 0; i < n; i++) {
            temp += s.charAt(i) == '*' ? 1 : 0;
            presum[i] = temp;
        }

        int[] last = new int[n];
        temp = -1;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '|') temp = i;
            last[i] = temp;
        }

        int[] next = new int[n];
        temp = n;
        for (int i = n - 1; i >= 0; i--) {
            if (s.charAt(i) == '|') temp = i;
            next[i] = temp;
        }

        int[] res = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int a = queries[i][0], b = queries[i][1];
            int x = next[a], y = last[b];
            if (x >= a && y <= b && x <= y) res[i] = presum[y] - presum[x];
        }
        return res;
    }
}
// @lc code=end

