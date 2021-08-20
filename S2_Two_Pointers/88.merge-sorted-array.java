/*
 * @lc app=leetcode id=88 lang=java
 *
 * [88] Merge Sorted Array
 */

// @lc code=start
class Solution {
    // time = O(m + n), space = O(1)
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1, j = n - 1, p = m + n - 1;
        while (i >= 0 && j >= 0) {
            if (nums1[i] < nums2[j]) {
                nums1[p--] = nums2[j--];
            } else nums1[p--] = nums1[i--];
        }
        while (i >= 0) nums1[p--] = nums1[i--];
        while (j >= 0) nums1[p--] = nums2[j--];
    }
}
// @lc code=end

