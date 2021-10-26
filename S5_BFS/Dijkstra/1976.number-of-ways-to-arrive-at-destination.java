import java.util.PriorityQueue;

/*
 * @lc app=leetcode id=1976 lang=java
 *
 * [1976] Number of Ways to Arrive at Destination
 */

// @lc code=start
class Solution {
    // time = O(n^3), space = O(n^2)
    public int countPaths(int n, int[][] roads) {
        List<long[]>[] graph = new List[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        for (int[] r : roads) {
            graph[r[0]].add(new long[]{r[1], r[2]});
            graph[r[1]].add(new long[]{r[0], r[2]}); // {node, time}
        }

        long[] dist = new long[n];
        Arrays.fill(dist, -1);

        PriorityQueue<long[]> pq = new PriorityQueue<>((o1, o2) -> (int)o1[0] - (int)o2[0]); // {d, node}
        pq.offer(new long[]{0, 0});

        while (!pq.isEmpty()) {
            long[] cur = pq.poll();
            long d = cur[0], node = cur[1];
            if (dist[(int)node] != -1) continue;
            dist[(int)node] = d;
            
            for (long[] x : graph[(int)node]) {
                long next = x[0], time = x[1];
                if (dist[(int)next] != -1) continue;
                pq.offer(new long[]{d + time, next});
            }
        }
        long[] memo = new long[n];
        Arrays.fill(memo, -1);
        return (int) dfs((long)n - 1, dist[n - 1], graph, dist, memo);
    }

    private long dfs(long cur, long t, List<long[]>[] graph, long[] dist, long[] memo) {
        // base case
        if (dist[(int)cur] != t) return 0;
        if (cur == 0) return 1;
        
        if (memo[(int)cur] != -1) return memo[(int)cur];

        long total = 0;
        long M = (long)(1e9 + 7);
        for (long[] x : graph[(int)cur]) {
            long next = x[0], time = x[1];
            total += dfs(next, t - time, graph, dist, memo);
            total %= M;
        }
        memo[(int)cur] = total;
        return total;
    }
}
// @lc code=end

