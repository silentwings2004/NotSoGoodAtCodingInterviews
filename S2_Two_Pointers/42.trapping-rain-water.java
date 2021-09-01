/*
 * @lc app=leetcode id=42 lang=java
 *
 * [42] Trapping Rain Water
 */

// @lc code=start
class Solution {
    // time = O(n), space = O(1)
    public int trap(int[] height) {
        // corner case
        if (height == null || height.length == 0) return 0;
    
        int len = height.length;
        int left = 0, right = len - 1;
        int leftMax = 0, rightMax = 0;
        int res = 0;
    
        while (left < right) { // 当左右相遇则表示已经遍历完整个数组，跳出loop
            // 这里写成 <= 也行，放在left或者right都没有问题，效果是一样的
            if (height[left] < height[right]) { // 左矮右高，计算并移动左边
                leftMax = Math.max(leftMax, height[left]); // 实时更新leftMax
                res += leftMax - height[left];
                left++;
            } else { // 相等的情况在此也一起归入右边，移动右指针
                rightMax = Math.max(rightMax, height[right]); // 实时更新rightMax
                res += rightMax - height[right];
                right--;
            }
        }
        return res;
    }
}
// @lc code=end

