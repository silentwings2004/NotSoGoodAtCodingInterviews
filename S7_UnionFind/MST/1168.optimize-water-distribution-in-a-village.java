/*
 * @lc app=leetcode id=1168 lang=java
 *
 * [1168] Optimize Water Distribution in a Village
 */

// @lc code=start
class Solution {
    // time = O((m + n)log(m + n)), space = O(m + n)
    int[] parent;
    public int minCostToSupplyWater(int n, int[] wells, int[][] pipes) {
        parent = new int[n + 1];
        for (int i = 0; i <= n; i++) parent[i] = i;

        List<int[]> edges = new ArrayList<>();
        for (int i = 0; i < wells.length; i++) edges.add(new int[]{i + 1, 0, wells[i]}); // add a dummy 0 node to connect!
        for (int[] x : pipes) edges.add(x);

        Collections.sort(edges, (o1, o2) -> o1[2] - o2[2]); // don't forget to sort the arrays!!!

        int res = 0;
        for (int[] x : edges) {
            int a = x[0], b = x[1], cost = x[2];
            if (findParent(a) != findParent(b)) {
                union(a, b);
                res += cost;
            }
        }
        return res;
    }

    private int findParent(int x) {
        if (parent[x] != x) parent[x] = findParent(parent[x]);
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

