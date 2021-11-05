/*
 * @lc app=leetcode id=1340 lang=java
 *
 * [1340] Jump Game V
 */

// @lc code=start
class Solution {
    // time = O(n^2), space = O(n)
    public int maxJumps(int[] arr, int d) {
        int n = arr.length, res = 1;
        int[] memo = new int[n];
        for (int i = 0; i < n; i++) {
            res = Math.max(res, dfs(arr, i, d, memo));
        }
        return res;
    }

    private int dfs(int[] arr, int idx, int d, int[] memo) {
        if (memo[idx] != 0) return memo[idx];

        // jump to the right
        int n = arr.length, res = 1;
        for (int k = 1; k <= d; k++) {
            if (idx + k >= n) break;
            if (arr[idx + k] >= arr[idx]) break;
            res = Math.max(res, dfs(arr, idx + k, d, memo) + 1);
        }
        for (int k = 1; k <= d; k++) {
            if (idx - k < 0) break;
            if (arr[idx - k] >= arr[idx]) break;
            res = Math.max(res, dfs(arr, idx - k, d, memo) + 1);
        }
        memo[idx] = res;
        return res;
    }
}
// @lc code=end

