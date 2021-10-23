/*
 * @lc app=leetcode id=1066 lang=java
 *
 * [1066] Campus Bikes II
 */

// @lc code=start
class Solution {
    // time = O(m * n!), space = O(m * n)
    public int assignBikes(int[][] workers, int[][] bikes) {
        int m = workers.length, n = bikes.length;
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]); // {cost, state}
        pq.offer(new int[]{0, 0});
        boolean[] visited = new boolean[2 << n];
        int[][] dist = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int x1 = workers[i][0];
                int y1 = workers[i][1];
                int x2 = bikes[j][0];
                int y2 = bikes[j][1];
                dist[i][j] = Math.abs(x1 - x2) + Math.abs(y1 - y2);
            }
        }

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int cost = cur[0], state = cur[1];
            if (visited[state]) continue;
            visited[state] = true;

            int i = Integer.bitCount(state);
            if (i == m) return cost;

            for (int j = 0; j < n; j++) {
                if (((state >> j) & 1) == 1) continue; // bike j is occupied
                int newState = state | (1 << j);
                if (visited[newState]) continue;
                pq.offer(new int[]{cost + dist[i][j], newState});
            }
        }
        return -1;
    }
}
// @lc code=end

