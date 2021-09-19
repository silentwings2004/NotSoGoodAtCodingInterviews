/*
 * @lc app=leetcode id=1539 lang=java
 *
 * [1539] Kth Missing Positive Number
 */

// @lc code=start
class Solution {
    // time = O(logn * logn), space = O(1)
    public int findKthPositive(int[] arr, int k) {
        // corner case
        if (arr == null || arr.length == 0 || k < 0) return 0;
        
        int n = arr.length;
        int left = 1, right = arr[n - 1] + k;
        while (left < right) {
            int mid = right - (right - left) / 2;
            int M = mid - 1;
            int T = lowerBound(arr, mid) + 1;
            if (M - T <= k - 1) left = mid;
            else right = mid - 1;
        }
        return left;
    }

    private int lowerBound(int[] nums, int t) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = right - (right - left) / 2;
            if (nums[mid] < t) left = mid;
            else right = mid - 1;
        }
        return nums[left] < t ? left : left - 1;
    }
}
// @lc code=end

