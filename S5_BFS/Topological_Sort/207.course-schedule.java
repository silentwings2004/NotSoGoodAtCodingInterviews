import java.util.Queue;

/*
 * @lc app=leetcode id=207 lang=java
 *
 * [207] Course Schedule
 */

// @lc code=start
class Solution {
    // S1: dfs
    // time = O(V + E), space = O(V + E)
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] visited = new int[numCourses];
        List<Integer>[] graph = new List[numCourses]; // contains its all prerequisites
        for (int i = 0; i < numCourses; i++) graph[i] = new ArrayList<>();
        for (int[] p : prerequisites) graph[p[0]].add(p[1]);
        
        for (int i = 0; i < numCourses; i++) {
            if (containsCycle(graph, visited, i)) return false;
        }
        return true;
    }

    private boolean containsCycle(List<Integer>[] graph, int[] visited, int cur) {
        if (visited[cur] == 2) return false;
        if (visited[cur] == 1) return true;

        visited[cur] = 1;
        for (int next : graph[cur]) {
            if (containsCycle(graph, visited, next)) return true;
        }
        visited[cur] = 2;
        return false;
    }

    // S2: bfs
    // time = O(V + E), space = O(V + E)
    public boolean canFinish2(int numCourses, int[][] prerequisites) {
        List<Integer>[] graph = new List[numCourses]; // contains its all prerequisites
        for (int i = 0; i < numCourses; i++) graph[i] = new ArrayList<>();
        int[] indegree = new int[numCourses];
        for (int[] p : prerequisites) {
            graph[p[1]].add(p[0]);
            indegree[p[0]]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        int count = 0; // visited nodes
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
                count++;
            }
        }

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (int next : graph[cur]) {
                indegree[next]--;
                if (indegree[next] == 0) {
                    queue.offer(next);
                    count++;
                }
            }
        }
        return count == numCourses;
    }
}
// @lc code=end

