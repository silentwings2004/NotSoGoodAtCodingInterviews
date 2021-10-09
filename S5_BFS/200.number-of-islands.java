import java.util.Queue;

/*
 * @lc app=leetcode id=200 lang=java
 *
 * [200] Number of Islands
 */

// @lc code=start
class Solution {
    // time = O(m * n), space = O(m * n)
    private static final int[][] DIRECTIONS = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    public int numIslands(char[][] grid) {
        int m = grid.length, n = grid[0].length;
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] != '1') continue;
                Queue<Integer> queue = new LinkedList<>();
                queue.offer(i * n + j);
                grid[i][j] = 0;

                while (!queue.isEmpty()) {
                    int cur = queue.poll();
                    int x = cur / n, y = cur % n;
                    for (int[] dir : DIRECTIONS) {
                        int ii = x + dir[0];
                        int jj = y + dir[1];
                        if (ii < 0 || ii >= m || jj < 0 || jj >= n) continue;
                        if (grid[ii][jj] != '1') continue;
                        queue.offer(ii * n + jj);
                        grid[ii][jj] = 0;
                    }
                }
                count++;
            }
        }
        return count;
    }
}
// @lc code=end

