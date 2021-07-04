package S12_Recursion;
import java.util.*;

/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: FlattenBinaryTreetoLinkedList
 * Creater: Silentwings
 * Date: Mar, 2020
 * Description: 114. Flatten Binary Tree to Linked List
 */
public class LC114_FlattenBinaryTreetoLinkedList {
    /**
     * Given a binary tree, flatten it to a linked list in-place.
     *
     * For example, given the following tree:
     *
     *     1
     *    / \
     *   2   5
     *  / \   \
     * 3   4   6
     * The flattened tree should look like:
     *
     * 1
     *  \
     *   2
     *    \
     *     3
     *      \
     *       4
     *        \
     *         5
     *          \
     *           6
     *
     * @param root
     */
    // S1: preOrder --> 左子树插入到右子树的地方; 将原来的右子树接到左子树的最右边节点。
    // 考虑新的右子树的根节点，一直重复上边的过程，直到新的右子树为 null
    public void flatten(TreeNode root) {
        if (root == null) return;
        while (root != null) {
            //左子树为 null，直接考虑下一个节点
            if (root.left == null) {
                root = root.right;
            } else {
                // 找左子树最右边的节点
                TreeNode pre = root.left;
                while (pre.right != null) {
                    pre = pre.right;
                }
                //将原来的右子树接到左子树的最右边节点
                pre.right = root.right;
                // 将左子树插入到右子树的地方
                root.right = root.left;
                root.left = null;
                // 考虑下一个节点
                root = root.right;
            }
        }
    }

    // S2: reversed postOrder
    TreeNode prev = null;
    public void flatten2(TreeNode root) {
        if (root == null) return;
        flatten2(root.right);
        flatten2(root.left);
        root.right = prev;
        root.left = null;
        prev = root;
    }
}
