/*
 * @lc app=leetcode id=351 lang=java
 *
 * [351] Android Unlock Patterns
 */

// @lc code=start
class Solution {
    // time = O(1), space = O(1)
    private int[][] directions = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}, {-2, -1}, {-2, 1}, {2, -1}, {2, 1}, {-1, -2}, {-1, 2}, {1, -2}, {1, 2}};
    private int count = 0;
    public int numberOfPatterns(int m, int n) {
        boolean[][] visited = new boolean[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                visited[i][j] = true;
                dfs(i, j, 1, m, n, visited);
                visited[i][j] = false;
            }
        }
        return count;
    }

    private void dfs(int x, int y, int k, int m, int n, boolean[][] visited) {
        // base case
        if (k >= m && k <= n) count++;
        if (k > n) return;

        for (int[] dir : directions) {
            int i = x + dir[0];
            int j = y + dir[1];
            if (i < 0 || i >= 3 || j < 0 || j >= 3) continue;
            if (!visited[i][j]) {
                visited[i][j] = true;
                dfs(i, j, k + 1, m, n, visited);
                visited[i][j] = false;
            } else {
                i += dir[0];
                j += dir[1];
                if (i < 0 || i >= 3 || j < 0 || j >= 3) continue;
                if (visited[i][j]) continue;
                visited[i][j] = true;
                dfs(i, j, k + 1, m, n, visited);
                visited[i][j] = false;
            }
        }
    }
}
// @lc code=end

