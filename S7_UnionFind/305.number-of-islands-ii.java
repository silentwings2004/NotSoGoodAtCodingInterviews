/*
 * @lc app=leetcode id=305 lang=java
 *
 * [305] Number of Islands II
 */

// @lc code=start
class Solution {
    // time = O(m * n + l), space = O(m * n)  l: the number of operations
    int[] parent;
    private int[][] directions = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        List<Integer> res = new ArrayList<>();
        parent = new int[m * n];
        for (int i = 0; i < m * n; i++) parent[i] = -1;

        int count = 0;
        for (int[] pos : positions) {
            int x = pos[0], y = pos[1];
            int u = x * n + y;
            if (parent[u] != -1) {
                res.add(res.get(res.size() - 1));
                continue;
            }
            parent[u] = u;
            count++;

            for (int[] dir : directions) {
                int i = x + dir[0];
                int j = y + dir[1];
                int v = i * n + j;
                if (i < 0 || i >= m || j < 0 || j >= n) continue;
                if (parent[v] == -1) continue;
                if (findParent(u) != findParent(v)) {
                    union(u, v);
                    count--;
                }
            }
            res.add(count);
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

