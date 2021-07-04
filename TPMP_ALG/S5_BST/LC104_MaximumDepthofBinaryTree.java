package S5_BST;

import java.util.*;

/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: MaximumDepthofBinaryTree
 * Creater: Silentwings
 * Date: Feb, 2020
 * Description: 104. Maximum Depth of Binary Tree
 */

public class LC104_MaximumDepthofBinaryTree {
    /**
     * Given a binary tree, find its maximum depth.
     *
     * The maximum depth is the number of nodes along the longest path from the root node
     * down to the farthest leaf node.
     *
     * Note: A leaf is a node with no children.
     *
     * Example:
     *
     * Given binary tree [3,9,20,null,null,15,7],
     *
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     * return its depth = 3.
     *
     * time : O(n)
     * space : O(n)
     *
     * @param root
     * @return
     */
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode(int x) { val = x; }
     * }
     */
    public int maxDepth(TreeNode root) {
        // base case
        if (root == null) return 0;

        int leftHeight = maxDepth(root.left);
        int rightHeight = maxDepth(root.right);
        return Math.max(leftHeight, rightHeight) + 1;
    }
}