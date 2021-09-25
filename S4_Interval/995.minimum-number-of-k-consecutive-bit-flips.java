/*
 * @lc app=leetcode id=995 lang=java
 *
 * [995] Minimum Number of K Consecutive Bit Flips
 */

// @lc code=start
class Solution {
    // time = O(n), space = O(n)
    public int minKBitFlips(int[] nums, int k) {
        int n = nums.length;
        int[] diff = new int[n + 1];
        int flips = 0, count = 0;

        for (int i = 0; i < n; i++) {
            flips += diff[i];
            if (nums[i] + flips % 2 == 1) continue;
            if (i + k - 1 >= n) return -1; // 越界
            flips++;
            diff[i + k] -= 1;
            count++;
        }
        return count;
    }
}
// @lc code=end

