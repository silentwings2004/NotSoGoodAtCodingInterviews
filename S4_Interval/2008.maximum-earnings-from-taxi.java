/*
 * @lc app=leetcode id=2008 lang=java
 *
 * [2008] Maximum Earnings From Taxi
 */

// @lc code=start
class Solution {
    // time = O(mlogm), space = O(m);
    public long maxTaxiEarnings(int n, int[][] rides) {
        int m = rides.length;
        Arrays.sort(rides, (o1, o2) -> o1[1] - o2[1]);

        int[] endTimes = new int[m];
        for (int i = 0; i < m; i++) endTimes[i] = rides[i][1];

        long[] dp = new long[m + 1];
        for (int i = 1; i <= m; i++) {
            dp[i] = dp[i - 1];
            int j = upperBound(endTimes, rides[i - 1][0]);
            dp[i] = Math.max(dp[i], dp[j + 1] + rides[i - 1][1] - rides[i - 1][0] + rides[i - 1][2]);
        }
        return dp[m];
    }

    private int upperBound(int[] nums, int t) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = right - (right - left) / 2;
            if (nums[mid] <= t) left = mid;
            else right = mid - 1;
        }
        return nums[left] <= t ? left : left - 1;
    }
}
// @lc code=end

