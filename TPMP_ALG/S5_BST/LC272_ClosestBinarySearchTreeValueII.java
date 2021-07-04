package S5_BST;

import java.util.*;

/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: ClosestBinarySearchTreeValueII
 * Creater: Silentwings
 * Date: Feb, 2020
 * Description: 272. Closest Binary Search Tree Value II
 */

public class LC272_ClosestBinarySearchTreeValueII {
    /**
     * Given a non-empty binary search tree and a target value, find k values in the BST that are closest to the target.
     *
     * Note:
     *
     * Given target value is a floating point.
     * You may assume k is always valid, that is: k ≤ total nodes.
     * You are guaranteed to have only one unique set of k values in the BST that are closest to the target.
     * Example:
     *
     * Input: root = [4,2,5,1,3], target = 3.714286, and k = 2
     *
     *     4
     *    / \
     *   2   5
     *  / \
     * 1   3
     *
     * Output: [4,3]
     * Follow up:
     * Assume that the BST is balanced, could you solve it in less than O(n) runtime (where n = total nodes)?
     *
     * @param root
     * @param target
     * @param k
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
    // S1: Divide & Conquer --> time: O(n)  space = O(n)
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        LinkedList<Integer> res = new LinkedList<>();
        helper(res, root, target, k);
        return res;
    }

    private void helper(LinkedList<Integer> res, TreeNode root, double target, int k) {
        if (root == null) return;
        helper(res, root.left, target, k);
        if (res.size() == k) {
            if (Math.abs(target - root.val) < Math.abs(target - res.peekFirst())) {
                res.removeFirst();
            } else return;
        }
        res.add(root.val);
        helper(res, root.right, target, k);
    }

    // S2: Two stacks --> time: O(klogn)
    // stack: pred    stack: succ
    public List<Integer> closestKValues2(TreeNode root, double target, int k) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> pred = new Stack<>();
        Stack<TreeNode> succ = new Stack<>();
        TreeNode cur = root;

        while (cur != null) {
            if (cur.val < target) {
                pred.push(cur);
                cur = cur.right;
            } else {
                succ.push(cur);
                cur = cur.left;
            }
        }

        while (k-- > 0) {
            if (!pred.isEmpty() && !succ.isEmpty()) {
                if (Math.abs((double)pred.peek().val - target) < Math.abs((double)succ.peek().val - target)) {
                    res.add(getNextPred(pred));
                } else {
                    res.add(getNextSucc(succ));
                }
            } else if (!pred.isEmpty()) {
                res.add(getNextPred(pred));
            } else if (!succ.isEmpty()) {
                res.add(getNextSucc(succ));
            }
        }
        return res;
    }

    private int getNextPred(Stack<TreeNode> pred) {
        TreeNode top = pred.pop();
        int ret = top.val;
        TreeNode cur = top.left;

        while (cur != null) {
            pred.push(cur);
            cur = cur.right;
        }
        return ret;
    }

    private int getNextSucc(Stack<TreeNode> succ) {
        TreeNode top = succ.pop();
        int ret = top.val;
        TreeNode cur = top.right;

        while (cur != null) {
            succ.push(cur);
            cur = cur.left;
        }
        return ret;
    }
}