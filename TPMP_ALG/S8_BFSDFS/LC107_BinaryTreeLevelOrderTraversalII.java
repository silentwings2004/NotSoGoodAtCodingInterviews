package S8_BFSDFS;
import java.util.*;

/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: BinaryTreeLevelOrderTraversalII
 * Creater: Silentwings
 * Date: Feb, 2020
 * Description: 107. Binary Tree Level Order Traversal II
 */
public class LC107_BinaryTreeLevelOrderTraversalII {
    /**
     * Given a binary tree, return the bottom-up level order traversal of its nodes' values. (ie, from left to right,
     * level by level from leaf to root).
     *
     * For example:
     * Given binary tree [3,9,20,null,null,15,7],
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     * return its bottom-up level order traversal as:
     * [
     *   [15,7],
     *   [9,20],
     *   [3]
     * ]
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
    // 先按照之前level order traversal的过程来做，然后把它的外层list上下颠倒(注意不是内层，内层是左右),
    // 全部做完后call一个上下颠倒的reverse即可。
    // time = O(n), space = O(n)
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        // corner case
        if (root == null) return res;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            while (size-- > 0) {
                TreeNode cur = queue.poll();
                list.add(cur.val);
                if (cur.left != null) queue.offer(cur.left);
                if (cur.right != null) queue.offer(cur.right);
            }
            res.add(0, list);
        }
        // Collections.reverse(result);
        return res;
    }
}
