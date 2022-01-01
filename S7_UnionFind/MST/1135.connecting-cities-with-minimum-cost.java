/*
 * @lc app=leetcode id=1135 lang=java
 *
 * [1135] Connecting Cities With Minimum Cost
 */

// @lc code=start
class Solution {
    // time = O(ElogE), space = O(n)   E:  total number of edges (connections)
    int[] parent;
    public int minimumCost(int n, int[][] connections) {
        parent = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;

        Arrays.sort(connections, (o1, o2) -> o1[2] - o2[2]);

        int res = 0;
        for (int[] x : connections) {
            int a = x[0] - 1, b = x[1] - 1, cost = x[2];
            if (findParent(a) != findParent(b)) {
                union(a, b);
                res += cost;
            }
        }

        for (int i = 0; i < n; i++) {
            if (findParent(i) != findParent(0)) return -1;
        }
        return res;
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

