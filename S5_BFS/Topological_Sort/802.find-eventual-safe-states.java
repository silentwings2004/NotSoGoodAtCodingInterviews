/*
 * @lc app=leetcode id=802 lang=java
 *
 * [802] Find Eventual Safe States
 */

// @lc code=start
class Solution {
    // S1: dfs
    // time = O(V + E), space = O(V)
    public List<Integer> eventualSafeNodes(int[][] graph) {
        List<Integer> res = new ArrayList<>();
        int n = graph.length;
        int[] status = new int[n];
        for (int i = 0; i < n; i++) {
            if (!containsCycle(graph, status, i)) res.add(i);
        }
        return res;
    }

    private boolean containsCycle(int[][] graph, int[] status, int cur) {
        if (status[cur] == 2) return false;
        if (status[cur] == 1) return true;

        status[cur] = 1;
        for (int next : graph[cur]) {
            if (containsCycle(graph, status, next)) return true;
        }

        status[cur] = 2;
        return false;
    }

    // S2: BFS
    // time = O(V + E), space = O(V)
    public List<Integer> eventualSafeNodes2(int[][] graph) {
        List<Integer> res = new ArrayList<>();
        int n = graph.length;
        int[] outdegree = new int[n];
        List<Integer>[] prev = new List[n];
        for (int i = 0; i < n; i++) prev[i] = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j : graph[i]) {
                prev[j].add(i);
                outdegree[i]++;
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (outdegree[i] == 0) {
                queue.offer(i);
                res.add(i);
            }
        }

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (int x : prev[cur]) {
                outdegree[x]--;
                if (outdegree[x] == 0) {
                    queue.offer(x);
                    res.add(x);
                }
            }
        }
        // order被打乱，需要重新sort
        Collections.sort(res);
        return res;
    }
}
// @lc code=end

