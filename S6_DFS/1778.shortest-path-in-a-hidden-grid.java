/*
 * @lc app=leetcode id=1778 lang=java
 *
 * [1778] Shortest Path in a Hidden Grid
 */

// @lc code=start
/**
 * // This is the GridMaster's API interface.
 * // You should not implement it, or speculate about its implementation
 * class GridMaster {
 *     boolean canMove(char direction);
 *     void move(char direction);
 *     boolean isTarget();
 * }
 */

class Solution {
    // time = O(1), space = O(1)
    private int[][] directions = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    public int findShortestPath(GridMaster master) {
        int[][] grid = new int[1001][1001];
        boolean[][] visited = new boolean[1001][1001];
        visited[500][500] = true;
        dfs(master, grid, 500, 500, visited);

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{500, 500});
        for (int i = 0; i < visited.length; i++) Arrays.fill(visited[i], false);
        visited[500][500] = true;

        int step = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int[] cur = queue.poll();
                int x = cur[0], y = cur[1];
                if (grid[x][y] == 2) return step;
                for (int k = 0; k < 4; k++) {
                    int i = x + directions[k][0];
                    int j = y + directions[k][1];
                    if (visited[i][j]) continue;
                    if (grid[i][j] == 0) continue;
                    queue.offer(new int[]{i, j});
                    visited[i][j] = true;
                }
            }
            step++;
        }
        return -1;
    }

    private void dfs(GridMaster master, int[][] grid, int x, int y, boolean[][] visited) {
        char[] d = new char[]{'U', 'R', 'D', 'L'};
        grid[x][y] = 1;
        if (master.isTarget()) {
            grid[x][y] = 2;
            return;
        }

        for (int k = 0; k < 4; k++) {
            int i = x + directions[k][0];
            int j = y + directions[k][1];
            if (visited[i][j]) continue;
            if (!master.canMove(d[k])) {
                grid[i][j] = 0;
                continue;
            } else {
                visited[i][j] = true;
                master.move(d[k]);
                dfs(master, grid, i, j, visited);
                int kk = k <= 1 ? k + 2 : k - 2;
                master.move(d[kk]);
            }
        }
    }
}
// @lc code=end

