/*
 * @lc app=leetcode id=124 lang=java
 *
 * [124] Binary Tree Maximum Path Sum
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
    // time = O(n), space = O(n)
    private int res = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        // corner case
        if (root == null) return 0;

        dfs(root);
        return res;
    }

    private int dfs(TreeNode node) {
        if (node == null) return 0;

        int leftSum = dfs(node.left);
        int rightSum = dfs(node.right);
        
        res = Math.max(res, node.val + Math.max(0, leftSum) + Math.max(0, rightSum));
        return node.val + Math.max(0, Math.max(leftSum, rightSum));
    }
}
// @lc code=end

