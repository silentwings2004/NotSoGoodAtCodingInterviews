import java.util.Queue;

/*
 * @lc app=leetcode id=1617 lang=java
 *
 * [1617] Count Subtrees With Max Distance Between Cities
 */

// @lc code=start
class Solution {
    List<Integer>[] graph;
    public int[] countSubgraphsForEachDiameter(int n, int[][] edges) {
        // step 1: build graph
        graph = new List[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        for (int[] edge : edges) {
            int a = edge[0], b = edge[1];
            graph[a - 1].add(b - 1);
            graph[b - 1].add(a - 1);
        }

        // step 2: iterate all states
        int[] allow = new int[n];
        int[] dist = new int[n];
        int[] count = new int[n];
        for (int state = 1; state < (1 << n); state++) {
            int a = -1, nodeNum = 0;
            for (int i = 0; i < n; i++) {
                if (((state >> i) & 1) == 1) {
                    allow[i] = 1;
                    nodeNum++;
                    a = i;
                }
                else allow[i] = 0;
            }
            Arrays.fill(dist, -1); // 看遍历后有多少点不是-1了来检查连通性
            int b = bfs(a, dist, allow);
            int countVisited = 0;
            for (int i = 0; i < n; i++) countVisited += (dist[i] != -1 ? 1 : 0);
            if (countVisited != nodeNum) continue; // 验证连通性
            
            Arrays.fill(dist, -1);
            int c = bfs(b, dist, allow);
            int maxDiameter = findMax(dist);
            count[maxDiameter]++;
        }
        int[] res = new int[n - 1];
        for (int i = 1; i < n; i++) res[i - 1] = count[i];
        return res;
    }

    private int bfs(int start, int[] dist, int[] allow) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        dist[start] = 0;
        int maxDist = 0, idx = start;

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (int next : graph[cur]) {
                if (allow[next] == 0) continue;
                if (dist[next] == -1) {
                    queue.offer(next);
                    dist[next] = dist[cur] + 1;
                    if (dist[next] > maxDist) {
                        maxDist = dist[next];
                        idx = next;
                    }
                }
            }
        }
        return idx;
    }

    private int findMax(int[] nums) {
        int res = 0;
        for (int x : nums) res = Math.max(res, x);
        return res;
    }
}
// @lc code=end

