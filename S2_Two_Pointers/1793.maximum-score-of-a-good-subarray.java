/*
 * @lc app=leetcode id=1793 lang=java
 *
 * [1793] Maximum Score of a Good Subarray
 */

// @lc code=start
class Solution {
    // time = O(n), space = O(1)
    public int maximumScore(int[] nums, int k) {
        int n = nums.length;
        int i = k, j = k;
        int min = nums[k], res = 0;

        while (i >= 0 || j < n) {
            while (i >= 0 && nums[i] >= min) i--;
            while (j < n && nums[j] >= min) j++;
            res = Math.max(res, min * (j - i - 1));
            min = Math.max(i >= 0 ? nums[i] : Integer.MIN_VALUE, j < n ? nums[j] : Integer.MIN_VALUE);
        }
        return res;
    }
}
// @lc code=end

