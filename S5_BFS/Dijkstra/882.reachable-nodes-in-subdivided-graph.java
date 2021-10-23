/*
 * @lc app=leetcode id=882 lang=java
 *
 * [882] Reachable Nodes In Subdivided Graph
 */

// @lc code=start
class Solution {
    // time = O(mlogn), space = O(n)
    public int reachableNodes(int[][] edges, int maxMoves, int n) {
        List<int[]>[] graph = new List[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        for (int[] edge : edges) {
            graph[edge[0]].add(new int[]{edge[1], edge[2] + 1});
            graph[edge[1]].add(new int[]{edge[0], edge[2] + 1});
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]); // {dist, nodeIdx}
        pq.offer(new int[]{0, 0});
        boolean[] visited = new boolean[n];
        int[] dist = new int[n];

        while (!pq.isEmpty()) {
            int[] top = pq.poll();
            int d = top[0], cur = top[1];
            if (visited[cur]) continue;
            visited[cur] = true;
            dist[cur] = d;

            for (int[] x : graph[cur]) {
                int next = x[0], weight = x[1];
                if (visited[next]) continue;
                if (d + weight > maxMoves) continue;
                pq.offer(new int[]{d + weight, next});
            }
        }

        int count = 0;
        for (int[] edge : edges) {
            int a = edge[0], b = edge[1], sum = 0;
            if (visited[a]) sum += maxMoves - dist[a];
            if (visited[b]) sum += maxMoves - dist[b];
            count += Math.min(sum, edge[2]);
        }

        for (int i = 0; i < n; i++) {
            if (visited[i]) count++;
        }
        return count;
    }
}
// @lc code=end

