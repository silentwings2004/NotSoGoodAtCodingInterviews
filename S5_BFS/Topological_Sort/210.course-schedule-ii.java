/*
 * @lc app=leetcode id=210 lang=java
 *
 * [210] Course Schedule II
 */

// @lc code=start
class Solution {
    // S1: bfs
    // time = O(V + E), space = O(V + E)
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<Integer>[] graph = new List[numCourses];
        for (int i = 0; i < numCourses; i++) graph[i] = new ArrayList<>();
        int[] indegree = new int[numCourses];
        for (int[] p : prerequisites) {
            graph[p[1]].add(p[0]);
            indegree[p[0]]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }

        List<Integer> res = new ArrayList<>();
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            res.add(cur);
            for (int next : graph[cur]) {
                indegree[next]--;
                if (indegree[next] == 0) {
                    queue.offer(next);
                }
            }
        }
        if (res.size() != numCourses) return new int[0];
        int[] ans = new int[numCourses];
        for (int i = 0; i < numCourses; i++) ans[i] = res.get(i);
        return ans;
    }

    // S2: dfs
    // time = O(V + E), space = O(V + E)
    public int[] findOrder2(int numCourses, int[][] prerequisites) {
        List<Integer>[] graph = new List[numCourses];
        for (int i = 0; i < numCourses; i++) graph[i] = new ArrayList<>();
        for (int[] p : prerequisites) graph[p[1]].add(p[0]);

        int[] status = new int[numCourses];
        List<Integer> res = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (containsCycle(graph, status, i, res)) return new int[0]; 
        }
        int[] ans = new int[numCourses];
        for (int i = 0; i < numCourses; i++) ans[i] = res.get(i);
        return ans;
    }

    private boolean containsCycle(List<Integer>[] graph, int[] status, int cur, List<Integer> res) {
        if (status[cur] == 2) return false;
        if (status[cur] == 1) return true;

        status[cur] = 1;
        for (int next : graph[cur]) {
            if (containsCycle(graph, status, next, res)) return true;
        }
        status[cur] = 2;
        res.add(0, cur); // 第一个加入list的是最后上的那门课，所以是倒着加！
        return false;
    }
}
// @lc code=end

