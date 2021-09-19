/*
 * @lc app=leetcode id=215 lang=java
 *
 * [215] Kth Largest Element in an Array
 */

// @lc code=start
class Solution {
    // time = O(nlogn), space = O(1)
    public int findKthLargest(int[] nums, int k) {
        // corner case
        if (nums == null || nums.length == 0 || k < 0) return 0;

        int left = -10000, right = 10000;
        while (left < right) {
            int mid = right - (right - left) / 2;
            if (helper(nums, mid) < k) right = mid - 1;
            else left = mid;
        }
        return left;
    }

    private int helper(int[] nums, int t) {
        int count = 0;
        for (int num : nums) {
            if (num >= t) count++;
        }
        return count;
    }
}
// @lc code=end

