/*
 * @lc app=leetcode id=312 lang=java
 *
 * [312] Burst Balloons
 */

// @lc code=start
class Solution {
    // time = O(n^3), space = O(n^2)
    public int maxCoins(int[] nums) {
        // corner case
        if (nums == null || nums.length == 0) return 0;

        int n = nums.length;
        int[] nums2 = new int[n + 2];
        nums2[0] = nums2[n + 1] = 1;
        for (int i = 0; i < n; i++) {
            nums2[i + 1] = nums[i];
        }
        nums = nums2;
        int[][] dp = new int[n + 2][n + 2];

        for (int len = 1; len <= n; len++) {
            for (int i = 1; i + len - 1 <= n; i++) {
                int j = i + len - 1;
                for (int k = i; k <= j; k++) {
                    dp[i][j] = Math.max(dp[i][j], dp[i][k - 1] + dp[k + 1][j] + nums[i - 1] * nums[k] * nums[j + 1]);
                }
            }
        }
        return dp[1][n];
    }
}
// @lc code=end

