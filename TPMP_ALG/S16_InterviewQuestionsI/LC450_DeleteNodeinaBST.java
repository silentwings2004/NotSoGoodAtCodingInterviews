package S16_InterviewQuestionsI;
import java.util.*;

/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: DeleteNodeinaBST
 * Creater: Silentwings
 * Date: Mar, 2020
 * Description: 450. Delete Node in a BST
 */
public class LC450_DeleteNodeinaBST {
    /**
     * Given a root node reference of a BST and a key, delete the node with the given key in the BST. Return the root
     * node reference (possibly updated) of the BST.
     *
     * Basically, the deletion can be divided into two stages:
     *
     * Search for a node to remove.
     * If the node is found, delete the node.
     * Note: Time complexity should be O(height of tree).
     *
     * Example:
     *
     * root = [5,3,6,2,4,null,7]
     * key = 3
     *
     *     5
     *    / \
     *   3   6
     *  / \   \
     * 2   4   7
     *
     * Given key to delete is 3. So we find the node with value 3 and delete it.
     *
     * One valid answer is [5,4,6,2,null,null,7], shown in the following BST.
     *
     *     5
     *    / \
     *   4   6
     *  /     \
     * 2       7
     *
     * Another valid answer is [5,2,6,null,4,null,7].
     *
     *     5
     *    / \
     *   2   6
     *    \   \
     *     4   7
     *
     * @param root
     * @param key
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
    public TreeNode deleteNode(TreeNode root, int key) {
        // corner case
        if (root == null) return root;
        if (root.val == key) {
            if (root.left != null && root.right != null) {
                root.val = findMin(root.right).val; //
                root.right = deleteNode(root.right, root.val);
            } else root = root.left != null ? root.left : root.right;
        } else if (root.val > key) root.left = deleteNode(root.left, key);
        else root.right = deleteNode(root.right, key);
        return root;
    }

    private TreeNode findMin(TreeNode root) {
//        if (root == null) return root; // corner case在这个helper函数里不用写，因为主函数里是因为root.right != null才call的
        while (root.left != null) root = root.left;
        return root;
    }
}
