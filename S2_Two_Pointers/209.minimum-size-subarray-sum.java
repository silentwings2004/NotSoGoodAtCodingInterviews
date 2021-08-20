/*
 * @lc app=leetcode id=209 lang=java
 *
 * [209] Minimum Size Subarray Sum
 */

// @lc code=start
class Solution {
    // time = O(n), space = O(1)
    public int minSubArrayLen(int target, int[] nums) {
        // corner case
        if (nums == null || nums.length == 0) return 0;

        int n = nums.length;
        int i = 0; // i -> left, j -> right
        int sum = 0, res = Integer.MAX_VALUE;

        for (int j = 0; j < n; j++) {
            sum += nums[j];
            if (sum < target) continue;
            else {
                while (sum >= target) {
                    res = Math.min(res, j - i + 1);
                    sum -= nums[i];
                    i++;
                }
            }
        }
        return res ==Integer.MAX_VALUE ? 0 : res;
    }
}
// @lc code=end

