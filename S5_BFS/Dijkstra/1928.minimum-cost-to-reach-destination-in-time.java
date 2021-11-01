import java.util.PriorityQueue;
import java.util.Queue;

/*
 * @lc app=leetcode id=1928 lang=java
 *
 * [1928] Minimum Cost to Reach Destination in Time
 */

// @lc code=start
class Solution {
    // S1: BFS
    public int minCost(int maxTime, int[][] edges, int[] passingFees) {
        int n = passingFees.length;
        List<int[]>[] graph = new List[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        for (int[] edge : edges) {
            graph[edge[0]].add(new int[]{edge[1], edge[2]});
            graph[edge[1]].add(new int[]{edge[0], edge[2]});
        }

        int[] earliestTime = new int[n + 1];
        Arrays.fill(earliestTime, Integer.MAX_VALUE / 2);

        int[][] dp = new int[n][maxTime + 1]; // dp[c][t] = fee
        for (int i = 0; i < n; i++) Arrays.fill(dp[i], Integer.MAX_VALUE / 2);
        dp[0][0] = passingFees[0];

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0});

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int city = cur[0], time = cur[1], fee = dp[city][time];

            for (int[] x : graph[city]) {
                int next = x[0], deltaT = x[1];
                int newTime = time + deltaT;
                int newFee = fee + passingFees[next];

                if (newTime > maxTime) continue;
                if (newTime > earliestTime[next] && newFee > dp[next][earliestTime[next]]) continue;

                if (newFee < dp[next][newTime]) {
                    dp[next][newTime] = newFee;
                    queue.offer(new int[]{next, newTime});
                    earliestTime[next] = Math.min(newTime, earliestTime[next]);
                }
            }
        }

        int res = Integer.MAX_VALUE / 2;
        for (int t = 0; t <= maxTime; t++) {
            res = Math.min(res, dp[n - 1][t]);
        }
        return res == Integer.MAX_VALUE / 2 ? -1 : res;
    }

    // S2: Dijkstra
    public int minCost2(int maxTime, int[][] edges, int[] passingFees) {
        int n = passingFees.length;
        List<int[]>[] graph = new List[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        for (int[] edge : edges) { 
            graph[edge[0]].add(new int[]{edge[1], edge[2]});
            graph[edge[1]].add(new int[]{edge[0], edge[2]});
        }

        int[] earlistTime = new int[n];
        Arrays.fill(earlistTime, Integer.MAX_VALUE / 2);

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[2] - o2[2]); // {city, time, cost}
        pq.offer(new int[]{0, 0, passingFees[0]});

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int city = cur[0], time = cur[1], cost = cur[2];
            if (time >= earlistTime[city]) continue;
            earlistTime[city] = time;

            if (city == n - 1) return cur[2];

            for (int[] x : graph[city]) {
                int next = x[0], deltaT = x[1];
                int newTime = time + deltaT;
                int newCost = cost + passingFees[next];

                if (newTime > maxTime) continue;
                if (newTime > earlistTime[next]) continue;
                pq.offer(new int[]{next, newTime, newCost});
            }
        }
        return -1;
    }
}
// @lc code=end

