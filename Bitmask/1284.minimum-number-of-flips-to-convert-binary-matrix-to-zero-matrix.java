/*
 * @lc app=leetcode id=1284 lang=java
 *
 * [1284] Minimum Number of Flips to Convert Binary Matrix to Zero Matrix
 */

// @lc code=start
class Solution {
    // S1: bit mask
    // time = O(m * n * 2^(m * n)), space = O(m * n)
    int m, n;
    private int[][] directions = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}, {0, 0}};
    public int minFlips(int[][] mat) {
        this.m = mat.length;
        this.n = mat[0].length;
        int res = Integer.MAX_VALUE;

        for (int state = 0; state < (1 << (m * n)); state++) {
            if (check(mat, state)) {
                res = Math.min(res, Integer.bitCount(state));
            }
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    private boolean check(int[][] mat, int state) {
        int[][] temp = new int[m][n];
        for (int i = 0; i < m; i++) temp[i] = mat[i].clone();

        for (int i = 0; i < (m * n); i++) {
            if (((state >> i) & 1) == 1) {
                int x = i / n, y = i % n;
                for (int[] dir : directions) {
                    int xx = x + dir[0];
                    int yy = y + dir[1];
                    if (xx < 0 || xx >= m || yy < 0 || yy >= n) continue;
                    temp[xx][yy] = 1 - temp[xx][yy];
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (temp[i][j] != 0) return false;
            }
        }
        return true;
    }

    // S2: Gosper's hack
    public int minFlips2(int[][] mat) {
        this.m = mat.length;
        this.n = mat[0].length;
        
        if (check(mat, 0)) return 0;
        for (int k = 1; k <= (m * n); k++) {
            int state = (1 << k) - 1;
            while (state < (1 << (m * n))) {
                // do something
                if (check(mat, state)) return k;
                int c = state & -state;
                int r = state + c;
                state = (((r ^ state) >> 2) / c) | r;
            }
        }
        return -1;
    }
}
// @lc code=end

