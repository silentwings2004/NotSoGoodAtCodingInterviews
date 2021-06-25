/*
 * @lc app=leetcode id=174 lang=java
 *
 * [174] Dungeon Game
 */

// @lc code=start
class Solution {
    // time = O(m * n), space = O(m * n)
    public int calculateMinimumHP(int[][] dungeon) {
        int m = dungeon.length, n = dungeon[0].length;
        int[][] dp = new int[m][n];

        for(int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (i == m - 1 && j == n - 1) {
                    dp[i][j] = 1;
                } else if (i == m - 1) {
                    dp[i][j] = dp[i][j + 1] - dungeon[i][j + 1]; // 只能从右边倒推过来
                } else if (j == n - 1) {
                    dp[i][j] = dp[i + 1][j] - dungeon[i + 1][j]; // 只能从左边倒推过来
                } else {
                    dp[i][j] = Math.min(dp[i + 1][j] - dungeon[i + 1][j], dp[i][j + 1] - dungeon[i][j + 1]);
                }
                dp[i][j] = Math.max(dp[i][j], 1); 
            }
        }
        dp[0][0] = Math.max(1, dp[0][0] - dungeon[0][0]); // 最后起点位置还要补上处理，前面只能保证活着走到0，但不能从起点出发走出来
        return dp[0][0];
    }
}
// @lc code=end

