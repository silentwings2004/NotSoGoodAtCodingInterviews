package S1_BS;
import java.util.*;
/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: Searcha2DMatrix
 * Creater: Silentwings
 * Date: Aug, 2020
 * Description: 74. Search a 2D Matrix
 */
public class LC74_Searcha2DMatrix {
    /**
     * Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following
     * properties:
     *
     * Integers in each row are sorted from left to right.
     * The first integer of each row is greater than the last integer of the previous row.
     * Example 1:
     *
     * Input:
     * matrix = [
     *   [1,   3,  5,  7],
     *   [10, 11, 16, 20],
     *   [23, 30, 34, 50]
     * ]
     * target = 3
     * Output: true
     *
     * Example 2:
     *
     * Input:
     * matrix = [
     *   [1,   3,  5,  7],
     *   [10, 11, 16, 20],
     *   [23, 30, 34, 50]
     * ]
     * target = 13
     * Output: false
     * @param matrix
     * @param target
     * @return
     */
    // time = O(log(mn)), space = O(1)
    public boolean searchMatrix(int[][] matrix, int target) {
        // corner case
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) return false;

        int row = matrix.length, col = matrix[0].length;
        int left = 0, right = row * col - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int i = mid / col, j = mid % col;
            if (matrix[i][j] == target) return true;
            if (matrix[i][j] < target) left = mid + 1;
            else right = mid - 1;
        }
        return false;
    }
}
