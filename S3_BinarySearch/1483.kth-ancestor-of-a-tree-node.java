/*
 * @lc app=leetcode id=1483 lang=java
 *
 * [1483] Kth Ancestor of a Tree Node
 */

// @lc code=start
class TreeAncestor {
    private int[][] p;
    public TreeAncestor(int n, int[] parent) {
        p = new int[n][32];
        for (int i = 0; i < n; i++) {
            Arrays.fill(p[i], -1);
            p[i][0] = parent[i];
        }

        for (int j = 1; j < 32; j++) {
            for (int i = 0; i < n; i++) {
                if (p[i][j - 1] != -1) {
                    p[i][j] = p[p[i][j - 1]][j - 1];
                }
            }
        }
    }
    
    public int getKthAncestor(int node, int k) {
        for (int j = 0; j < 32; j++) {
            if (((k >> j) & 1) == 1) {
                node = p[node][j];
                if (node == -1) break;
            }
        }
        return node;
    }
}

/**
 * Your TreeAncestor object will be instantiated and called as such:
 * TreeAncestor obj = new TreeAncestor(n, parent);
 * int param_1 = obj.getKthAncestor(node,k);
 */
// @lc code=end

