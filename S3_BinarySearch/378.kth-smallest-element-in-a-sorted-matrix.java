/*
 * @lc app=leetcode id=378 lang=java
 *
 * [378] Kth Smallest Element in a Sorted Matrix
 */

// @lc code=start
class Solution {
    // S1: quick select
    // time = O(n), space = O(n)
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

    // S2: binary search
    // time = O(nlogn), space = O(1)
    public int kthSmallest2(int[][] matrix, int k) {
        int n = matrix.length;
        int left = matrix[0][0], right = matrix[n - 1][n - 1];
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            int count = countSmallerOrEqual(matrix, mid); // # of matrix elements <= mid
            if (count < k) left = mid + 1;
            else right = mid;
        }
        return left;
    }

    private int countSmallerOrEqual(int[][] matrix, int t) {
        int n = matrix.length, count = 0;
        int i = n - 1, j = 0;
        while (i >= 0 && j < n) {
            if (matrix[i][j] <= t) {
                count += i + 1;
                j++;
            } else i--;
        }
        return count;
    }

}
// @lc code=end

