import java.util.Arrays;

/*
 * @lc app=leetcode id=1199 lang=java
 *
 * [1199] Minimum Time to Build Blocks
 */

// @lc code=start
class Solution {
    // time = O(nlogn), space = O(n^2)
    public int minBuildTime(int[] blocks, int split) {
        int n = blocks.length;
        int[][] dp = new int[n][n + 1]; // dp[i][j]: 
        for (int i = 0; i < n; i++) Arrays.fill(dp[i], -1);

        Arrays.sort(blocks);
        int i = 0, j = n - 1;
        while (i < j) {
            int temp = blocks[i];
            blocks[i++] = blocks[j];
            blocks[j--] = temp;
        }
        
        return dfs(blocks, split, dp, 0, 1);
    }

    private int dfs(int[] blocks, int split, int[][] dp, int i, int j) {
        int n = blocks.length;
        // base case
        if (i == n) return 0;
        if (j == 0) return Integer.MAX_VALUE;
        if (j > n - i) return blocks[i];
        if (dp[i][j] != -1) return dp[i][j];

        dp[i][j] = Math.min(split + dfs(blocks, split, dp, i, j * 2), Math.max(blocks[i], dfs(blocks, split, dp, i + 1, j - 1)));
        return dp[i][j];
    }
}
// @lc code=end

