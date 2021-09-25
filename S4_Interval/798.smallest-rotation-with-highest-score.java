/*
 * @lc app=leetcode id=798 lang=java
 *
 * [798] Smallest Rotation with Highest Score
 */

// @lc code=start
class Solution {
    // time = O(n), space = O(n)
    public int bestRotation(int[] nums) {
        // corner case
        if (nums == null || nums.length == 0) return 0;

        int n = nums.length;
        int[] diff = new int[n];

        for (int i = 0; i < n; i++) {
            if (nums[i] <= i) {
                diff[0] += 1;
                diff[(i - (nums[i] - 1)) % n] -= 1;
                diff[(i - 0 + 1) % n] += 1;
            } else {
                diff[0] += 0;
                diff[(i + 1) % n] += 1;
                diff[(i + n - nums[i] + 1) % n] -= 1; // diff[i + 1 + (n - 1) - (nums[i] - 1)]
            }
        }
        int score = 0, max = 0, k = 0;
        for (int i = 0; i < n; i++) {
            score += diff[i];
            if (score > max) {
                max = score;
                k = i;
            } 
        }
        return k;
    }
}
/**
 *    A[i]-1       i
 * 1    3     0    2    4     => i - (A[i] - 1)
 *                  i   A[i]          
 * 2    3      1    4    0
 */
// @lc code=end

