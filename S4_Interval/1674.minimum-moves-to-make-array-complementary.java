/*
 * @lc app=leetcode id=1674 lang=java
 *
 * [1674] Minimum Moves to Make Array Complementary
 */

// @lc code=start
class Solution {
    // time = O(n), space = O(n)
    public int minMoves(int[] nums, int limit) {
        int[] diff = new int[2 * 100000 + 2];
        
        int n = nums.length;
        for (int i = 0; i < n / 2; i++) {
            int a = Math.min(nums[i], nums[n - 1 - i]);
            int b = Math.max(nums[i], nums[n - 1 - i]);

            diff[2] += 2;
            diff[a + 1] -= 1;
            diff[a + b] -= 1;
            diff[a + b + 1] += 1;
            diff[limit + 1 + b] += 1;
        }

        int y = 0, res = Integer.MAX_VALUE;
        for (int x = 2; x <= limit * 2; x++) {
            y += diff[x];
            res = Math.min(res, y);
        }
        return res;
    }
}
// @lc code=end

