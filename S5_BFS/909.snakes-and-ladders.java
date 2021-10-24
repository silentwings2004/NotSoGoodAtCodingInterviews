import java.util.Queue;

/*
 * @lc app=leetcode id=909 lang=java
 *
 * [909] Snakes and Ladders
 */

// @lc code=start
class Solution {
    // time = O(n^2), space = O(n^2)
    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        Queue<Integer> queue= new LinkedList<>();
        queue.offer(1);
        boolean[] visited = new boolean[n * n + 1]; // 1 ~ 36
        visited[1] = true;

        int step = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int cur = queue.poll();
                if (cur == n * n) return step;
                for (int k = 1; k <= Math.min(6, n * n - cur); k++) {
                    int val = cur + k;
                    int[] next = convert(board, val);
                    int x = next[0], y = next[1];
                    if (board[x][y] != -1) val = board[x][y];
                    if (!visited[val]) {
                        queue.offer(val);
                        visited[val] = true;
                    }
                }
            }
            step++;
        }
        return -1;
    }

    private int[] convert(int[][] board, int val) {
        int n = board.length;
        int i = (val - 1) / n, j = (val - 1) % n;
        int row = n - 1 - i, col = i % 2 == 0 ? j : n - 1 - j;
        return new int[]{row, col};
    }
}
// @lc code=end

