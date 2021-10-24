import java.util.Queue;

/*
 * @lc app=leetcode id=1293 lang=java
 *
 * [1293] Shortest Path in a Grid with Obstacles Elimination
 */

// @lc code=start
class Solution {
    // time = O(m * n * min(k, m + n), space = O(m * n * min(k, m + n)
    private int[][] directions = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    public int shortestPath(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        if (k >= m + n - 3) return m + n - 2;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0, 0}); // {x, y, r}
        boolean[][][] visited = new boolean[m][n][k + 1];

        int step = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int[] cur = queue.poll();
                int x = cur[0], y = cur[1], r = cur[2];
                if (x == m - 1 && y == n - 1) return step;

                for (int[] dir : directions) {
                    int i = x + dir[0];
                    int j = y + dir[1];
                    if (i < 0 || i >= m || j < 0 || j >= n) continue;
                    if (grid[i][j] == 1) {
                        if (r == k) continue;
                        if (visited[i][j][r + 1]) continue;
                        queue.offer(new int[]{i, j, r + 1});
                        visited[i][j][r + 1] = true;
                    } else {
                        if (visited[i][j][r]) continue;
                        queue.offer(new int[]{i, j, r});
                        visited[i][j][r] = true;
                    }
                }
            }
            step++;
        }
        return -1;
    }
}
// @lc code=end

