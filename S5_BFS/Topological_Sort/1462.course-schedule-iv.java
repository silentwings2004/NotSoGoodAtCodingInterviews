import java.util.Queue;

/*
 * @lc app=leetcode id=1462 lang=java
 *
 * [1462] Course Schedule IV
 */

// @lc code=start
class Solution {
    // S1: Topological Sort
    // time = O(V + E), space = O(V + E)
    public List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {
        List<Integer>[] graph = new List[numCourses];
        HashSet<Integer>[] preSet = new HashSet[numCourses];
        for (int i = 0; i < numCourses; i++) {
            graph[i] = new ArrayList<>();
            preSet[i] = new HashSet<>();
        }

        int[] indegree = new int[numCourses];
        for (int[] p : prerequisites) {
            graph[p[0]].add(p[1]);
            indegree[p[1]]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            preSet[i].add(i);
            if (indegree[i] == 0) queue.offer(i);
        }

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (int next : graph[cur]) {
                for (int x : preSet[cur]) {
                    preSet[next].add(x);
                }
                indegree[next]--;
                if (indegree[next] == 0) queue.offer(next);
            }
        }

        List<Boolean> res = new ArrayList<>();
        for (int[] q : queries) {
            res.add(preSet[q[1]].contains(q[0]));
        }
        return res;
    }

    // S2: Floyd
    // time = O(n^3), space = O(n^2)
    public List<Boolean> checkIfPrerequisite2(int numCourses, int[][] prerequisites, int[][] queries) {
        boolean[][] connected = new boolean[numCourses][numCourses];
        for (int[] p : prerequisites) {
            connected[p[0]][p[1]] = true;
        }

        for (int k = 0; k < numCourses; k++) {
            for (int i = 0; i < numCourses; i++) {
                for (int j = 0; j < numCourses; j++) {
                    connected[i][j] = connected[i][j] || connected[i][k] && connected[k][j];
                }
            }
        }

        List<Boolean> res = new ArrayList<>();
        for (int[] q : queries) res.add(connected[q[0]][q[1]]);
        return res;
    }
}
// @lc code=end

