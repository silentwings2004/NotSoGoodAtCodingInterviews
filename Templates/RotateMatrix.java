package Templates;

public class RotateMatrix {
    // rotate each layer counter-clockwise
    public void rotate(int[][] grid, int offset) { // O(m + n)
        int m = grid.length, n = grid[0].length;

        int temp = grid[offset][offset];

        // top
        for (int j = offset; j < n - 1 - offset; j++) {
            grid[offset][j] = grid[offset][j + 1];
        }

        // right
        for (int i = offset; i < m - 1 - offset; i++) {
            grid[i][n - 1 - offset] = grid[i + 1][n - 1 - offset];
        }

        // bottom
        for (int j = n - 1 - offset; j > offset; j--) {
            grid[m - 1 - offset][j] = grid[m - 1 - offset][j - 1];
        }

        // left
        for (int i = m - 1 - offset; i > offset; i--) {
            grid[i][offset] = grid[i - 1][offset];
        }

        grid[offset + 1][offset] = temp;
    }
}
