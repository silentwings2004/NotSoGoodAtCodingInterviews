/*
 * @lc app=leetcode id=4 lang=java
 *
 * [4] Median of Two Sorted Arrays
 */

// @lc code=start
class Solution {
    // time = O(log(m + n)), space = O(log(min(m, n)))
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        if ((m + n) % 2 == 1) {
            return findKthElement(nums1, 0, nums2, 0, (m + n) / 2 + 1);
        } else {
            return (findKthElement(nums1, 0, nums2, 0, (m + n) / 2) + findKthElement(nums1, 0, nums2, 0, (m + n) / 2 + 1)) / 2.0;
        }
    }

    private double findKthElement(int[] nums1, int a, int[] nums2, int b, int k) {
        int m = nums1.length, n = nums2.length;
        if (m - a > n - b) return findKthElement(nums2, b, nums1, a, k);
        // base case
        if (a == m) return nums2[b + k - 1];
        if (k == 1) return Math.min(nums1[a], nums2[b]);

        int k1 = 0, k2 = 0;
        if (a + k / 2 >= m) k1 = m - a;
        else k1 = k / 2;
        k2 = k - k1;

        if (nums1[a + k1 - 1] < nums2[b + k2 - 1]) {
            return findKthElement(nums1, a + k1, nums2, b, k - k1);
        } else {
            return findKthElement(nums1, a, nums2, b + k2, k - k2);
        }
    }
}
// @lc code=end

