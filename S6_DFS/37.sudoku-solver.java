/*
 * @lc app=leetcode id=37 lang=java
 *
 * [37] Sudoku Solver
 */

// @lc code=start
class Solution {
    // time = O(1), space = O(1)
    public void solveSudoku(char[][] board) {
        dfs(board, 0, 0);
    }

    private boolean dfs(char[][] board, int i, int j) {
        // base case
        if (i == 9) return true;
        if (j == 9) return dfs(board, i + 1, 0);
        if (board[i][j] != '.') return dfs(board, i, j + 1);

        for (char c = '1'; c <= '9'; c++) {
            if (!isValid(board, i, j, c)) continue;
            board[i][j] = c;
            if (dfs(board, i, j + 1)) return true;
            board[i][j] = '.'; // setback
        }
        return false;
    }
    
    private boolean isValid(char[][] board, int i, int j, int c) {
        for (int row = 0; row < 9; row++) {
            if (board[row][j] == c) return false;
        }

        for (int col = 0; col < 9; col++) {
            if (board[i][col] == c) return false;
        }

        int x = i / 3 * 3, y = j / 3 * 3;
        for (int p = 0; p < 3; p++) {
            for (int q = 0; q < 3; q++) {
                if (board[x + p][y + q] == c) return false;
            }
        }
        return true;
    }
}
// @lc code=end

