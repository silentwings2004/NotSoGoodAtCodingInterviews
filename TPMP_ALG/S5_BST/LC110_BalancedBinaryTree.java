package S5_BST;

import java.util.*;

/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: BalancedBinaryTree
 * Creater: Silentwings
 * Date: Feb, 2020
 * Description: 110. Balanced Binary Tree
 */
public class LC110_BalancedBinaryTree {
    /**
     * Given a binary tree, determine if it is height-balanced.
     *
     * For this problem, a height-balanced binary tree is defined as:
     *
     * a binary tree in which the left and right subtrees of every node differ in height by no more than 1.
     *
     *
     *
     * Example 1:
     *
     * Given the following tree [3,9,20,null,null,15,7]:
     *
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     * Return true.
     *
     * Example 2:
     *
     * Given the following tree [1,2,2,3,3,null,null,4,4]:
     *
     *        1
     *       / \
     *      2   2
     *     / \
     *    3   3
     *   / \
     *  4   4
     * Return false.
     *
     * S1 / S2 :
     * time: O(nlogn)
     * space: O(n)
     *
     * S3:
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
    // S1: 先做pruning后recursion
    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        int leftHeight = getHeight(root.left);  // ==> t = O(n / 2)
        int rightHeight = getHeight(root.right);  // ==> t = O(n / 2)
        if (Math.abs(leftHeight - rightHeight) > 1) return false; // 提前结束，提前剪枝
        return  isBalanced(root.left) && isBalanced(root.right);
    }

    private int getHeight(TreeNode root) {     // ==> t = O(n)
        if (root == null) return 0;
        int leftHeight = getHeight(root.left);
        int rightHeight = getHeight(root.right);
        return Math.max(leftHeight, rightHeight) + 1;
    }

    // S2: 换一种方式做pruning
    public boolean isBalanced2(TreeNode root){
        if ( root == null ) return true;
        int leftHeight = getHeight(root.left);
        int rightHeight = getHeight(root.right);
        return Math.abs(leftHeight - rightHeight) <= 1 && isBalanced2(root.left) && isBalanced2(root.right);
        // return isBalanced2(root.left) && isBalanced2(root.right) && Math.abs(leftHeight - rightHeight) <= 1;
        // 结果是对的，但是失去了提前剪枝的可能，多了许多不必要的计算，没有pruning的效果，所以要往前写
    }
    // S1 / S2: time = (O(n / 2) + O(n / 2)) * log(n)层 = O（nlogn)

    // S3: 最优解 ==> 优化S1 / S2: getHeight和isBalance都是以recursion方式遍历整个树，如果能把这两个过程合二为一就能优化 ⇒
    // 在getHeight方法中引入一个getHeight不存在的值来代表isBalance中的false
    public boolean isBalanced3(TreeNode root) {
        if (root == null) return true;
        return getHeight(root) != -1;
    }

    private int getHeight(TreeNode root) {     // ==> t = O(n)
        if (root == null) return 0;
        int leftHeight = getHeight(root.left);
        int rightHeight = getHeight(root.right);
        if (leftHeight == -1 || rightHeight == -1 || Math.abs(leftHeight - rightHeight) > 1) return -1;
        return Math.max(leftHeight, rightHeight) + 1;
    }
}
