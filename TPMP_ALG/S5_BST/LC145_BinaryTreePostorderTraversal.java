package S5_BST;

import java.util.*;

/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: BinaryTreePostorderTraversal
 * Creater: Silentwings
 * Date: Feb, 2020
 * Description: 145. Binary Tree Postorder Traversal
 */

public class LC145_BinaryTreePostorderTraversal {
    /**
     * Given a binary tree, return the postorder traversal of its nodes' values.
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
     * Output: [3,2,1]
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
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        List<Integer> left = postorderTraversal(root.left);
        List<Integer> right = postorderTraversal(root.right);

        result.addAll(left);
        result.addAll(right);
        result.add(root.val);
        return result;
    }

    // S2: Iterative --> Stack
    public List<Integer> postorderTraversal2(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {
            if (root == null) {
                root = stack.pop();
                root = root.left;
            } else {
                result.add(root.val);
                stack.push(root);
                root = root.right;
            }
        }
        Collections.reverse(result);
        return result;
    }

    // S3: Traverse
    public List<Integer> postorderTraversal3(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        traverse(root, result);
        return result;
    }

    private void traverse(TreeNode root, List<Integer> result) {
        if (root == null) return;
        traverse(root.left, result);
        traverse(root.right, result);
        result.add(root.val);
    }
}