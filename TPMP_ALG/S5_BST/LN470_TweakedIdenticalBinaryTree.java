package S5_BST;

import java.util.*;

/**
 * Project Name: Lintcode
 * Package Name: lintcode
 * File Name: TweakedIdenticalBinaryTree
 * Creater: Silentwings
 * Date: Feb, 2020
 * Description: 470.Tweaked identical Binary Tree
 */
public class LN470_TweakedIdenticalBinaryTree {
    /**
     * Check two given binary trees are identical or not.
     * Assuming any number of tweaks are allowed.
     * A tweak is defined as a swap of the children of one node in the tree.
     * Example
     *     1             1
     *    / \           / \
     *   2   3   and   3   2
     *  /                   \
     * 4                     4
     * are identical.
     *     1             1
     *    / \           / \
     *   2   3   and   3   4
     *  /                   \
     * 4                     2
     * are not identical.
     * Note
     * There is no two nodes with the same value in the tree.
     * Challenge
     * O(n) time
     *
     * time : O(n^2)
     * space : O(n^2)
     *
     * @param a the root of binary trees
     * @param b the root of binary trees
     * @return true if they are tweaked identical, or false
     */
    /**
     * Definition of TreeNode:
     * public class TreeNode {
     *     public int val;
     *     public TreeNode left, right;
     *     public TreeNode(int val) {
     *         this.val = val;
     *         this.left = this.right = null;
     *     }
     * }
     */
    // LC100 + LC101 ==> LN470
    public boolean isTweakedIdentical(TreeNode a, TreeNode b) {
        if (a == null && b ==null) return true;
        if (a == null || b == null) return false;
        if (a.val != b.val) return false;
        // 要么符合symmetric tree的要求，要么就符合same tree的要求，中间用"||"连接
        return isTweakedIdentical(a.left, b.left) && isTweakedIdentical(a.right, b.right) ||
                isTweakedIdentical(a.left, b.right) && isTweakedIdentical(a.right, b.left);
    }
    //最坏情况每次分4叉 ==> 1, 4, 4^2, ... 4^logn - 1 ==> time = O(4^logn) = O(n^2)
}
