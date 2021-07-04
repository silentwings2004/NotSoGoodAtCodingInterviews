package S5_BST;

import java.util.*;

/**
 * Project Name: Lintcode
 * Package Name: lintcode
 * File Name: SearchRangeinBinarySearchTree
 * Creater: Silentwings
 * Date: Feb, 2020
 * Description: 11. Search Range in Binary Search Tree
 */

public class LN11_SearchRangeinBinarySearchTree {
    /**
     * Given a binary search tree and a range [k1, k2], return node values within a given range in ascending order.
     *
     * Example
     * Example 1:
     *
     * Input：{5},6,10
     * Output：[]
     *         5
     * it will be serialized {5}
     * No number between 6 and 10
     * Example 2:
     *
     * Input：{20,8,22,4,12},10,22
     * Output：[12,20,22]
     * Explanation：
     *         20
     *        /  \
     *       8   22
     *      / \
     *     4   12
     * it will be serialized {20,8,22,4,12}
     * [12,20,22] between 10 and 22
     *
     * S1:
     * time : O(n)
     * space : O(n)
     *
     * S1.5:
     * time : O(logn) ~ O(n)
     * space : O(n)
     *
     * @param root The root of the binary search tree
     * @param k1 An integer
     * @param k2 An integer
     * @return Return all keys that k1<=key<=k2 in ascending order
     */
    // S1: use inorder traversal by recursion, check whether current root's value in the given range.
    private List<Integer> result;

    public List<Integer> searchRange(TreeNode root, int k1, int k2) {
        // write your code here
        result = new ArrayList<>();
        helper(root, k1, k2);
        return result;
    }

    private void helper(TreeNode root, int k1, int k2) {
        if (root == null) return;
        helper(root.left, k1, k2);
        if (k1 <= root.val && root.val <= k2) result.add(root.val);
        helper(root.right, k1, k2);
    }

    // S1.5: every time when you do recursion to next level. check if you should go that side.
    // decide left or not , check if cur.value > min / max, there may be candidate in the left subtree
    // decide right or not, check if cur.value < max / min,there may be candidate in the right subtree
    public List<Integer> searchRange1(TreeNode root, int k1, int k2) {
        // write your code here
        result = new ArrayList<>();
        helper2(root, k1, k2);
        return result;
    }

    private void helper2(TreeNode root, int k1, int k2) {
        if (root == null) return;
        if (root.val > k1) helper2(root.left, k1, k2);
        if (k1 <= root.val && root.val <= k2) result.add(root.val);
        if (root.val < k2) helper2(root.right, k1, k2);
    }
    // 极端情况，k1 = k2，找range → 找点 ==> 从上到下只有一条路径 → O(h) = O(logn) 层数
}
