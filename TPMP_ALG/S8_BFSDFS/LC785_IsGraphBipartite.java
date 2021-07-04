package S8_BFSDFS;
import java.util.*;

/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: IsGraphBipartite
 * Creater: Silentwings
 * Date: Feb, 2020
 * Description: 785. Is Graph Bipartite?
 */
public class LC785_IsGraphBipartite {
    /**
     * Given an undirected graph, return true if and only if it is bipartite.
     *
     * Recall that a graph is bipartite if we can split it's set of nodes into two independent subsets A and B
     * such that every edge in the graph has one node in A and another node in B.
     *
     * The graph is given in the following form: graph[i] is a list of indexes j for which the edge between
     * nodes i and j exists.  Each node is an integer between 0 and graph.length - 1.
     * There are no self edges or parallel edges: graph[i] does not contain i, and it doesn't contain any element twice.
     *
     * Example 1:
     * Input: [[1,3], [0,2], [1,3], [0,2]]
     * Output: true
     * Explanation:
     * The graph looks like this:
     * 0----1
     * |    |
     * |    |
     * 3----2
     * We can divide the vertices into two groups: {0, 2} and {1, 3}.
     * Example 2:
     * Input: [[1,2,3], [0,2], [0,1,3], [0,2]]
     * Output: false
     * Explanation:
     * The graph looks like this:
     * 0----1
     * | \  |
     * |  \ |
     * 3----2
     * We cannot find a way to divide the set of nodes into two independent subsets.
     *
     * Bipartite Graph可以被红绿两种颜色染色，且相邻顶点之间不能染上相同的颜色
     * time: O(n + m)  n: 顶点的个数, m: 边数
     * space: O(n)
     * Note:
     *
     * graph will have length in range [1, 100].
     * graph[i] will contain integers in range [0, graph.length - 1].
     * graph[i] will not contain i or duplicate values.
     * The graph is undirected: if any element j is in graph[i], then i will be in graph[j].
     * @param graph
     * @return
     */
    // time = O(V + E), space = O(n)
    public boolean isBipartite(int[][] graph) {
        int[] visited = new int[graph.length];
        for (int i = 0; i < graph.length; i++) {
            if (visited[i] != 0) continue; // 外层for loop主要遍历各个非连通的结点
            Queue<Integer> queue = new LinkedList<>();
            queue.offer(i);   // add index of vertices into queue
            visited[i] = 1;    // paint this visited point with color 1

            while (!queue.isEmpty()) {
                int cur = queue.poll();    // poll out a vertice index
                int curColor = visited[cur];
                int neighborColor = curColor == 1 ? 2 : 1; // Decide neighbor color
                for (int neighbor : graph[cur]) {  // 遍历所有与该结点连通的neighbors
                    if (visited[neighbor] == 0) {  // neighbor没染色就染色
                        visited[neighbor] = neighborColor;
                        queue.offer(neighbor);
                    } else {
                        if (visited[neighbor] != neighborColor) {
                            return false; // neighbor染色的话就check颜色是否一致
                        }
                    }
                }
            }
        }
        return true;
    }
}
