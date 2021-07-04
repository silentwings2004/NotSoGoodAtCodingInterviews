package S8_BFSDFS;
import java.util.*;

/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: CheckCompletenessofaBinaryTree
 * Creater: Silentwings
 * Date: Feb, 2020
 * Description: 103. Binary Tree Zigzag Level Order Traversal
 */
public class LC958_CheckCompletenessofaBinaryTree {
    /**
     * Given a binary tree, determine if it is a complete binary tree.
     *
     * Definition of a complete binary tree from Wikipedia:
     * In a complete binary tree every level, except possibly the last, is completely filled, and all nodes in the last
     * level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.
     *
     *
     *
     * Example 1:
     *
     *
     *
     * Input: [1,2,3,4,5,6]
     * Output: true
     * Explanation: Every level before the last is full (ie. levels with node-values {1} and {2, 3}),
     * and all nodes in the last level ({4, 5, 6}) are as far left as possible.
     * Example 2:
     *
     *
     *
     * Input: [1,2,3,4,5,null,7]
     * Output: false
     * Explanation: The node with value 7 isn't as far left as possible.
     *
     * Note:
     *
     * The tree will have between 1 and 100 nodes.
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
    // S1: manually add a mark to indicate null
    // time = O(n), space = O(n)
    public boolean isCompleteTree(TreeNode root) {
        // corner case
        if (root == null) return true;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean flag = false;

        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if (cur == null) flag = true;
            else {
                if (flag) return false;
                queue.offer(cur.left);
                queue.offer(cur.right);
            }
        }
        return true;
    }

    // S2: 基于正常的level order traversal
    public boolean isCompleteTree2(TreeNode root) {
        if (root == null) return true;
        Queue<TreeNode> queue = new LinkedList<>();
        boolean flag = false;
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if (cur.left != null) {
                if (flag) return false;
                queue.offer(cur.left);
            } else {
                flag = true;
            }
            if (cur.right != null) {
                if (flag) return false;
                queue.offer(cur.right);
            } else {
                flag = true;
            }
        }
        return true;
    }
}
