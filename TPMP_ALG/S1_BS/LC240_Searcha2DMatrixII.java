package S1_BS;
import java.util.*;
/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: Searcha2DMatrixII
 * Creater: Silentwings
 * Date: Aug, 2020
 * Description: 240. Search a 2D Matrix II
 */
public class LC240_Searcha2DMatrixII {
    /**
     * Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following
     * properties:
     *
     * Integers in each row are sorted in ascending from left to right.
     * Integers in each column are sorted in ascending from top to bottom.
     * Example:
     *
     * Consider the following matrix:
     *
     * [
     *   [1,   4,  7, 11, 15],
     *   [2,   5,  8, 12, 19],
     *   [3,   6,  9, 16, 22],
     *   [10, 13, 14, 17, 24],
     *   [18, 21, 23, 26, 30]
     * ]
     * Given target = 5, return true.
     *
     * Given target = 20, return false.
     * @param matrix
     * @param target
     * @return
     */
    // time = O(m * n), space = O(1)
    public boolean searchMatrix(int[][] matrix, int target) {
        // corner case
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) return false;

        int row = matrix.length, col = matrix[0].length;
        int i = row - 1, j = 0;
        while (i >= 0 && i < row && j >= 0 && j < col) {
            if (matrix[i][j] == target) return true;
            if (matrix[i][j] < target) j++;
            else i--;
        }
        return false;
    }
}
