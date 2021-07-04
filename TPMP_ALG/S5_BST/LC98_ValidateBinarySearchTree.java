package S5_BST;

import java.util.*;

/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: ValidateBinarySearchTree
 * Creater: Silentwings
 * Date: Feb, 2020
 * Description: 98. Validate Binary Search Tree
 */

class ResultType {
    boolean isBST;
    int min, max;
    ResultType (boolean isBST, int min, int max) {
        this.isBST = isBST;
        this.min = min;
        this.max = max;
    }
}

public class LC98_ValidateBinarySearchTree {
    /**
     * Given a binary tree, determine if it is a valid binary search tree (BST).
     *
     * Assume a BST is defined as follows:
     *
     * The left subtree of a node contains only nodes with keys less than the node's key.
     * The right subtree of a node contains only nodes with keys greater than the node's key.
     * Both the left and right subtrees must also be binary search trees.
     *
     *
     * Example 1:
     *
     *     2
     *    / \
     *   1   3
     *
     * Input: [2,1,3]
     * Output: true
     * Example 2:
     *
     *     5
     *    / \
     *   1   4
     *      / \
     *     3   6
     *
     * Input: [5,1,4,null,null,3,6]
     * Output: false
     * Explanation: The root node's value is 5 but its right child's value is 4.
     *
     * time : O(n)
     * space : O(logn)
     *
     * @param root
     * @return
     *
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
    // S1: 自上向下carry一个边界，先办事后recursion，遍历到leaf node就return true
    // time = O(n), space = O(n)
    public boolean isValidBST(TreeNode root) {
        // corner case
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE); //用long代替int就是为了包括int的边界条件
    }

    private boolean isValidBST(TreeNode root, long lowerBound, long upperBound) {
        if (root == null) return true;
        if (root.val >= upperBound || root.val <= lowerBound) return false;
        // 这里如果用int，那么tree可能取到int里的最大和最小值，那么将在下面的if语句里返回false，
        // 因此在这里必须要扩展为long来限定tree所能取到的所有int范围-2^31 ~ 2^31 - 1
        return isValidBST(root.left, lowerBound, root.val) && isValidBST(root.right, root.val, upperBound);
    }

    // S1.5: The min / max solution
    public boolean isValidBST1(TreeNode root) {
        return isValidBST1(root, null, null);
    }

    private boolean isValidBST1(TreeNode root, Integer min, Integer max) {
        if (root == null) return true;
        if (min != null && root.val <= min || max != null && root.val >= max) return false;
        if (!isValidBST1(root.left, min, root.val) || !isValidBST1(root.right, root.val, max)) return false;
        return true;
    }

    // S2: Use min max to divide and conquer // recursion from bottom to top
    public boolean isValidBST2(TreeNode root) {
        ResultType result = helper(root);
        return result.isBST;
    }

    private ResultType helper(TreeNode root) {
        if (root == null) {
            return new ResultType(true, Integer.MAX_VALUE, Integer.MIN_VALUE);
            // 要分别取左子树的最大值和右子树的最小值与root去比较
        }

        ResultType left = helper(root.left);
        ResultType right = helper(root.right);

        if (!left.isBST || !right.isBST) {
            // if is_bst is false then min and max are useless
            return new ResultType(false, 0, 0);
        }

        if (root.left != null && root.val <= left.max || root.right != null && root.val >= right.min) {
            return new ResultType(false, 0, 0);
        }

        return new ResultType(true, Math.min(root.val, left.min), Math.max(root.val, right.max));
        // 向上返值要分别用左边的最小和右边的最大来限定区间
    }

    // S3: inorder traversal ascending sequence 使用中序遍历 → 升序
    private TreeNode prev;
    public boolean isValidBST3(TreeNode root) {
        prev = null;
        return inorder(root);
    }

    private boolean inorder(TreeNode root) {
        if (root == null) return true;
        if (!inorder(root.left)) return false;
        if (prev != null && root.val <= prev.val) return false;
        prev = root;
        return inorder(root.right);
    }
}