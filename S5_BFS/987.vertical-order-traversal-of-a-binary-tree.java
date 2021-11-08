import java.util.HashMap;
import java.util.List;
import java.util.Queue;
import java.util.TreeMap;

/*
 * @lc app=leetcode id=987 lang=java
 *
 * [987] Vertical Order Traversal of a Binary Tree
 */

// @lc code=start
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    // time = O(nlogn), space = O(n)
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        // corner case
        if (root == null) return res;

        TreeMap<Integer, List<Integer>> map = new TreeMap<>();
        Queue<Pair> queue = new LinkedList<>();
        queue.offer(new Pair(root, 0));

        while (!queue.isEmpty()) {
            int size = queue.size();
            HashMap<Integer, List<Integer>> temp = new HashMap<>();
            while (size-- > 0) {
                Pair cur = queue.poll();
                TreeNode node = cur.node;
                int idx = cur.idx;
                temp.putIfAbsent(idx, new ArrayList<>());
                temp.get(idx).add(node.val);

                if (node.left != null) queue.offer(new Pair(node.left, idx - 1));
                if (node.right != null) queue.offer(new Pair(node.right, idx + 1));
            }
            for (int key : temp.keySet()) {
                List<Integer> list = temp.get(key);
                Collections.sort(list);
                map.putIfAbsent(key, new ArrayList<>());
                map.get(key).addAll(list);
            }

        }

        for (int key : map.keySet()) {
            res.add(map.get(key));
        }
        return res;
    }

    private class Pair {
        private TreeNode node;
        private int idx;
        public Pair(TreeNode node, int idx) {
            this.node = node;
            this.idx = idx;
        }
    }
}
// @lc code=end

