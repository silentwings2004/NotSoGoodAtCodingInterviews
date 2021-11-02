/*
 * @lc app=leetcode id=131 lang=java
 *
 * [131] Palindrome Partitioning
 */

// @lc code=start
class Solution {
    // time = O(n * 2^n), space = O(n^2)
    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        // corner case
        if (s == null || s.length() == 0) return res;

        int n = s.length();
        boolean[][] isPalin = new boolean[n][n];
        helper(s, isPalin);

        dfs(s, 0, new ArrayList<>(), res, isPalin);
        return res;
    }

    private void dfs(String s, int idx, List<String> path, List<List<String>> res, boolean[][] isPalin) {
        int n = s.length();
        // base case
        if (idx == n) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = idx; i < n; i++) {
            if (isPalin[idx][i]) {
                path.add(s.substring(idx, i + 1));
                dfs(s, i + 1, path, res, isPalin);
                path.remove(path.size() - 1);
            }
        }
    }

    private void helper(String s, boolean[][] isPalin) {
        int n = s.length();
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                if (s.charAt(i) == s.charAt(j) && (j - i < 2 || isPalin[i + 1][j - 1])) {
                    isPalin[i][j] = true;
                }
            }
        }
    }
}
// @lc code=end

