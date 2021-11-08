import java.util.Queue;

/*
 * @lc app=leetcode id=2039 lang=java
 *
 * [2039] The Time When the Network Becomes Idle
 */

// @lc code=start
class Solution {
    // time = O(n), space = O(n)
    public int networkBecomesIdle(int[][] edges, int[] patience) {
        int n = patience.length;
        List<Integer>[] graph = new List[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        for (int[] edge : edges) {
            int a = edge[0], b = edge[1];
            graph[a].add(b);
            graph[b].add(a);
        }

        int[] steps = bfs(graph, 0, n);

        int res = 0;
        for (int i = 1; i < n; i++) {
            int time = steps[i] * 2;
            int sent = time / patience[i] == 0 ? 0 : (time % patience[i] == 0 ? time / patience[i] - 1 : time / patience[i]);
            int totalTime = time + patience[i] * sent;
            res = Math.max(res, totalTime);
        }
        return res + 1;
    }

    private int[] bfs(List<Integer>[] graph, int start, int n) {
        int[] steps = new int[n];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        boolean[] visited = new boolean[n];
        visited[start] = true;

        int step = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int cur = queue.poll();
                steps[cur] = step;
                for (int next : graph[cur]) {
                    if (visited[next]) continue;
                    queue.offer(next);
                    visited[next] = true;
                }
            }
            step++;
        }
        return steps;
    }
}
// @lc code=end

