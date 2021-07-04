package S5_BST;

import java.util.*;

/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: MinimumDepthofBinaryTree
 * Creater: Silentwings
 * Date: Feb, 2020
 * Description: 111. Minimum Depth of Binary Tree
 */

public class LC111_MinimumDepthofBinaryTree {
    /**
     * Given a binary tree, find its minimum depth.
     *
     * The minimum depth is the number of nodes along the shortest path from the root node
     * down to the nearest leaf node.
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
     * return its minimum depth = 2.
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
    // time = O(n), space = O(n)
    public int minDepth(TreeNode root) {
        // corner case
        if (root == null) return 0;

        int leftHeight = minDepth(root.left);
        int rightHeight = minDepth(root.right);
        if (leftHeight == 0) return rightHeight + 1;
        if (rightHeight == 0) return leftHeight + 1;
        return Math.min(leftHeight, rightHeight) + 1;
    }
}
