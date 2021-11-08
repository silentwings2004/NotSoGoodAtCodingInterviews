import java.util.Queue;

import javax.swing.tree.TreeNode;

/*
 * @lc app=leetcode id=863 lang=java
 *
 * [863] All Nodes Distance K in Binary Tree
 */

// @lc code=start
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    // S1: bfs
    // time = O(n), space = O(n)
    HashMap<TreeNode, TreeNode> map;
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        List<Integer> res = new ArrayList<>();

        // build map
        map = new HashMap<>();
        buildMap(root, null);

        // bfs
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(target);
        HashSet<TreeNode> visited = new HashSet<>();
        visited.add(target);

        int step = 0;
        while (!queue.isEmpty()) {
            if (step == k) break;
            int size = queue.size();
            while (size-- > 0) {
                TreeNode cur = queue.poll();
                if (map.get(cur) != null && visited.add(map.get(cur))) queue.offer(map.get(cur));
                if (cur.left != null && visited.add(cur.left)) queue.offer(cur.left);
                if (cur.right != null && visited.add(cur.right)) queue.offer(cur.right);
            }
            step++;
        }
        while (!queue.isEmpty()) res.add(queue.poll().val);
        return res;
    }

    private void buildMap(TreeNode cur, TreeNode parent) {
        if (cur == null) return;
        map.put(cur, parent);
        buildMap(cur.left, cur);
        buildMap(cur.right, cur);
    }

    // S2: dfs
    // time = O(n), space = O(n)
    public List<Integer> distanceK2(TreeNode root, TreeNode target, int k) {
        List<Integer> res = new ArrayList<>();
        dfs(root, target, k, res);
        return res;
    }

    private int dfs(TreeNode node, TreeNode target, int k, List<Integer> res) {
        // base case
        if (node == null) return -1;
        if (node == target) {
            fetch(node, k, res);
            return 0;
        }

        int depth1 = dfs(node.left, target, k, res);
        if (depth1 != -1) { // exist in left branch
            if (depth1 == k - 1) res.add(node.val); // depth1 = 0, k = 1 单独处理
            else fetch(node.right, k - depth1 - 2, res); 
            return depth1 + 1;
        }

        int depth2 = dfs(node.right, target, k, res);
        if (depth2 != -1) { // exist in right branch
            if (depth2 == k - 1) res.add(node.val);
            else fetch(node.left, k - depth2 - 2, res);
            return depth2 + 1;
        }
        return -1;
    }

    private void fetch(TreeNode node, int k, List<Integer> res) {
        if (node == null) return;
        if (k == 0) {
            res.add(node.val);
            return;
        }
        fetch(node.left, k - 1, res);
        fetch(node.right, k - 1, res);
    }
}
// @lc code=end

