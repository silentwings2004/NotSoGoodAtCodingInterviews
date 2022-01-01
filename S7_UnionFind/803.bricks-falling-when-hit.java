/*
 * @lc app=leetcode id=803 lang=java
 *
 * [803] Bricks Falling When Hit
 */

// @lc code=start
class Solution {
    // time = O((k + m * n) * log(m * n)), space = O(m * n)
    int[] parent, size;
    int m, n;
    private int[][] directions = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    public int[] hitBricks(int[][] grid, int[][] hits) {
        this.m = grid.length;
        this.n = grid[0].length;
        parent = new int[m * n];
        size = new int[m * n];
        for (int i = 0; i < m * n; i++) {
            parent[i] = i;
            size[i] = 1;
        }

        // recover the last scene
        for (int[] hit : hits) {
            int a = hit[0], b = hit[1];
            grid[a][b] *= -1; // label the hit unit
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] != 1) continue;
                for (int[] dir : directions) {
                    int x = i + dir[0];
                    int y = j + dir[1];
                    int u = i * n + j, v = x * n + y;
                    if (x < 0 || x >= m || y < 0 || y >= n) continue;
                    if (grid[x][y] != 1) continue; // labeled / original empty
                    if (findParent(u) != findParent(v)) union(u, v);
                }
            }
        }

        // time backtrack
        int[] res = new int[hits.length];
        for (int t = hits.length - 1; t >= 0; t--) {
            int i = hits[t][0], j = hits[t][1];
            if (grid[i][j] == 0) continue;
            grid[i][j] = 1;
            boolean flag = i == 0; // check if the hit piece is attached to the ceiling
            int count = 0;
            for (int[] dir : directions) {
                int x = i + dir[0];
                int y = j + dir[1];
                int u = i * n + j, v = x * n + y;
                if (x < 0 || x >= m || y < 0 || y >= n) continue;
                if (grid[x][y] != 1) continue;
                if (findParent(u) != findParent(v)) {
                    if (findParent(v) < n) flag = true; // already attached to the ceiling
                    else count += size[findParent(v)]; // unioned group, but may not be saved yet
                    union(u, v);
                }
            }
            if (flag) res[t] = count; // only flag = true, then the previously unioned group can be all rescued.
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
        if (x < y) {
            parent[y] = x;
            size[x] += size[y];
        }
        else {
            parent[x] = y;
            size[y] += size[x];
        }
    }
}
// @lc code=end

