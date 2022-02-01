import java.util.HashMap;

/*
 * @lc app=leetcode id=1649 lang=java
 *
 * [1649] Create Sorted Array through Instructions
 */

// @lc code=start
class Solution {
    // time = O(nlogm), space = O(m)   m: max value of instructions
    public int createSortedArray(int[] instructions) {
        TreeSet<Integer> set = new TreeSet<>();
        for (int num : instructions) set.add(num);
        HashMap<Integer, Integer> map = new HashMap<>();
        int i = 0;
        for (int x : set) map.put(x, i++);

        int n = map.size(); // 有多少个编号 -> 有多少个叶子节点

        // build tree
        SegTreeNode root = new SegTreeNode(0, n - 1);
        init(root, 0, n - 1);

        long res = 0;
        long M = (long)(1e9 + 7);
        for (int k : instructions) {
            int idx = map.get(k);
            long count1 = queryRange(root, 0, idx - 1);
            long count2 = queryRange(root, idx + 1, n - 1);
            res += Math.min(count1, count2);
            res %= M;
            updateSingle(root, idx, 1); // freq + 1
        }
        return (int)res;
    }

    private class SegTreeNode {
        private SegTreeNode left, right;
        int start, end;
        int info; // freq
        public SegTreeNode(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    private void init(SegTreeNode node, int a, int b) { 
        // base case -> single point
        if (a == b) {
            node.info = 0; // 如果是个单点的话，那么它的频次一开始都是0， 建树的时候还没往里面扔东西
            return;
        }

        int mid = (a + b) / 2;
        if (node.left == null) { 
            node.left = new SegTreeNode(a, mid);
            node.right = new SegTreeNode(mid + 1, b);
        }
        init(node.left, a, mid);
        init(node.right, mid + 1, b);
        // info
        node.info = 0; // 建树的过程 -> 肯定是0
    }

    private void updateSingle(SegTreeNode node, int idx, int val) { 
        if (idx < node.start || idx > node.end) return; 
        if (node.start == node.end) { 
            node.info += val;
            return;
        }
        // 拆分 -> recursion
        updateSingle(node.left, idx, val);
        updateSingle(node.right, idx, val);
        // update info
        node.info = node.left.info + node.right.info;
    }

    private int queryRange(SegTreeNode node, int a, int b) { 
        if (b < node.start || a > node.end) return 0;
        if (a <= node.start && node.end <= b) return node.info; 
        // recursion
        return queryRange(node.left, a, b) + queryRange(node.right, a, b);
    }
}
// @lc code=end

