/*
 * @lc app=leetcode id=1284 lang=java
 *
 * [1284] Minimum Number of Flips to Convert Binary Matrix to Zero Matrix
 */

// @lc code=start
class Solution {
    // time = O(m * n * 2^(m * n)), space = O(m * n)
    private static final int[][] DIRECTIONS = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}, {0, 0}};
    public int minFlips(int[][] mat) {
        // corner case
        if (mat == null || mat.length == 0 || mat[0] == null || mat[0].length == 0) return 0;

        int m = mat.length, n = mat[0].length;
        int res = Integer.MAX_VALUE;

        for (int state = 0; state < (1 << m * n); state++) {
            if (check(mat, state)) res = Math.min(res, Integer.bitCount(state));
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    private boolean check(int[][] mat, int state) {
        int m = mat.length, n = mat[0].length;
        int[][] copy = new int[m][n];
        for (int i = 0; i < m; i++) copy[i] = mat[i].clone();

        for (int b = 0; b < m * n; b++) {
            int t = state % 2;
            state /= 2;
            if (t == 0) continue; // no need to flip
            int x = b / n, y = b % n;
            for (int[] dir : DIRECTIONS) {
                int xx = x + dir[0];
                int yy = y + dir[1];
                if (xx < 0 || xx >= m || yy < 0 || yy >= n) continue;
                copy[xx][yy] = 1 - copy[xx][yy]; // flip copy[xx][yy]
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (copy[i][j] != 0) return false;
            }
        }
        return true;
    }
}
// @lc code=end

