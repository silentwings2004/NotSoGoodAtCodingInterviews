/*
 * @lc app=leetcode id=1612 lang=java
 *
 * [1612] Check If Two Expression Trees are Equivalent
 */

// @lc code=start
/**
 * Definition for a binary tree node.
 * class Node {
 *     char val;
 *     Node left;
 *     Node right;
 *     Node() {this.val = ' ';}
 *     Node(char val) { this.val = val; }
 *     Node(char val, Node left, Node right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    // time = O(n), space = O(n)
    public boolean checkEquivalence(Node root1, Node root2) {
        int[] count = new int[26];
        dfs(root1, count, 1);
        dfs(root2, count, -1);

        for (int x : count) {
            if (x != 0) return false;
        }
        return true;
    }

    private void dfs(Node node, int[] count, int add) {
        if (node == null) return;

        if (Character.isLowerCase(node.val)) {
            count[node.val - 'a'] += add;
        }

        dfs(node.left, count, add);
        dfs(node.right, count, add);
    }
}
// @lc code=end

