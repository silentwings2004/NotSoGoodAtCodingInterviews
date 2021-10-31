/*
 * @lc app=leetcode id=1192 lang=java
 *
 * [1192] Critical Connections in a Network
 */

// @lc code=start
class Solution {
    // S1: Tarjan
    // time = O(V + E), space = O(E)
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer>[] graph = new List[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        for (List<Integer> c : connections) {
            int a = c.get(0), b = c.get(1);
            graph[a].add(b);
            graph[b].add(a);
        }

        // init Tarjan
        boolean[] visited = new boolean[n];
        int[] dfn = new int[n];
        int[] low = new int[n];
        int[] parent = new int[n];
        Arrays.fill(parent, -1);

        // dfs
        for (int i = 0; i < n; i++) {
            if (visited[i]) continue;
            dfs(graph, i, visited, dfn, low, parent, res);
        }
        return res;
    }

    private int t = 0;
    private void dfs(List<Integer>[] graph, int cur, boolean[] visited, int[] dfn, int[] low, int[] parent, List<List<Integer>> res) {
        visited[cur] = true;
        dfn[cur] = low[cur] = t++;

        for (int next : graph[cur]) {
            if (next == parent[cur]) continue;
            if (!visited[next]) {
                parent[next] = cur;
                dfs(graph, next, visited, dfn, low, parent, res);
                if (low[next] > dfn[cur]) {
                    res.add(Arrays.asList(cur, next));
                }
            }
            low[cur] = Math.min(low[cur], low[next]);
        }
    }
}
// @lc code=end

