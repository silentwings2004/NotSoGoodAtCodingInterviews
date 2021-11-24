import java.util.List;
import java.util.TreeMap;

/*
 * @lc app=leetcode id=1632 lang=java
 *
 * [1632] Rank Transform of a Matrix
 */

// @lc code=start
class Solution {
    // time = O(m * n * log(m * n)), space = O(m + n)
    int[] parent;
    public int[][] matrixRankTransform(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;

        // sort and group the matrix
        TreeMap<Integer, List<Integer>> map = new TreeMap<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                map.putIfAbsent(matrix[i][j], new ArrayList<>());
                map.get(matrix[i][j]).add(i * n + j);
            }
        }

        int[] rank = new int[m + n];
        for (int key : map.keySet()) {
            List<Integer> list = map.get(key);
            parent = new int[m + n];
            int[] temp = rank.clone();
            for (int i = 0; i < m + n; i++) parent[i] = i;
            for (int x : list) {
                int pi = findParent(x / n), pj = findParent(x % n + m);
                if (findParent(pi) != findParent(pj)) union(pi, pj);
                if (pi < pj) temp[pi] = Math.max(temp[pi], temp[pj]);
                else temp[pj] = Math.max(temp[pi], temp[pj]);
            }

            for (int x : list) {
                int i = x / n, j = x % n;
                int p = findParent(i);
                rank[i] = temp[p] + 1;
                rank[j + m] = temp[p] + 1;
                matrix[i][j] = temp[p] + 1;
            }
        }
        return matrix;
    }

    private int findParent(int x) {
        if (x != parent[x]) parent[x] = findParent(parent[x]);
        return parent[x];
    }

    private void union(int x, int y) {
        x = parent[x];
        y = parent[y];
        if (x < y) parent[y] = x;
        else parent[x] = y;
    }
}
// @lc code=end

