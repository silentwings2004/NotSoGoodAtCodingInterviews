package S8_BFSDFS;
import java.util.*;

/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: BinaryTreeVerticalOrderTraversal
 * Creater: Silentwings
 * Date: Feb, 2020
 * Description: 314. Binary Tree Vertical Order Traversal
 */
public class LC314_BinaryTreeVerticalOrderTraversal {
    /**
     * Given a binary tree, return the vertical order traversal of its nodes' values.
     * (ie, from top to bottom, column by column).
     *
     * If two nodes are in the same row and column, the order should be from left to right.
     *
     * Examples 1:
     *
     * Input: [3,9,20,null,null,15,7]
     *
     *    3
     *   /\
     *  /  \
     *  9  20
     *     /\
     *    /  \
     *   15   7
     *
     * Output:
     *
     * [
     *   [9],
     *   [3,15],
     *   [20],
     *   [7]
     * ]
     * Examples 2:
     *
     * Input: [3,9,8,4,0,1,7]
     *
     *      3
     *     /\
     *    /  \
     *    9   8
     *   /\  /\
     *  /  \/  \
     *  4  01   7
     *
     * Output:
     *
     * [
     *   [4],
     *   [9],
     *   [3,0,1],
     *   [8],
     *   [7]
     * ]
     * Examples 3:
     *
     * Input: [3,9,8,4,0,1,7,null,null,null,2,5] (0's right child is 2 and 1's left child is 5)
     *
     *      3
     *     /\
     *    /  \
     *    9   8
     *   /\  /\
     *  /  \/  \
     *  4  01   7
     *     /\
     *    /  \
     *    5   2
     *
     * Output:
     *
     * [
     *   [4],
     *   [9,5],
     *   [3,0,1],
     *   [8,2],
     *   [7]
     * ]
     *
     * 1. dfs -- vairiable: col 0 => root
     * bfs --
     *
     * @param root
     * @return
     */
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode(int x) { val = x; }
     * }
     */
    // S1
    // define two global variables used as left col and right col
    int min = 0, max = 0;

    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        // corner case
        if (root == null) return res;
        dfs(root, 0);
        // 初始化最左边的col一直到最右边的col
        for (int i = min; i <= max; i++) {
            res.add(new ArrayList<>());
        }

        Queue<TreeNode> queue = new LinkedList<>();
        Queue<Integer> index = new LinkedList<>();
        queue.offer(root);
        index.offer(-min);

        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            int idx = index.poll();
            // 按照col把node加到res里去
            res.get(idx).add(cur.val);
            // 左边
            if (cur.left != null) {
                queue.offer(cur.left);
                index.offer(idx - 1);
            }
            // 右边
            if (cur.right != null) {
                queue.offer(cur.right);
                index.offer(idx + 1);
            }
        }
        return res;
    }

    private void dfs(TreeNode root, int index) {
        // 递归出口
        if (root == null) return;
        min = Math.min(min, index);
        max = Math.max(max, index);
        dfs(root.left, index - 1);
        dfs(root.right, index + 1);
    }

    // S1.2: Use HashMap to map the col with list
    public List<List<Integer>> verticalOrder2(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        // corner case
        if (root == null) return res;

        // use two queues to map the node with col
        Queue<TreeNode> queue = new LinkedList<>();
        Queue<Integer> index = new LinkedList<>();

        // use a map to map the col with list
        Map<Integer, List<Integer>> map = new HashMap<>();

        // use two index to track the range of cols
        int min = 0, max = 0;

        queue.offer(root);
        index.offer(0);

        // BFS standard countlate
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            int idx = index.poll();

            if (!map.containsKey(idx)) {
                map.put(idx, new ArrayList<>());
            }
            map.get(idx).add(cur.val);

            if (cur.left != null) {
                queue.offer(cur.left);
                index.offer(idx - 1);
                min = Math.min(min, idx - 1);
            }

            if (cur.right != null) {
                queue.offer(cur.right);
                index.offer(idx + 1);
                max = Math.max(max, idx + 1);
            }
        }

        for (int i = min; i <= max; i++) {
            res.add(map.get(i));
        }
        return res;
    }
}
