/*
 * @lc app=leetcode id=51 lang=java
 *
 * [51] N-Queens
 */

// @lc code=start
class Solution {
    // time = O(n!), space = O(n^2)
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) Arrays.fill(board[i], '.');
        dfs(board, 0, res);
        return res;
    }

    private void dfs(char[][] board, int i, List<List<String>> res) {
        int n = board.length;
        // base case
        if (i == n) {
            addSol(board, res);
            return;
        }

        for (int j = 0; j < n; j++) {
            if (isValid(board, i, j)) {
                board[i][j] = 'Q';
                dfs(board, i + 1, res);
                board[i][j] = '.';
            }
        }
    }

    private boolean isValid(char[][] board, int row, int col) {
        int n = board.length;
        // check col
        for (int j = 0; j < n; j++) {
            if (board[row][j] == 'Q') return false;
        }

        // check row
        for (int i = 0; i < n; i++) {
            if (board[i][col] == 'Q') return false;
        }

        // check upper left
        int k = 1;
        while (row - k >= 0 && col - k >= 0) {
            if (board[row - k][col - k] == 'Q') return false;
            k++;
        }

        // check upper right
        k = 1;
        while (row - k >= 0 && col + k < n) {
            if (board[row - k][col + k] == 'Q') return false;
            k++;
        }
        return true;
    }

    private void addSol(char[][] board, List<List<String>> res) {
        int n = board.length;
        List<String> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(String.valueOf(board[i]));
        }
        res.add(list);
    }
}
// @lc code=end

