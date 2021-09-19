/*
 * @lc app=leetcode id=1870 lang=java
 *
 * [1870] Minimum Speed to Arrive on Time
 */

// @lc code=start
class Solution {
    // time = O(nlogn), space = O(1)
    public int minSpeedOnTime(int[] dist, double hour) {
        int n = dist.length;
        if (hour <= n - 1) return -1;

        int left = 1, right = (int) 1e7;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (helper(dist, mid) > hour) left = mid + 1;
            else right = mid;
        }
        return left;
    }

    private double helper(int[] nums, int t) {
        double time = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            time += Math.ceil(nums[i] * 1.0 / t);
        }
        time += nums[nums.length - 1] * 1.0 / t;
        return time;
    }
}
// @lc code=end

