/*
 * @lc app=leetcode id=1591 lang=java
 *
 * [1591] Strange Printer II
 */

// @lc code=start
class Solution {
    // time = O(V + E) = O(k * m * n), space = O(k)    k: number of colors
    public boolean isPrintable(int[][] targetGrid) {
        int m = targetGrid.length, n = targetGrid[0].length;

        int[] left = new int[61];
        int[] right = new int[61];
        int[] top = new int[61];
        int[] bottom = new int[61];
        Arrays.fill(left, n);
        Arrays.fill(right, -1);
        Arrays.fill(top, m);
        Arrays.fill(bottom, -1);

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int color = targetGrid[i][j];
                left[color] = Math.min(left[color], j);
                right[color] = Math.max(right[color], j);
                top[color] = Math.min(top[color], i);
                bottom[color] = Math.max(bottom[color], i);
            }
        }

        List<Integer>[] graph = new List[61];
        for (int i = 0; i < 61; i++) graph[i] = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int color = 1; color <= 60; color++) {
                    if (i >= top[color] && i <= bottom[color] && j >= left[color] && j <= right[color]) {
                        // construct the graph
                        if (color != targetGrid[i][j]) { // can't let color point to color itself
                            graph[targetGrid[i][j]].add(color);
                        }
                    }
                }
            }
        }

        int[] status = new int[61];
        for (int i = 0; i < 61; i++) {
            if (containsCycle(graph, status, i)) return false;
        }
        return true;
    }

    private boolean containsCycle(List<Integer>[] graph, int[] status, int cur) {
        if (status[cur] == 2) return false;
        if (status[cur] == 1) return true;

        status[cur] = 1;
        for (int next : graph[cur]) {
            if (containsCycle(graph, status, next)) return true;
        }

        status[cur] = 2;
        return false;
    }
}
// @lc code=end

