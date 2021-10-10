/*
 * @lc app=leetcode id=1893 lang=java
 *
 * [1893] Check if All the Integers in a Range Are Covered
 */

// @lc code=start
class Solution {
    // time = O(n), space = O(1)
    public boolean isCovered(int[][] ranges, int left, int right) {
        // corner case
        if (ranges == null || ranges.length == 0 || ranges[0] == null || ranges[0].length == 0) {
            return false;
        }

        int[] diff = new int[52];
        for (int[] range : ranges) {
            diff[range[0]]++;
            diff[range[1] + 1]--;
        }

        int sum = 0;
        for (int i = 0; i <= right; i++) {
            sum += diff[i];
            if (sum == 0 && i >= left) return false;
        }
        return true;
    }
}
// @lc code=end

