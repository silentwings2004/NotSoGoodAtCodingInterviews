import java.util.HashSet;

/*
 * @lc app=leetcode id=694 lang=java
 *
 * [694] Number of Distinct Islands
 */

// @lc code=start
class Solution {
    // time = O(m * n), space = O(m * n)
    private int[][] directions = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    public int numDistinctIslands(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        HashSet<String> set = new HashSet<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    StringBuilder path = new StringBuilder();
                    dfs(grid, i, j, -1, path); // start has no direction, set as -1
                    set.add(path.toString());
                }
            }
        }
        return set.size();
    }

    private void dfs(int[][] grid, int i, int j, int dir, StringBuilder path) {
        int m = grid.length, n = grid[0].length;
        // base case - fail
        if (i < 0 || i >= m || j < 0 || j >= n || grid[i][j] == 0) return;

        path.append(dir);
        grid[i][j] = 0;
        for (int k = 0; k < 4; k++) {
            dfs(grid, i + directions[k][0], j + directions[k][1], k, path);
        }
        path.append(4); // setback -> turn back
    }
}
// @lc code=end

