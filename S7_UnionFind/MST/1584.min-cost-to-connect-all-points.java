import java.util.PriorityQueue;

/*
 * @lc app=leetcode id=1584 lang=java
 *
 * [1584] Min Cost to Connect All Points
 */

// @lc code=start
class Solution {
    // S1: Kruskal
    // time = O(n^2*logn), space = O(n^2)
    int[] parent;
    public int minCostConnectPoints(int[][] points) {
        int n = points.length;
        parent = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]);
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int x1 = points[i][0], y1 = points[i][1];
                int x2 = points[j][0], y2 = points[j][1];
                int dist = Math.abs(x1 - x2) + Math.abs(y1 - y2);
                pq.offer(new int[]{dist, i, j});
            }
        }

        int count = 0, res = 0;
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int dist = cur[0], a = cur[1], b = cur[2];
            if (findParent(a) != findParent(b)) {
                union(a, b);
                count++;
                res += dist;
            }
            if (count == n - 1) break;
        }
        return res;
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

