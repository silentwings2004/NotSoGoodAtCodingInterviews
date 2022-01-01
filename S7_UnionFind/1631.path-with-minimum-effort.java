/*
 * @lc app=leetcode id=1631 lang=java
 *
 * [1631] Path With Minimum Effort
 */

// @lc code=start
class Solution {
    // time = O(m * n * 10^6), space = O(m * n)
    private int[][] directions = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    public int minimumEffortPath(int[][] heights) {
        int left = 0, right = (int) 1e6;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (helper(heights, mid)) right = mid;
            else left = mid + 1;
        }
        return left;
    }

    private boolean helper(int[][] heights, int t) {
        int m = heights.length, n = heights[0].length;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0});
        boolean[][] visited = new boolean[m][n];
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0], y = cur[1];
            if (x == m - 1 && y == n - 1) return true;

            for (int[] dir : directions) {
                int i = x + dir[0];
                int j = y + dir[1];
                if (i < 0 || i >= m || j < 0 || j >= n) continue;
                if (visited[i][j]) continue;
                if (Math.abs(heights[i][j] - heights[x][y]) > t) continue;
                queue.offer(new int[]{i, j});
                visited[i][j] = true;
            }
        }
        return false;
    }

    // S2: Union Find
    // time = O(m * n * log(m * n)), space = O(m * n)
    int[] parent;
    public int minimumEffortPath2(int[][] heights) {
        int m = heights.length, n = heights[0].length;
        parent = new int[m * n];
        for (int i = 0; i < m * n; i++) parent[i] = i;

        List<int[]> edges = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (j < n - 1) {
                    edges.add(new int[]{Math.abs(heights[i][j] - heights[i][j + 1]), i * n + j, i * n + j + 1});
                }
                if (i < m - 1) {
                        edges.add(new int[]{Math.abs(heights[i][j] - heights[i + 1][j]), i * n + j, (i + 1) * n + j});
                }
            }
        }

        Collections.sort(edges, (o1, o2) -> o1[0] - o2[0]);

        for (int[] edge : edges) {
            int a = edge[1], b = edge[2];
            if (findParent(a) != findParent(b)) union(a, b);
            if (findParent(0) == findParent((m - 1) * n + (n - 1))) return edge[0];
        }
        return 0;
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

