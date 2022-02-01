import java.util.PriorityQueue;

/*
 * @lc app=leetcode id=1383 lang=java
 *
 * [1383] Maximum Performance of a Team
 */

// @lc code=start
class Solution {
    // time = O(n * (logn + logk)), space = O(n + k)
    public int maxPerformance(int n, int[] speed, int[] efficiency, int k) {
        int[][] player = new int[n][2];
        for (int i = 0; i < n; i++) {
            player[i][0] = efficiency[i];
            player[i][1] = speed[i];
        }

        Arrays.sort(player, (o1, o2) -> o2[0] - o1[0]);

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        long res = 0, total = 0, M = (long)(1e9 + 7);
        for (int[] p : player) {
            total += p[1];
            pq.offer(p[1]);
            if (pq.size() > k) total -= pq.poll();
            res = Math.max(res, total * p[0]);
        }
        return (int)(res % M);
    }
}
// @lc code=end

