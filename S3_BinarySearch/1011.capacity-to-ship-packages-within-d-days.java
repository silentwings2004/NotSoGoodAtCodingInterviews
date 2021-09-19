/*
 * @lc app=leetcode id=1011 lang=java
 *
 * [1011] Capacity To Ship Packages Within D Days
 */

// @lc code=start
class Solution {
     // time = O(nlogn), space = O(1)
     public int shipWithinDays(int[] weights, int days) {
        // corner case
        if (weights == null || weights.length == 0) return 0;
        
        int max = 1;
        for (int weight : weights) max = Math.max(max, weight);
        
        int left = max, right = Integer.MAX_VALUE;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (helper(weights, mid) > days) left = mid + 1;
            else right = mid;
        }
        return left;
    }

    private int helper(int[] nums, int t) {
        int day = 1, sum = 0;
        for (int num : nums) {
            if (sum + num <= t) sum += num;
            else {
                day++;
                sum = num;
            }
        }
        return day;
    }
}
// @lc code=end

