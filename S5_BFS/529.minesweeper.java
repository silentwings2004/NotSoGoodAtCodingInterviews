import java.util.Queue;

/*
 * @lc app=leetcode id=529 lang=java
 *
 * [529] Minesweeper
 */

// @lc code=start
class Solution {
    // time = O(m * n), space = O(m * n)
    private static final int[][] DIRECTIONS = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};
    public char[][] updateBoard(char[][] board, int[] click) {
        int m = board.length, n = board[0].length;
        if (board[click[0]][click[1]] == 'M') {
            board[click[0]][click[1]] = 'X';
            return board;
        }
        
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(click[0] * n + click[1]);
        boolean[][] visited = new boolean[m][n];
        visited[click[0]][click[1]] = true;
        
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            int i = cur / n, j = cur % n;
            int count = 0;
            List<int[]> nexts = new ArrayList<>();
            
            for (int[] dir : DIRECTIONS) {
                int ii = i + dir[0];
                int jj = j + dir[1];
                if (ii < 0 || ii >= m || jj < 0 || jj >= n) continue;
                if (board[ii][jj] == 'M') count++;
                else {
                    if (!visited[ii][jj]) nexts.add(new int[]{ii, jj});
                }
            }
            
            if (count == 0) {
                board[i][j] = 'B';
                for (int[] x : nexts) {
                    queue.offer(x[0] * n + x[1]);
                    visited[x[0]][x[1]] = true;
                }
            } else board[i][j] = (char)('0' + count);
        }
        return board;
    }
}
// @lc code=end

