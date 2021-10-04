/*
 * @lc app=leetcode id=1004 lang=java
 *
 * [1004] Max Consecutive Ones III
 */

// @lc code=start
class Solution {
    // time = O(n), space = O(1)
    public int longestOnes(int[] nums, int k) {
        // corner case
        if (nums == null || nums.length == 0 || k < 0) return 0;

        int n = nums.length;
        int count = 0, i = 0, res = 0;

        for (int j = 0; j < n; j++) {
            if (nums[j] == 1) res = Math.max(res, j - i + 1);
            else {
                count++;
                while (count > k) {
                    if (nums[i] == 0) count--;
                    i++;
                }
                res = Math.max(res, j - i + 1);
            }
        }
        return res;
    }
}
// @lc code=end

