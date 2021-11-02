/*
 * @lc app=leetcode id=417 lang=java
 *
 * [417] Pacific Atlantic Water Flow
 */

// @lc code=start
class Solution {
    // time = O(m + n), space = O(m * n)
    private int[][] directions = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        List<List<Integer>> res = new ArrayList<>();
        int m = heights.length, n = heights[0].length;
        boolean[][] pacific = new boolean[m][n];
        boolean[][] atlantic = new boolean[m][n];

        for (int i = 0; i < m; i++) dfs(heights, i, 0, pacific);
        for (int j = 0; j < n; j++) dfs(heights, 0, j, pacific);
        for (int i = 0; i < m; i++) dfs(heights, i, n - 1, atlantic);
        for (int j = 0; j < n; j++) dfs(heights, m - 1, j, atlantic);

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (pacific[i][j] && atlantic[i][j]) {
                    res.add(Arrays.asList(i, j));
                }
            }
        }
        return res;
    }

    private void dfs(int[][] heights, int i, int j, boolean[][] sea) {
        int m = heights.length, n = heights[0].length;

        sea[i][j] = true;
        for (int[] dir : directions) {
            int x = i + dir[0];
            int y = j + dir[1];
            if (x < 0 || x >= m || y < 0 || y >= n) continue;
            if (sea[x][y]) continue;
            if (heights[x][y] < heights[i][j]) continue;
            dfs(heights, x, y, sea);
        }
    }
}
// @lc code=end

