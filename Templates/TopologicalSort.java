package Templates;
import java.util.*;

public class TopologicalSort {
    public boolean bfs(int numCourses, int[][] prerequisites) {
        int[] indegree = new int[numCourses];
        for (int[] p : prerequisites) {
            indegree[p[0]]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        int count = 0;
        // int k = 0;
        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
                count++;
                // res[k++] = i;
            }
        }

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (int[] p : prerequisites) {
                if (p[1] == cur) {
                    indegree[p[0]]--;
                    if (indegree[p[0]] == 0) {
                        queue.offer(p[0]);
                        count++;
                        // res[k++] = p[0]; // if need to return the path
                    }
                }
            }
        }
        return count == numCourses;
    }

    public boolean dfs(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = buildGraph(numCourses, prerequisites);
        int[] status = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            if (containsCycle(graph, status, i)) return false;
        }
        return true;
    }

    private List<List<Integer>> buildGraph(int n, int[][] courses) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());
        for (int[] c : courses) {
            graph.get(c[1]).add(c[0]);
        }
        return graph;
    }

    private boolean containsCycle(List<List<Integer>> graph, int[] status, int cur) {
        if (status[cur] == 2) return false;
        if (status[cur] == 1) return true;

        status[cur] = 1;
        for (int next : graph.get(cur)) {
            if (containsCycle(graph, status, next)) return true;
        }
        status[cur] = 2;
        // res.add(0, cur); // if need to return the path
        return false;
    }
}
