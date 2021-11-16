import java.util.HashMap;

import javax.swing.tree.TreeNode;

/*
 * @lc app=leetcode id=337 lang=java
 *
 * [337] House Robber III
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
    HashMap<TreeNode, Integer> flag0;
    HashMap<TreeNode, Integer> flag1;
    public int rob(TreeNode root) {
        // corner case
        if (root == null) return 0;
        flag0 = new HashMap<>();
        flag1 = new HashMap<>();

        return dfs(root, 1);
    }

    private int dfs(TreeNode node, int flag) {
        // base case
        if (node == null) return 0;

        if (flag == 1 && flag1.containsKey(node)) return flag1.get(node);
        if (flag == 0 && flag0.containsKey(node)) return flag0.get(node);
        
        int res = 0;
        if (flag == 0) {
            res = dfs(node.left, 1) + dfs(node.right, 1);
        } else {
            int option1 = node.val + dfs(node.left, 0) + dfs(node.right, 0);
            int option2 = dfs(node.left, 1) + dfs(node.right, 1);
            res = Math.max(option1, option2);
        }

        if (flag == 0) flag0.put(node, res);
        if (flag == 1) flag1.put(node, res);
        return res;
    }
}
// @lc code=end

