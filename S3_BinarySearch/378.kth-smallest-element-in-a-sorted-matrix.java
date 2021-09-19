/*
 * @lc app=leetcode id=378 lang=java
 *
 * [378] Kth Smallest Element in a Sorted Matrix
 */

// @lc code=start
class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        int m = matrix.length, n = matrix[0].length;
        return quickselect(matrix, 0, m * n - 1, k);
    }

    private int quickselect(int[][] matrix, int a, int b, int k) {
        int n = matrix[0].length;
        int idx = a + (b - a) / 2;
        int x = idx / n, y = idx % n;
        int pivot = matrix[x][y];

        int i = a, j = b, t = a;
        while (t <= j) {
            if (matrix[t / n][t % n] < pivot) swap(matrix, t++, i++);
            else if (matrix[t / n][t % n] > pivot) swap(matrix, t, j--);
            else t++;
        }

        if (i - a >= k) return quickselect(matrix, a, i - 1, k);
        if (j - a + 1 >= k) return pivot;
        return quickselect(matrix, j + 1, b, k - (j - a + 1));
    }

    private void swap(int[][] matrix, int i, int j) {
        int n = matrix[0].length;
        int temp = matrix[i / n][i % n];
        matrix[i / n][i % n] = matrix[j / n][j % n];
        matrix[j / n][j % n] = temp;
    }
}
// @lc code=end

