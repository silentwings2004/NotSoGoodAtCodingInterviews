/*
 * @lc app=leetcode id=1597 lang=java
 *
 * [1597] Build Binary Expression Tree From Infix Expression
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
    public Node expTree(String s) {
        int n = s.length();
        // base case
        if (n == 1) {
            return new Node(s.charAt(0));
        }

        for (int i = n - 1; i >= 0; i--) {
            if (s.charAt(i) == '+' || s.charAt(i) == '-') {
                Node root = new Node(s.charAt(i));
                root.left = expTree(s.substring(0, i));
                root.right = expTree(s.substring(i + 1));
                return root;
            } else if (s.charAt(i) == ')') {
                int j = i - 1, count = 1;
                while (j >= 0 && count > 0) {
                    if (s.charAt(j) == ')') count++;
                    else if (s.charAt(j) == '(') count--;
                    j--;
                }
                i = j + 1;
            }
        }


        for (int i = n - 1; i >= 0; i--) {
            if (s.charAt(i) == '*' || s.charAt(i) == '/') {
                Node root = new Node(s.charAt(i));
                root.left = expTree(s.substring(0, i));
                root.right = expTree(s.substring(i + 1));
                return root;
            } else if (s.charAt(i) == ')') {
                int j = i - 1, count = 1;
                while (j >= 0 && count > 0) {
                    if (s.charAt(j) == ')') count++;
                    else if (s.charAt(j) == '(') count--;
                    j--;
                }
                i = j + 1;
            }
        }

        if (s.charAt(0) == '(' && s.charAt(n - 1) == ')') {
            return expTree(s.substring(1, n - 1));
        }
        return null;
    }
}
// @lc code=end

