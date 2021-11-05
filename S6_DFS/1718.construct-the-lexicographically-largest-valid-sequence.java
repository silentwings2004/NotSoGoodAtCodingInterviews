/*
 * @lc app=leetcode id=1718 lang=java
 *
 * [1718] Construct the Lexicographically Largest Valid Sequence
 */

// @lc code=start
class Solution {
    // time = O(n), space = O(n)
    public int[] constructDistancedSequence(int n) {
        boolean[] used = new boolean[n + 1]; // 1 ~ n
        int[] res = new int[2 * n - 1];
        dfs(0, used, res, n);
        return res;
    }

    private boolean dfs(int idx, boolean[] used, int[] res, int n) {
        // base case
        if (idx == 2 * n - 1) return true;
        if (res[idx] > 0) return dfs(idx + 1, used, res, n);

        for (int d = n; d >= 1; d--) {
            if (used[d]) continue;
            if (d > 1 && (idx + d >= 2 * n - 1 || res[idx + d] > 0)) continue;
            used[d] = true;
            res[idx] = d;
            if (d > 1) res[idx + d] = d;
            if (dfs(idx + 1, used, res, n)) return true;
            // setback
            used[d] = false;
            res[idx] = 0;
            if (d > 1) res[idx + d] = 0;
        }
        return false;
    }
}
// @lc code=end

