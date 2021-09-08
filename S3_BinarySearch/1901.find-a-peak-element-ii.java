/*
 * @lc app=leetcode id=1901 lang=java
 *
 * [1901] Find a Peak Element II
 */

// @lc code=start
class Solution {
    // time = O(nlogm), space = O(1)
    public int[] findPeakGrid(int[][] mat) {
        // corner case
        if (mat == null || mat.length == 0 || mat[0] == null || mat[0].length == 0) return new int[0];

        int m = mat.length;
        int left = 0, right = m - 1, idx = 0;
        while (left < right) {
            int mid = left + (right - left) / 2;
            idx = findPeak(mat[mid]);
            if (mat[mid][idx] < mat[mid + 1][idx]) left = mid + 1;
            else right = mid; 
        }
        return new int[]{left, idx};
    }

    private int findPeak(int[] nums) {
        int n = nums.length, idx = 0, max = nums[0];
        for (int i = 0; i < n; i++) {
            if (nums[i] > max) {
                max = nums[i];
                idx = i;
            }
        }
        return idx;
    }
}
// @lc code=end

