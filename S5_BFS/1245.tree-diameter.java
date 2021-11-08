import java.util.Queue;

/*
 * @lc app=leetcode id=1245 lang=java
 *
 * [1245] Tree Diameter
 */

// @lc code=start
class Solution {
    // time = O(n), space = O(n)   n: # of nodes in the graph
    public int treeDiameter(int[][] edges) {
        int n = edges.length + 1;
        List<Integer>[] graph = new List[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        for (int[] edge : edges) {
            int a = edge[0], b = edge[1];
            graph[a].add(b);
            graph[b].add(a);
        }

        int[] t1 = bfs(graph, 0);
        int[] t2 = bfs(graph, t1[0]);
        return t2[1];
    }

    private int[] bfs(List<Integer>[] graph, int start) {
        int n = graph.length;
        int[] dist = new int[n];
        Arrays.fill(dist, -1);
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        dist[start] = 0;

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (int next : graph[cur]) {
                if (dist[next] == -1) {
                    queue.offer(next);
                    dist[next] = dist[cur] + 1;
                }
            }
        }

        int maxDist = 0, idx = -1;
        for (int i = 0; i < n; i++) {
            if (dist[i] > maxDist) {
                idx = i;
                maxDist = dist[i];
            }
        }
        return new int[]{idx, maxDist};

    }
}
// @lc code=end

