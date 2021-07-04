package S5_BST;

import java.util.*;

/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: InvertBinaryTree
 * Creater: Silentwings
 * Date: Feb, 2020
 * Description: 226. Invert Binary Tree
 */
public class LC226_InvertBinaryTree {
    /**
     * Invert a binary tree.
     *
     * Example:
     *
     * Input:
     *
     *      4
     *    /   \
     *   2     7
     *  / \   / \
     * 1   3 6   9
     * Output:
     *
     *      4
     *    /   \
     *   7     2
     *  / \   / \
     * 9   6 3   1
     * Trivia:
     * This problem was inspired by this original tweet by Max Howell:
     *
     * Google: 90% of our engineers use the software you wrote (Homebrew),
     * but you can’t invert a binary tree on a whiteboard so f*** off.
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
    // S1: 自下向上Recursion ==> 最universal的方案
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return root;
        TreeNode leftNode = invertTree(root.left);
        // NO root.right = invertTree(root.left); 严格按照三段论去写，将出错率降低
        TreeNode rightNode = invertTree(root.right); // no cache root.right 后面立刻还要用到！
        root.left = rightNode;
        root.right = leftNode;
        return root;
    }

    // S2: 不返值，用swap来实现invert ==> root不改变的情况下OK，但是如果在swap中出现改变root的情况，则不能这么作
    public TreeNode invertTree2(TreeNode root) {
        if (root == null) return root;
        invertTree(root.left);
        invertTree(root.right);

        TreeNode count = root.left; // not recommended
        // 有可能操作完向上返回时root已经改变(比如删值或者上下翻转)，所以不推荐使用
        root.left = root.right;
        root.right = count; // 反值上来时root还是同一个，所以这么做没有问题
        return root;
    }

    // S3: 先办事反转，后Recursion call
    public TreeNode invertTree3(TreeNode root) {
        if (root == null) return root;
        TreeNode count = root.left;   // not recommended
        root.left = root.right;
        root.right = count;
        invertTree(root.left);  // 刚好反了，但不影响结果,相当于老树的右和左
        invertTree(root.right);
        return root;    // 从上往下做，不需要返值
    }
}