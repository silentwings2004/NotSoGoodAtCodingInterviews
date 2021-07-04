package S5_BST;

import java.util.*;

/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: BinaryTreeInorderTraversal
 * Creater: Silentwings
 * Date: Feb, 2020
 * Description: 94. Binary Tree Inorder Traversal
 */

public class LC94_BinaryTreeInorderTraversal {
    /**
     * Given a binary tree, return the inorder traversal of its nodes' values.
     *
     * Example:
     *
     * Input: [1,null,2,3]
     *    1
     *     \
     *      2
     *     /
     *    3
     *
     * Output: [1,3,2]
     * Follow up: Recursive solution is trivial, could you do it iteratively?
     *
     * time : O(n)
     * space : O(n)
     *
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
    // S1: Recursion --> Divide & Conquer
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        List<Integer> left = inorderTraversal(root.left);
        List<Integer> right = inorderTraversal(root.right);

        result.addAll(left);
        result.add(root.val);
        result.addAll(right);
        return result;
    }

    // S2: Iterative --> Stack
    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        Stack<TreeNode> stack = new Stack<>();

        while (root != null || !stack.isEmpty()) {
            if (root == null) {
                root = stack.pop();
                result.add(root.val);
                root = root.right;
            } else {
                stack.push(root);
                root = root.left;
            }
        }
        return result;
    }

    // S3: Traverse
    public List<Integer> inorderTraversal3(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        traverse(root, result);
        return result;
    }

    private void traverse(TreeNode root, List<Integer> result) {
        if (root == null) return;
        traverse(root.left, result);
        result.add(root.val);
        traverse(root.right, result);
    }
}
