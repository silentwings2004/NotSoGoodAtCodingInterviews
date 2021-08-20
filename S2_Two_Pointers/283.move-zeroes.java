/*
 * @lc app=leetcode id=283 lang=java
 *
 * [283] Move Zeroes
 */

// @lc code=start
class Solution {
    // time = O(n), space = O(1)
    public void moveZeroes(int[] nums) {
        // corner case
        if (nums == null || nums.length == 0) return;

        int i = 0, j = 0;
        int n = nums.length;
        while (j < n) {
            if (nums[j] == 0) j++;
            else swap(nums, i++, j++);
        }
    }
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
// @lc code=end

