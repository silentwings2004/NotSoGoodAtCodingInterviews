/*
 * @lc app=leetcode id=1537 lang=java
 *
 * [1537] Get the Maximum Score
 */

// @lc code=start
class Solution {
    // time = O(m + n), space = O(1)
    public int maxSum(int[] nums1, int[] nums2) {
        int i = 0, j = 0;
        long x = 0, y = 0;
        int m = nums1.length, n = nums2.length;
        long M = (long)(1e9 + 7);
    
        while (i < m || j < n) {
            if (i == m) {
                y += nums2[j];
                j++;
            } else if (j == n) {
                x += nums1[i];
                i++;
            } else if (nums1[i] < nums2[j]) {
                x += nums1[i];
                i++;
            } else if (nums1[i] > nums2[j]){
                y += nums2[j];
                j++;
            } else if (nums1[i] == nums2[j]) { // 到达传送门位置
                x = Math.max(x, y) + nums1[i];
                y = x;
                i++;
                j++;
            }
        }
    
        return (int)(Math.max(x, y) % M);
    }
}
// @lc code=end

