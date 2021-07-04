package S13_DPII;
import java.util.*;

/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: MaximalSquare
 * Creater: Silentwings
 * Date: Mar, 2020
 * Description: 221. Maximal Square
 */
public class LC221_MaximalSquare {
    /**
     * Given a 2D binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its
     * area.
     *
     * Example:
     *
     * Input:
     *
     * 1 0 1 0 0
     * 1 0 1 1 1
     * 1 1 1 1 1
     * 1 0 0 1 0
     *
     * Output: 4
     * @param matrix
     * @return
     */
    // S1: DP I  --> time = O(m * n), space = O(m * n)
    // time = O(n^2), space = O(n^2)
    public int maximalSquare(char[][] matrix) {
        // corner case
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return 0;
        }

        int row = matrix.length, col = matrix[0].length;
        int max = 0;
        int[][] dp = new int[row][col];
        for (int i = 0; i < row; i++) {
            dp[i][0] = matrix[i][0] - '0';
            max = Math.max(dp[i][0], max);
        }
        for (int j = 0; j < col; j++) {
            dp[0][j] = matrix[0][j] - '0';
            max = Math.max(dp[0][j], max);
        }

        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (matrix[i][j] == '1') {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                }
                max = Math.max(max, dp[i][j]);
            }
        }
        return max * max;
    }

    // S2: DPII  --> time = O(m * n), space = O(m *n)
    public int maximalSquare2(char[][] matrix) {
        // corner case
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) return 0;

        int rows = matrix.length;
        int cols = matrix[0].length;
        // 行和列多申请一行，这样的话第一行和第一列的情况就不需要单独考虑了。
        int[][] dp = new int[rows + 1][cols + 1];
        int res = 0;

        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++) {
                //因为多申请了一行一列，所以这里下标要减 1
                if (matrix[i - 1][j - 1] == '1') {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                    res = Math.max(res, dp[i][j]);
                }
            }
        }
        return res * res;
    }

    // S3: DP III - use 1D dp --> time = O(m * n), space = O(n) 使用了一个一维数组 dp
    public int maximalSquare3(char[][] matrix) {
        // corner case
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) return 0;

        int rows = matrix.length;
        int cols = matrix[0].length;
        int[] dp = new int[cols + 1];
        int max = 0, prev = 0;

        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++) {
                int count = dp[j];
                if (matrix[i - 1][j - 1] == '1') {
                    dp[j] = Math.min(Math.min(dp[j - 1], prev), dp[j]) + 1;
                    max = Math.max(max, dp[j]);
                } else {
                    dp[j] = 0;
                }
                prev = count;
            }
        }
        return max * max;
    }
}
