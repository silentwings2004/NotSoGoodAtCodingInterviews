import java.util.Queue;

/*
 * @lc app=leetcode id=130 lang=java
 *
 * [130] Surrounded Regions
 */

// @lc code=start
class Solution {
    // time = O(m * n), space = O(m * n)
    public void solve(char[][] board) {
        int m = board.length, n = board[0].length;
        for (int i = 0; i < m; i++) {
            if (board[i][0] == 'O') bfs(board, i, 0);
            if (board[i][n - 1] == 'O') bfs(board, i, n - 1);
        }

        for (int j = 0; j < n; j++) {
            if (board[0][j] == 'O') bfs(board, 0, j);
            if (board[m - 1][j] == 'O') bfs(board, m - 1, j);
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O') board[i][j] = 'X';
                else if (board[i][j] == '#') board[i][j] = 'O';
            }
        }
    }

    private void bfs(char[][] board, int i, int j) {
        int m = board.length, n = board[0].length;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(i * n + j);
        board[i][j] = '#';
        int[][] dir = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            int x = cur / n, y = cur % n;
            for (int k = 0; k < 4; k++) {
                int ii = x + dir[k][0];
                int jj = y + dir[k][1];
                if (ii < 0 || ii >= m || jj < 0 || jj >= n) continue;
                if (board[ii][jj] != 'O') continue;
                board[ii][jj] = '#';
                queue.offer(ii * n + jj);
            }
        }
    }
}
// @lc code=end

