package S12_Recursion;
import java.util.*;

/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: SpiralMatrix
 * Creater: Silentwings
 * Date: Mar, 2020
 * Description: 54. Spiral Matrix
 */
public class LC54_SpiralMatrix {
    /**
     * Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.
     *
     * Example 1:
     *
     * Input:
     * [
     *  [ 1, 2, 3 ],
     *  [ 4, 5, 6 ],
     *  [ 7, 8, 9 ]
     * ]
     * Output: [1,2,3,6,9,8,7,4,5]
     * Example 2:
     *
     * Input:
     * [
     *   [1, 2, 3, 4],
     *   [5, 6, 7, 8],
     *   [9,10,11,12]
     * ]
     * Output: [1,2,3,4,8,12,11,10,9,5,6,7]
     *
     * @param matrix
     * @return
     */
    public List<Integer> spiralOrder(int[][] matrix) {

    }

    private void spiral(int offset, int size, int[][] matrix) {
        // base case

        for (int i = 0; i < colsize - 1; i++）matrix[offset][offset + i];
        for (int i = 0; i < rowsize - 1; i++) matrix[offset + i][offset + size - 1];
        for (int i = 0; i < colsize - 1; i++) matrix[offset + size - 1][offset + size - i];
        for (int i = 0; i < rowsize - 1, i++) matrix[offset + size - 1][offset];
        spiral(offset + 1, size - 2, matrix);
    }
}
