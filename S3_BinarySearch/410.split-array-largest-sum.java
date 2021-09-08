/*
 * @lc app=leetcode id=410 lang=java
 *
 * [410] Split Array Largest Sum
 */

// @lc code=start
class Solution {
    // S1: DP
    public int splitArray1(int[] nums, int m) {
        // corner case
        if (nums == null || nums.length == 0 || m <= 0) return 0;

        int n = nums.length;
        int[][] dp = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) dp[i][1] = dp[i - 1][1] + nums[i - 1];

        for (int i = 1; i <= n; i++) {
            for (int k = 2; k <= Math.min(i, m); k++) {
                dp[i][k] = Math.max(dp[i - 1][k - 1], nums[i - 1]);
                for (int j = 1; j < i; j++) {
                    if (j >= k - 1) {
                        int temp = Math.max(dp[j][k - 1], dp[i][1] - dp[j][1]);
                        dp[i][k] = Math.min(dp[i][k], temp);
                    }
                }
            }
        }
        return dp[n][m];
    }

    // S2: BS
    // time = O(nlogS), space = O(1)  S: sum of array
    public int splitArray(int[] nums, int m) {
        // corner case
        if (nums == null || nums.length == 0 || m <= 0) return 0;

        int left = 0, right = Integer.MAX_VALUE;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (!checkOK(nums, m, mid)) left = mid + 1;
            else right = mid;
        }
        return left;
    }

    private boolean checkOK(int[] nums, int m, int target) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > target) return false;
            int j = i, sum = 0;
            while (j < nums.length && sum + nums[j] <= target) sum += nums[j++];
            count++;
            i = j - 1; // outer for loop has i++
        }
        return count <= m;
    }
}
// @lc code=end

