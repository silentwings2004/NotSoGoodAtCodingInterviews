package S5_BST;

import java.util.*;

/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: ClosestBinarySearchTreeValue
 * Creater: Silentwings
 * Date: Feb, 2020
 * Description: 270. Closest Binary Search Tree Value
 */

public class LC270_ClosestBinarySearchTreeValue {
    /**
     * Given a non-empty binary search tree and a target value, find the value in the BST that is closest to the target.
     *
     * Note:
     *
     * Given target value is a floating point.
     * You are guaranteed to have only one unique value in the BST that is closest to the target.
     * Example:
     *
     * Input: root = [4,2,5,1,3], target = 3.714286
     *
     *     4
     *    / \
     *   2   5
     *  / \
     * 1   3
     *
     * Output: 4
     *
     * time : O(logn)
     * space : O(1)
     *
     * @param root
     * @param target
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
    // time = O(logn), space = O(1)
    public int closestValue(TreeNode root, double target) {
        if (root == null) throw new NullPointerException();
        TreeNode cur = root; // 不要动root,设置一个cur去遍历
        int res = root.val; // 初始化最近值，可以初始化为root.val，尽量不要hard code，don't introduce 0 manually

        while (cur != null) {
            if (Math.abs(cur.val - target) <= Math.abs(res - target)) {
                res = cur.val;
            }
            if (cur.val == target) return cur.val; //只要出现相等就可以直接返回，因为没有比相等更接近target的case了
            if (cur.val < target) cur = cur.right;
            else cur = cur.left;
        }
        return res;
    }
}
