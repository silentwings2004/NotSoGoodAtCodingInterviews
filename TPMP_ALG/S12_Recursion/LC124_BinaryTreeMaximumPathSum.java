package S12_Recursion;
import java.util.*;

/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: BinaryTreeMaximumPathSum
 * Creater: Silentwings
 * Date: Mar, 2020
 * Description: 124. Binary Tree Maximum Path Sum
 */
public class LC124_BinaryTreeMaximumPathSum {
    /**
     * Given a non-empty binary tree, find the maximum path sum.
     *
     * For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree
     * along the parent-child connections. The path must contain at least one node and does not need to go through
     * the root.
     *
     * Example 1:
     *
     * Input: [1,2,3]
     *
     *        1
     *       / \
     *      2   3
     *
     * Output: 6
     * Example 2:
     *
     * Input: [-10,9,20,null,null,15,7]
     *
     *    -10
     *    / \
     *   9  20
     *     /  \
     *    15   7
     *
     * Output: 42
     *
     * @param root
     * @return
     */
    // 问题较难的情况下，先分解为几种简单的特殊case各个击破，从中发现规律再来解决最后的难题
    // Step 1: Q15.2 --> find the maximum path sum from any leaf node to root node (root to leaf)
    public int maxPathSum1(TreeNode root) {
        if (root == null) return 0;
        int left = maxPathSum(root.left);
        int right = maxPathSum(root.right);

        if (root.left == null) return right + root.val;
        if (root.right == null) return left + root.val;
        return Math.max(left, right) + root.val;
    }

    // Step 2: Q15.3 --> Any to Root
    public int maxPathSum2(TreeNode root) {
        if (root == null) return 0;
        int left = maxPathSum(root.left);
        int right = maxPathSum(root.right);

        return Math.max(0, Math.max(left, right)) + root.val;
    }

    // Step 3: Q15.1 --> leaf to leaf (no negative)
    public int maxPathSum3(TreeNode root) {
        if (root == null) return 0;
        int left = Math.max(0, maxPathSum(root.left));
        int right = Math.max(0, maxPathSum(root.right));

        int max = Integer.MIN_VALUE;
        // update global max if needed
        max = Math.max(max, left + right + root.val);
        return Math.max(left, right) + root.val;
    }

    // Step 4: Q15 --> any to any (Q15.1 + Q15.3)
    int res;
    public int maxPathSum(TreeNode root) {
        if (root == null) return 0;
        res = Integer.MIN_VALUE;
        helper(root);
        return res;
    }

    private int helper(TreeNode root) {
        if (root == null) return 0;
        int left = Math.max(0, helper(root.left));
        int right = Math.max(0, helper(root.right));
        res = Math.max(res, left + right + root.val);
        return Math.max(left, right) + root.val;
    }

    // Follow up --> minSum
    public int minPathSum(TreeNode root) {
        if (root == null) return 0;
        res = Integer.MAX_VALUE;
        helper2(root);
        return res;
    }

    private int helper2(TreeNode root) {
        if (root == null) return 0;
        int left = Math.min(0, helper(root.left));
        int right = Math.min(0, helper(root.right));
        res = Math.min(res, left + right + root.val);
        return Math.min(left, right) + root.val;
    }
}
