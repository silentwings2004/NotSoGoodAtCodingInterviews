package S8_BFSDFS;
import java.util.*;
/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: BinaryTreeLevelOrderTraversal
 * Creater: Silentwings
 * Date: Feb, 2020
 * Description: 102. Binary Tree Level Order Traversal
 */
public class LC102_BinaryTreeLevelOrderTraversal {
    /**
     * Given a binary tree, return the level order traversal of its nodes' values.
     * (ie, from left to right, level by level).
     *
     * For example:
     * Given binary tree [3,9,20,null,null,15,7],
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     * return its level order traversal as:
     * [
     *   [3],
     *   [9,20],
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
    public List<List<Integer>> levelOrder(TreeNode root) {
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
            res.add(list);
        }
        return res;
    }

    // S2: DFS
    // time = O(n), space = O(n)
    public List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        // corner case
        if (root == null) return res;

        HashMap<Integer, List<Integer>> map = new HashMap<>();

        dfs(map, root, 0);

        for (int i = 0; i < map.size(); i++) {
            res.add(map.get(i));
        }
        return res;
    }

    private void dfs(HashMap<Integer, List<Integer>> map, TreeNode cur, int level) {
        // base case - success
        if (cur == null) return;

        if (!map.containsKey(level)) map.put(level, new ArrayList<>());
        map.get(level).add(cur.val);

        dfs(map, cur.left, level + 1);
        dfs(map, cur.right, level + 1);
    }
}
