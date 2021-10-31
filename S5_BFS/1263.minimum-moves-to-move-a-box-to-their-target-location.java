import java.util.Deque;

/*
 * @lc app=leetcode id=1263 lang=java
 *
 * [1263] Minimum Moves to Move a Box to Their Target Location
 */

// @lc code=start
class Solution {
    private int[][] directions = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    public int minPushBox(char[][] grid) {
        int m = grid.length, n = grid[0].length;
        int bx = -1, by = -1, px = -1, py = -1, tx = -1, ty = -1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 'B') {
                    bx = i;
                    by = j;
                    grid[i][j] = '.';
                }
                if (grid[i][j] == 'S') {
                    px = i;
                    py = j;
                    grid[i][j] = '.';
                }
                if (grid[i][j] == 'T') {
                    tx = i;
                    ty = j;
                    grid[i][j] = '.';
                }
            }
        }

        int[][][][] memo = new int[m][n][m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < m; k++) {
                    for (int l = 0; l < n; l++) {
                        memo[i][j][k][l] = -1;
                    }
                }
            }
        }
        memo[bx][by][px][py] = 0;

        Deque<int[]> deque = new LinkedList<>();
        deque.offerFirst(new int[]{bx, by, px, py});
        
        while (!deque.isEmpty()) {
            int[] cur = deque.pollFirst();
            bx = cur[0];
            by = cur[1];
            px = cur[2];
            py = cur[3];
            
            // check if arrvied at target
            if (bx == tx && by == ty) return memo[bx][by][px][py];

            // case 1: people move around, but keep the box untouched
            for (int[] dir : directions) {
                int x = px + dir[0];
                int y = py + dir[1];
                if (x < 0 || x >= m || y < 0 || y >= n) continue;
                if (grid[x][y] != '.') continue;
                if (x == bx && y == by) continue;
                if (memo[bx][by][x][y] >= 0) continue;
                memo[bx][by][x][y] = memo[bx][by][px][py];
                deque.offerFirst(new int[]{bx, by, x, y});
            }

            // case 2: push the box
            if (Math.abs(bx - px) + Math.abs(by - py) == 1) { // box and people are next to each other
                for (int[] dir : directions) {
                    if (px + dir[0] == bx && py + dir[1] == by) {
                        int bx2 = bx + dir[0];
                        int by2 = by + dir[1];
                        if (bx2 < 0 || bx2 >= m || by2 < 0 || by2 >= n) continue;
                        if (grid[bx2][by2] != '.') continue;
                        if (memo[bx2][by2][bx][by] >= 0) continue;
                        memo[bx2][by2][bx][by] = memo[bx][by][px][py] + 1;
                        deque.offerLast(new int[]{bx2, by2, bx, by});
                    }
                }
            }
        }
        return -1;
    }
}
// @lc code=end

