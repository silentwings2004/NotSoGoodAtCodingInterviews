/*
 * @lc app=leetcode id=741 lang=java
 *
 * [741] Cherry Pickup
 */

// @lc code=start
class Solution {
    // time = O(n^3), space = O(n^3)
    public int cherryPickup(int[][] grid) {
        int n = grid.length;
        int[][][] dp = new int[n + 1][n + 1][n + 1];

        // init
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                for (int x = 0; x <= n; x++) {
                    dp[i][j][x] = Integer.MIN_VALUE;
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                for (int x = 1; x <= n; x++) {
                    int y = i + j - x;
                    if (y < 1 || y > n) continue;
                    if (grid[i - 1][j - 1] == -1 || grid[x - 1][y - 1] == -1) continue;
                    if (i == 1 && j == 1 && x == 1) {
                        dp[i][j][x] = grid[0][0];
                        continue;
                    }
                    dp[i][j][x] = Math.max(dp[i][j][x], dp[i - 1][j][x - 1]);
                    dp[i][j][x] = Math.max(dp[i][j][x], dp[i - 1][j][x]);
                    dp[i][j][x] = Math.max(dp[i][j][x], dp[i][j - 1][x - 1]);
                    dp[i][j][x] = Math.max(dp[i][j][x], dp[i][j - 1][x]);

                    if (i == x && j == y) {
                        dp[i][j][x] += grid[i - 1][j - 1];
                    } else {
                        dp[i][j][x] += grid[i - 1][j - 1] + grid[x - 1][y - 1];
                    }
                }
            }
        }
        return Math.max(0, dp[n][n][n]);
    }
}
// @lc code=end

