/*
 * @lc app=leetcode id=1932 lang=java
 *
 * [1932] Merge BSTs to Create Single BST
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
    // time = O(n), space = O(n)
    HashSet<Integer> set;
    HashMap<Integer, TreeNode> map;
    HashSet<Integer> visited;
    public TreeNode canMerge(List<TreeNode> trees) {
        set = new HashSet<>();
        map = new HashMap<>();
        visited = new HashSet<>();
        for (TreeNode root : trees) {
            findLeaf(root.left);
            findLeaf(root.right);
            map.put(root.val, root);
        }
        
        TreeNode root = null;
        int count = 0;
        for (TreeNode x : trees) {
            if (!set.contains(x.val)) {
                root = x;
                count++;
            }
        }
        if (count != 1) return null;
        visited.add(root.val);
        boolean ok = build(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
        if (ok && visited.size() == trees.size()) return root;
        return null;
    }

    private boolean build(TreeNode node, int min, int max) {
        if (node == null) return true;
        int val = node.val;
        if (val < min || val > max) return false;

        if (node.left != null || node.right != null) {
            return build(node.left, min, val - 1) && build(node.right, val + 1, max);
        } else {
            if (map.containsKey(val)) {
                TreeNode subRoot = map.get(val);
                node.left = subRoot.left;
                node.right = subRoot.right;
                visited.add(node.val);
                return build(node.left, min, val - 1) && build(node.right, val + 1, max);
            } else return true;
        }
    }

    private void findLeaf(TreeNode node) {
        if (node == null) return;

        // 如果题目改成不止2层，下面继续加的话，可以不断递归下去，能拼就拼，否则可以check是否为叶子节点再放入set!
        set.add(node.val); 
        findLeaf(node.left);
        findLeaf(node.right);
    }
}
// @lc code=end

