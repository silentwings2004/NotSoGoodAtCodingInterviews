package S5_BST;

import java.util.*;

/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: BinaryTreePreorderTraversal
 * Creater: Silentwings
 * Date: Feb, 2020
 * Description: 144. Binary Tree Preorder Traversal
 */

public class LC144_BinaryTreePreorderTraversal {
    /**
     * Given a binary tree, return the preorder traversal of its nodes' values.
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
     * Output: [1,2,3]
     * Follow up: Recursive solution is trivial, could you do it iteratively?
     *
     * time = O(n)
     * space = O(n)
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
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();

        // base case
        if (root == null) return result;

        List<Integer> left = preorderTraversal(root.left);
        List<Integer> right = preorderTraversal(root.right);

        result.addAll(left);
        result.add(root.val);
        result.addAll(right);

        return result;
    }

    // S2: Iterative --> Stack
    public List<Integer> preorderTraversal2(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {
            if (root == null) {
                root = stack.pop();
                root = root.right;
            } else {
                stack.push(root);
                result.add(root.val);
                root = root.left;
            }
        }
        return result;
    }

    // S3: Traverse
    public List<Integer> preorderTraversal3(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        traverse(root, result);
        return result;
    }

    private void traverse(TreeNode root, List<Integer> result) {
        if (root == null) return;
        result.add(root.val);
        traverse(root.left, result);
        traverse(root.right, result);
    }
}

// time = O(n)
// space = O(n)

