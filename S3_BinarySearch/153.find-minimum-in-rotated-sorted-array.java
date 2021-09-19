/*
 * @lc app=leetcode id=153 lang=java
 *
 * [153] Find Minimum in Rotated Sorted Array
 */

// @lc code=start
class Solution {
    // time = O(logn), space = O(1)
    public int findMin(int[] nums) {
        // corner case
        if (nums == null || nums.length == 0) return 0;

        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] >= nums[right]) left = mid + 1;
            else right = mid;
        }
        return nums[left];
    }
}
// @lc code=end

