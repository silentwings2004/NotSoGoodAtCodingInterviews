/*
 * @lc app=leetcode id=80 lang=java
 *
 * [80] Remove Duplicates from Sorted Array II
 */

// @lc code=start
class Solution {
    public int removeDuplicates(int[] nums) {
        // corner case
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return 1;

        int n = nums.length, j = 1;
        for (int i = 2; i < n; i++) {
            if (nums[i] == nums[j] && nums[i] == nums[j - 1]) continue;
            nums[++j] = nums[i];
        }
        return j + 1;
    }
}
// @lc code=end

