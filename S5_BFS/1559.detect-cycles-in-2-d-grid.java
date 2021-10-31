import java.util.Queue;

/*
 * @lc app=leetcode id=1559 lang=java
 *
 * [1559] Detect Cycles in 2D Grid
 */

// @lc code=start
class Solution {
    // S1: BFS
    // time = O(m * n * (m + n)), space = O(m * n)
    private int[][] directions = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    public boolean containsCycle(char[][] grid) {
        int m = grid.length, n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (visited[i][j]) continue;
                
                Queue<int[]> queue = new LinkedList<>();
                queue.offer(new int[]{i, j, -1});
                visited[i][j] = true;

                while (!queue.isEmpty()) {
                    int[] cur = queue.poll();
                    int x = cur[0], y = cur[1], d = cur[2];

                    for (int k = 0; k < 4; k++) {
                        if (d != -1 && Math.abs(d - k) == 2) continue;
                        int a = x + directions[k][0];
                        int b = y + directions[k][1];
                        if (a < 0 || a >= m || b < 0 || b >= n) continue;
                        if (grid[a][b] != grid[x][y]) continue;
                        if (visited[a][b]) return true;
                        queue.offer(new int[]{a, b, k});
                        visited[a][b] = true;
                    }
                }
            }
        }
        return false;
    }

    // S2: Union Find
    // time = O(m * n * log(m * n)), space = O(m * n)
    private int[] parent;
    public boolean containsCycle2(char[][] grid) {
        int m = grid.length, n = grid[0].length;
        parent = new int[m * n];
        for (int i = 0; i < m * n; i++) parent[i] = i;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // compare upper side
                if (i > 0 && grid[i][j] == grid[i - 1][j]) {
                    int v1 = i * n + j, v2 = (i - 1) * n + j;
                    if (findParent(v1) == findParent(v2)) return true;
                    union(v1, v2);
                }
                // compare left side
                if (j > 0 && grid[i][j] == grid[i][j - 1]) {
                    int v1 = i * n + j, v2 = i * n + j - 1;
                    if (findParent(v1) == findParent(v2)) return true;
                    union(v1, v2);
                }
            }
        }
        return false;
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

