package S8_BFSDFS;
import java.util.*;
/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: BinaryTreeZigzagLevelOrderTraversal
 * Creater: Silentwings
 * Date: Feb, 2020
 * Description: 103. Binary Tree Zigzag Level Order Traversal
 */
public class LC103_BinaryTreeZigzagLevelOrderTraversal {
    /**
     * Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right,
     * then right to left for the next level and alternate between).
     *
     * For example:
     * Given binary tree [3,9,20,null,null,15,7],
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     * return its zigzag level order traversal as:
     * [
     *   [3],
     *   [20,9],
     *   [15,7]
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
    // S1: BFS
    // time = O(n), space = O(n)
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        // corner case
        if (root == null) return res;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int level = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            while (size-- > 0) {
                TreeNode cur = queue.poll();
                list.add(cur.val);
                if (cur.left != null) queue.offer(cur.left);
                if (cur.right != null) queue.offer(cur.right);
            }
            if (level % 2 != 0) Collections.reverse(list);
            res.add(list);
            level++;
        }
        return res;
    }

    // S2: Using deque(stack, queue)
    // time = O(n), space = O(n)
    public List<List<Integer>> zigzagLevelOrder2(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        // corner case
        if (root == null) return res;

        Deque<TreeNode> deque = new LinkedList<>();
        deque.offerLast(root);
        boolean flag = false;

        while (!deque.isEmpty()) {
            int size = deque.size();
            List<Integer> list = new ArrayList<>();
            while (size-- > 0) {
                if (!flag) {
                    TreeNode cur = deque.pollFirst();
                    list.add(cur.val);
                    if (cur.left != null) deque.offerLast(cur.left);
                    if (cur.right != null) deque.offerLast(cur.right);
                } else {
                    TreeNode cur = deque.pollLast();
                    list.add(cur.val);
                    if (cur.right != null) deque.offerFirst(cur.right);
                    if (cur.left != null) deque.offerFirst(cur.left);
                }
            }
            flag = !flag;
            res.add(list);
        }
        return res;
    }
}