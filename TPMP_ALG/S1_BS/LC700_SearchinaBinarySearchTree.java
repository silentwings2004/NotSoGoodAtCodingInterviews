package S1_BS;
import java.util.*;
/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: SearchinaBinarySearchTree
 * Creater: Silentwings
 * Date: Aug, 2020
 * Description: 700. Search in a Binary Search Tree
 */
public class LC700_SearchinaBinarySearchTree {
    /**
     * Given the root node of a binary search tree (BST) and a value. You need to find the node in the BST that the
     * node's value equals the given value. Return the subtree rooted with that node. If such node doesn't exist, you
     * should return NULL.
     *
     * For example,
     *
     * Given the tree:
     *         4
     *        / \
     *       2   7
     *      / \
     *     1   3
     *
     * And the value to search: 2
     * You should return this subtree:
     *
     *       2
     *      / \
     *     1   3
     * In the example above, if we want to search the value 5, since there is no node with value 5, we should return
     * NULL.
     *
     * Note that an empty tree is represented by NULL, therefore you would see the expected output (serialized tree
     * format) as [], not null.
     * @param root
     * @param val
     * @return
     */
    // S1: recursion
    // time = O(n), space = O(1)
    public TreeNode searchBST(TreeNode root, int val) {
        // corner case
        if (root == null || root.val == val) return root;

        return root.val > val ? searchBST(root.left, val) : searchBST(root.right, val);
    }

    // S2: iteration
    // time = O(n), space = O(1)
    public TreeNode searchBST2(TreeNode root, int val) {
        // corner case
        if (root == null || root.val == val) return root;

        TreeNode cur = root;
        while (cur != null) {
            if (cur.val == val) return cur;
            if (cur.val < val) cur = cur.right;
            else cur = cur.left;
        }
        return cur;
    }
}
