import java.util.Queue;

/*
 * @lc app=leetcode id=2050 lang=java
 *
 * [2050] Parallel Courses III
 */

// @lc code=start
class Solution {
    // time = O(V + E), space = O(V + E)
    public int minimumTime(int n, int[][] relations, int[] time) {
        List<Integer>[] graph = new List[n + 1];
        for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();
        int[] indegree = new int[n + 1];
        for (int[] r : relations) {
            graph[r[0]].add(r[1]);
            indegree[r[1]]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        int[] t = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            t[i] = time[i - 1];
            if (indegree[i] == 0) queue.offer(i);
        }

        int res = 0;
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            res = Math.max(res, t[cur]);
            for (int next : graph[cur]) {
                t[next] = Math.max(t[next], t[cur] + time[next - 1]);
                indegree[next]--;
                if (indegree[next] == 0) queue.offer(next);
            }
        }
        return res;
    }
}
// @lc code=end

