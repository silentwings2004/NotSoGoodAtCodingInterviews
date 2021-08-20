/*
 * @lc app=leetcode id=26 lang=java
 *
 * [26] Remove Duplicates from Sorted Array
 */

// @lc code=start
class Solution {
    // time = O(n), space = O(1)
    public int removeDuplicates(int[] nums) {
        // corner case
        if (nums == null || nums.length == 0) return 0;

        int n = nums.length, j = 1;
        for (int i = 1; i < n; i++) {
            if (nums[i] != nums[j - 1]) nums[j++] = nums[i];
        }
        return j;
    }
}
// @lc code=end

