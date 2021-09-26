/*
 * @lc app=leetcode id=774 lang=java
 *
 * [774] Minimize Max Distance to Gas Station
 */

// @lc code=start
class Solution {
    // time = O(nlogS), space = O(1)
    public double minmaxGasDist(int[] stations, int k) {
        int n = stations.length;
        double left = 0, right = stations[n - 1] - stations[0];

        while (left + 1e-6 < right) {
            double mid = left + (right - left) / 2;
            if (helper(stations, mid) > k) left = mid;
            else right = mid;
        }
        return left;
    }

    private int helper(int[] nums, double t) {
        int n = nums.length, count = 0;
        for (int i = 1; i < n; i++) {
            double dist = nums[i] - nums[i - 1];
            count += Math.ceil(dist / t) - 1;
        }
        return (int) count;
    }
}
// @lc code=end

