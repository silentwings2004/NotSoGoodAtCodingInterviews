import java.util.Queue;

/*
 * @lc app=leetcode id=2045 lang=java
 *
 * [2045] Second Minimum Time to Reach Destination
 */

// @lc code=start
class Solution {
    // time = O(n), space = O(n)
    public int secondMinimum(int n, int[][] edges, int time, int change) {
        List<Integer>[] graph = new List[n + 1];
        for (int i = 0; i <= n; i++) graph[i] = new ArrayList<>();
        for (int[] edge : edges) {
            int a = edge[0], b = edge[1];
            graph[a].add(b);
            graph[b].add(a);
        }

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{1, 0}); // {node, time}
        int[] visited = new int[n + 1];
        int[] dist = new int[n + 1];
        dist[1] = 0;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int node = cur[0], t = cur[1];
            int ts = 0;
            int round = t / change;
            if (round % 2 == 0) ts = t + time;
            else ts = (round + 1) * change + time;
            for (int next : graph[node]) {
                if (visited[next] < 2 && dist[next] < ts) {
                    queue.offer(new int[]{next, ts});
                    visited[next]++;
                    dist[next] = ts;
                }
                if (next == n && visited[next] == 2) return ts;
            }
        }
        return -1;
    }
}
// @lc code=end

