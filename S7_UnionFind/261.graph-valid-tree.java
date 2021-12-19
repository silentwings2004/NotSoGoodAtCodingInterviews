/*
 * @lc app=leetcode id=261 lang=java
 *
 * [261] Graph Valid Tree
 */

// @lc code=start
class Solution {
    // time = O(nlogn), space = O(n)  n: number of nodes
    int[] parent;
    public boolean validTree(int n, int[][] edges) {
        if (n - 1 != edges.length) return false;

        parent = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;

        for (int[] edge : edges) {
            int a = edge[0], b = edge[1];
            if (findParent(a) != findParent(b)) union(a, b);
            else return false;
        }
        return true;
    }

    private int findParent(int x) {
        if (x != parent[x]) parent[x] = findParent(parent[x]);
        return parent[x];
    }

    private void union(int x, int y) {
        x = parent[x];
        y = parent[y];
        if (x < y) parent[y] = x;
        else parent[x] = y;
    }
}
// @lc code=end

