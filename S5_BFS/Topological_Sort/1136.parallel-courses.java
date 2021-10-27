import java.util.Queue;

/*
 * @lc app=leetcode id=1136 lang=java
 *
 * [1136] Parallel Courses
 */

// @lc code=start
class Solution {
    // time = O(V + E) = O(n), space = O(V + E) = O(n)
    public int minimumSemesters(int n, int[][] relations) {
        List<Integer>[] graph = new List[n + 1];
        for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();
        int[] indegree = new int[n + 1];
        for (int[] r : relations) {
            graph[r[0]].add(r[1]);
            indegree[r[1]]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        int count = 0;
        for (int i = 1; i <= n; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
                count++;
            }
        }

        int step = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int cur = queue.poll();
                for (int next : graph[cur]) {
                    indegree[next]--;
                    if (indegree[next] == 0) {
                        queue.offer(next);
                        count++;
                    }
                }
            }
            step++;
        }
        return count == n ? step : -1;
    }
}
// @lc code=end

