import java.util.Queue;

/*
 * @lc app=leetcode id=847 lang=java
 *
 * [847] Shortest Path Visiting All Nodes
 */

// @lc code=start
class Solution {
    // time = O(n^2*2^n), space = O(n*2^n)
    public int shortestPathLength(int[][] graph) {
        int n = graph.length;
        int finalState = 0;
        for (int i = 0; i < n; i++) finalState |= 1 << i;
        boolean[][] visited = new boolean[n][1 << n];
        Queue<int[]> queue = new LinkedList<>(); // {node, state}
        for (int i = 0; i < n; i++) {
            queue.offer(new int[]{i, 1 << i});
            visited[i][1 << i] = true;
        }

        int step = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int[] cur = queue.poll();
                int node = cur[0], state = cur[1];
                for (int nextNode : graph[node]) {
                    int nextState = state | (1 << nextNode);
                    if (nextState == finalState) return step + 1;
                    if (!visited[nextNode][nextState]) {
                        queue.offer(new int[]{nextNode, nextState});
                        visited[nextNode][nextState] = true;
                    }
                }
            }
            step++;
        }
        return 0;
    }
}
// @lc code=end

