package S8_BFSDFS;
import java.util.*;

/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: KthSmallestElementinaSortedMatrix
 * Creater: Silentwings
 * Date: Feb, 2020
 * Description: 378. Kth Smallest Element in a Sorted Matrix
 */
public class LC378_KthSmallestElementinaSortedMatrix {
    /**
     * Given a n x n matrix where each of the rows and columns are sorted in ascending order,
     * find the kth smallest element in the matrix.
     *
     * Note that it is the kth smallest element in the sorted order, not the kth distinct element.
     *
     * Example:
     *
     * matrix = [
     *    [ 1,  5,  9],
     *    [10, 11, 13],
     *    [12, 13, 15]
     * ],
     * k = 8,
     *
     * return 13.
     *
     * PQ: 1   5   9    (k - 1)
     * 1 -> 10 -> 12
     * 5 -> 11 -> 13
     * 9 -> 13 -> 15
     *
     * 1. PriorityQueue: 链表
     * 2. Binary Search: 数个数
     *
     * Note:
     * You may assume k is always valid, 1 ≤ k ≤ n2.
     *
     * time = O(klogk)
     * space = O(min(m + n - 1, k)）
     *
     * @param matrix
     * @param k
     * @return
     */
    // S1: Heap (Priority Queue)
    // time = O(klog(min(m + n, k))), space = O(min(m + n, k))
    public int kthSmallest(int[][] matrix, int k) {
        // corner case
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            throw new IllegalArgumentException();
        }

        int row = matrix.length, col = matrix[0].length;
        PriorityQueue<Integer> minHeap = new PriorityQueue<>((o1, o2) -> matrix[o1 / col][o1 % col] - matrix[o2 / col][o2 % col]);
        minHeap.offer(0);
        HashSet<Integer> visited = new HashSet<>();
        int res = matrix[0][0];

        while (k-- > 0) {  // O(k)
            if (minHeap.isEmpty()) break;
            int cur = minHeap.poll(); // O(logk)
            int i = cur / col, j = cur % col;
            res = matrix[i][j];

            // add right neighbor
            if (j < col - 1) {
                int rightNei = i * col + j + 1;
                if (visited.add(rightNei)) minHeap.offer(rightNei);
            }

            // add down neighbor
            if (i < row - 1) {
                int downNei = (i + 1) * col + j;
                if (visited.add(downNei)) minHeap.offer(downNei);
            }
        }
        return res;
    }

    // S2: Binary Search
    public int kthSmallest2(int[][] matrix, int k) {
        int row = matrix.length;
        int col = matrix[0].length;
        int left = matrix[0][0];
        int right = matrix[row -1][col - 1]; // row = col = n

        while (left < right) {
            int mid = left + (right - left) / 2;
            int num = count(matrix, mid, row, col);
            if (num < k) left = mid + 1;
            else right = mid;
        }
        return right;
    }

    private int count(int[][] matrix, int mid, int row, int col) {
        // 以列为单位找，找到每一列最后一个<=mid的数即知道每一列有多少个数<=mid
        int i = row - 1;
        int j = 0;
        int count = 0;
        while (i >= 0 && j < col) {
            if (matrix[i][j] <= mid) {
                count += i + 1; // 第j列有i+1个元素<=mid
                j++;
            } else i--; // 第j列目前的数大于mid，需要继续在当前列往上找
        }
        return count;
    }
}
