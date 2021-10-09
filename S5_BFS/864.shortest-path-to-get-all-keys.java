import java.util.Queue;

/*
 * @lc app=leetcode id=864 lang=java
 *
 * [864] Shortest Path to Get All Keys
 */

// @lc code=start
class Solution {
    // time = O(m * n), space = O(m * n)
    private static final int[][] DIRECTIONS = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    public int shortestPathAllKeys(String[] grid) {
        // corner case
        if (grid == null || grid.length == 0) return 0;

        int m = grid.length, n = grid[0].length();
        int count = 0; // # of keys
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i].charAt(j) == '@') {
                    queue.offer(new int[]{i, j, 0});
                } else if (grid[i].charAt(j) >= 'a' && grid[i].charAt(j) <= 'f') {
                    count++;
                }
            }
        }

        boolean[][][] visited = new boolean[m][n][1 << count];
        int x = queue.peek()[0], y = queue.peek()[1];
        visited[x][y][0] = true;

        int finalState = 0;
        for (int i = 0; i < count; i++) {
            finalState |= 1 << i;
        }

        int step = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int[] cur = queue.poll();
                int i = cur[0], j = cur[1], state = cur[2];
                for (int[] dir : DIRECTIONS) {
                    int ii = i + dir[0];
                    int jj = j + dir[1];
                    int newState = state;
                    if (ii < 0 || ii >= m || jj < 0 || jj >= n) continue;
                    if (grid[ii].charAt(jj) == '#') continue;
                    if (grid[ii].charAt(jj) >= 'A' && grid[ii].charAt(jj) <= 'F' && ((newState >> (grid[ii].charAt(jj) - 'A' )) & 1) == 0) {
                        continue;
                    }
                    if (grid[ii].charAt(jj) >= 'a' && grid[ii].charAt(jj) <= 'f') {
                        newState |= 1 << (grid[ii].charAt(jj) - 'a');
                    }
                    if (newState == finalState) return step + 1;
                    if (visited[ii][jj][newState]) continue;
                    queue.offer(new int[]{ii, jj, newState});
                    visited[ii][jj][newState] = true;
                }
            }
            step++;
        }
        return -1;
    }
}
// @lc code=end

