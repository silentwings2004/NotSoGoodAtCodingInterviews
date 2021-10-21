/*
 * @lc app=leetcode id=668 lang=java
 *
 * [668] Kth Smallest Number in Multiplication Table
 */

// @lc code=start
class Solution {
    // time = O(m * log(m * n)), space = O(1)
    public int findKthNumber(int m, int n, int k) {
        int left = 1, right = m * n;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (helper(m, n, mid) < k) left = mid + 1;
            else right = mid;
        }
        return left;
    }

    private int helper(int m, int n, int t) {
        int count = 0;
        for (int i = 1; i <= m; i++) {
            count += Math.min(n, t / i);
        }
        return count;
    }
}
// @lc code=end

