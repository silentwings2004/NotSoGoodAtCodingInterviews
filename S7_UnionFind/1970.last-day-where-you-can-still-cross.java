/*
 * @lc app=leetcode id=1970 lang=java
 *
 * [1970] Last Day Where You Can Still Cross
 */

// @lc code=start
class Solution {
    // time = O(m * n * a(m * n)), space = O(m * n)
    int[] parent;
    private int[][] directions = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    public int latestDayToCross(int row, int col, int[][] cells) {
        int m = row, n = col;
        parent = new int[m * n + 2];
        for (int i = 0; i < m * n + 2; i++) parent[i] = i;

        // construct the matrix in last scene
        int[][] grid = new int[m][n];
        for (int[] cell : cells) {
            int a = cell[0] - 1, b = cell[1] - 1;
            grid[a][b] = 1;
        }

        for (int j = 0; j < n; j++) union(0 * n + j, m * n);
        for (int j = 0; j < n; j++) union((m - 1) * n + j, m * n + 1);

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) continue;
                for (int[] dir : directions) {
                    int x = i + dir[0];
                    int y = j + dir[1];
                    int u = i * n + j, v = x * n + y;
                    if (x < 0 || x >= m || y < 0 || y >= n) continue;
                    if (grid[x][y] == 1) continue;
                    if (findParent(u) != findParent(v)) union(u, v);
                } 
            }
        }

        for (int t = cells.length - 1; t >= 0; t--) {
            if (findParent(m * n) == findParent(m * n + 1)) return t + 1; // 1-index
            int i = cells[t][0] - 1, j = cells[t][1] - 1;
            grid[i][j] = 0;
            for (int[] dir : directions) {
                int x = i + dir[0];
                int y = j + dir[1];
                int u = i * n + j, v = x * n + y;
                if (x < 0 || x >= m || y < 0 || y >= n) continue;
                if (grid[x][y] == 1) continue;
                if (findParent(u) != findParent(v)) union(u, v);
            } 
        }
        return 0;
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

