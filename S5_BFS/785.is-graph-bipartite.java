import java.util.LinkedList;
import java.util.Queue;

/*
 * @lc app=leetcode id=785 lang=java
 *
 * [785] Is Graph Bipartite?
 */

// @lc code=start
class Solution {
    // S1
    // time = O(n), space = O(n)
    public boolean isBipartite1(int[][] graph) {
        int n = graph.length;
        int[] visited = new int[n];
        Arrays.fill(visited, -1); // -1: unvisited; 0: group0; 1: group1
        for (int i = 0; i < n; i++) {
            if (visited[i] != -1) continue;
            Queue<int[]> queue = new LinkedList<>(); // {node, group}
            queue.offer(new int[]{i, 0});
            visited[i] = 0;

            while (!queue.isEmpty()) {
                int[] cur = queue.poll();
                int node = cur[0], group = cur[1];
                for (int next : graph[node]) {
                    if (visited[next] != -1) {
                        if (visited[next] == group) return false; // 矛盾
                    } else {
                        visited[next] = 1 - group;
                        queue.offer(new int[]{next, 1 - group});
                    }
                }
            }            
        }
        return true;
    }

    // S2: Union Find
    // time = O(n), space = O(n)
    private int[] parent;
    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        parent = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;

        for (int i = 0; i < n; i++) {
            int k = 0;
            if (graph[i].length > 0) k = graph[i][0];
            for (int x : graph[i]) {
                if (findParent(i) == findParent(x)) return false;
                union(k, x);
            }
        }
        return true;
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

