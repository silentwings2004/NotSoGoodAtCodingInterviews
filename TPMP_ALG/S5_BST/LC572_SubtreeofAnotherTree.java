package S5_BST;

import java.util.*;

/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: SubtreeofAnotherTree
 * Creater: Silentwings
 * Date: Feb, 2020
 * Description: 572. Subtree of Another Tree
 */

public class LC572_SubtreeofAnotherTree {
    /**
     * Given two non-empty binary trees s and t, check whether tree t has exactly the same structure and node values with a subtree of s. A subtree of s is a tree consists of a node in s and all of this node's descendants. The tree s could also be considered as a subtree of itself.
     *
     * Example 1:
     * Given tree s:
     *
     *      3
     *     / \
     *    4   5
     *   / \
     *  1   2
     * Given tree t:
     *    4
     *   / \
     *  1   2
     * Return true, because t has the same structure and node values with a subtree of s.
     * Example 2:
     * Given tree s:
     *
     *      3
     *     / \
     *    4   5
     *   / \
     *  1   2
     *     /
     *    0
     * Given tree t:
     *    4
     *   / \
     *  1   2
     * Return false.
     *
     * time : O(n)
     * space : O(n)
     *
     * @param s
     * @param t
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
    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (s == null) return t == null;  // if (t == null) return true; else return false;
        if (isSameTree(s, t)) return true;   // check if s and t are same tree, if not, continue to check subtree below
        return isSubtree(s.left, t) || isSubtree(s.right, t);
    }

    private boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;
        if (p.val != q.val) return false;
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    // follow up: How about t is part of the large binary tree s
    public boolean isPartofTree(TreeNode s, TreeNode t) {
        if (s == null) return t == null;  // if (t == null) return true; else return false;
        if (isSameTree2(s, t)) return true;   // check if s and t are same tree, if not, continue to check subtree below
        return isPartofTree(s.left, t) || isPartofTree(s.right, t);
    }

    private boolean isSameTree2(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p != null && q == null) return true;
        if (p == null && q != null) return false;
        if (p.val != q.val) return false;
        return isSameTree2(p.left, q.left) && isSameTree2(p.right, q.right);
    }
}