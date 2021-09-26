/*
 * @lc app=leetcode id=1891 lang=java
 *
 * [1891] Cutting Ribbons
 */

// @lc code=start
class Solution {
    // time = O(nlogS), space = O(1)
    public int maxLength(int[] ribbons, int k) {
        // corner case
        if (ribbons == null || ribbons.length == 0 || k <= 0) return 0;

        long sum = 0;
        for (int x : ribbons) sum += x;
        if (sum < k) return 0;

        long left = 1, right = sum;
        while (left < right) {
            long mid = right - (right - left) / 2;
            if (helper(ribbons, mid) >= k) left = mid;
            else right = mid - 1;
        }
        return (int) left;
    }

    private long helper(int[] nums, long t) {
        int count = 0;
        for (int x : nums) count += x / t;
        return count;
    }
}
// @lc code=end

