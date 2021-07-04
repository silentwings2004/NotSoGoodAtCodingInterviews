package S5_BST;

import java.util.*;

/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: SameTree
 * Creater: Silentwings
 * Date: Feb, 2020
 * Description: 100. Same Tree
 */
public class LC100_SameTree {
    /**
     * Given two binary trees, write a function to check if they are the same or not.
     *
     * Two binary trees are considered the same if they are structurally identical and the nodes have the same value.
     *
     * Example 1:
     *
     * Input:     1         1
     *           / \       / \
     *          2   3     2   3
     *
     *         [1,2,3],   [1,2,3]
     *
     * Output: true
     * Example 2:
     *
     * Input:     1         1
     *           /           \
     *          2             2
     *
     *         [1,2],     [1,null,2]
     *
     * Output: false
     * Example 3:
     *
     * Input:     1         1
     *           / \       / \
     *          2   1     1   2
     *
     *         [1,2,1],   [1,1,2]
     *
     * Output: false
     *
     * time : O(n)
     * space : O(n)
     *
     * @param p
     * @param q
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
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;
        if (p.val != q.val) return false;
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}
