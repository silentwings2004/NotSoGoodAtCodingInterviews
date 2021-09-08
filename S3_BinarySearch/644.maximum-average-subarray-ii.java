/*
 * @lc app=leetcode id=644 lang=java
 *
 * [644] Maximum Average Subarray II
 */

// @lc code=start
class Solution {
    // time = O(nlog(max - min)), space = O(1)
    public double findMaxAverage(int[] nums, int k) {
        // corner case
        if (nums == null || nums.length == 0 || k < 0) return 0;

        double left = Integer.MAX_VALUE, right = Integer.MIN_VALUE;
        for (int num : nums) {
            left = Math.min(left, num);
            right = Math.max(right, num);
        }

        while (left + 1e-5 < right) {
            double mid = left + (right - left) / 2;
            if (checkOK(nums, mid, k)) left = mid;
            else right = mid;
        }
        return left;
    }

    private boolean checkOK(int[] nums, double mid, int k) {
        int n = nums.length;
        double curSum = 0, preSum = 0, minSum = 0;

        for (int i = 0; i < n; i++) {
            curSum += nums[i] - mid;
            if (i >= k - 1) {
                if (curSum - minSum >= 0) return true;
                preSum += nums[i - k + 1] - mid;
                minSum = Math.min(minSum, preSum);
            }
        }
        return false;
    }
}
// @lc code=end

