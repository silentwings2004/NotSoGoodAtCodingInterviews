/*
 * @lc app=leetcode id=270 lang=java
 *
 * [270] Closest Binary Search Tree Value
 */

// @lc code=start
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    // time = O(n), space = O(1)
    public int closestValue(TreeNode root, double target) {
        TreeNode cur = root;
        int res = root.val;

        while (cur != null) {
            if (Math.abs(cur.val - target) < Math.abs(res - target)) res = cur.val;
            if (cur.val < target) cur = cur.right;
            else if (cur.val > target) cur = cur.left;
            else return cur.val;
        }
        return res;
    }
}
// @lc code=end

