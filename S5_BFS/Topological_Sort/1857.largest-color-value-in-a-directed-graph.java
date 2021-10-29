import java.util.Arrays;
import java.util.Queue;

/*
 * @lc app=leetcode id=1857 lang=java
 *
 * [1857] Largest Color Value in a Directed Graph
 */

// @lc code=start
class Solution {
    // time = O(V + E), space = O(V + E)
    public int largestPathValue(String colors, int[][] edges) {
        int n = colors.length();
        List<Integer>[] graph = new List[100000];
        int[] indegree = new int[100000];
        for (int i = 0; i < 100000; i++) graph[i] = new ArrayList<>();
        for (int[] edge : edges) {
            int a = edge[0], b = edge[1];
            graph[a].add(b);
            indegree[b]++;
        }
        
        HashSet<Character> set = new HashSet<>();
        for (char c : colors.toCharArray()) set.add(c);
        int res = 1;
        for (char ch = 'a'; ch <= 'z'; ch++) {
            if (!set.contains(ch)) continue;
            int ans = helper(graph, indegree, ch - 'a', n, colors);
            if (ans == -1) return -1;
            res = Math.max(res, ans);
        }
        return res;
    }

    private int helper(List<Integer>[] graph, int[] indegree, int k, int n, String colors) {
        int ans = 0, nodes = 0;
        Queue<Integer> queue = new LinkedList<>();
        int[] count = new int[n]; // count[i]: how many color k are there along the path from start to the i-th node
        int[] in = indegree.clone();
        for (int i = 0; i < n; i++) {
            if (in[i] == 0) {
                queue.offer(i);
                nodes++;
                count[i] += (colors.charAt(i) - 'a' == k ? 1 : 0);
            }
        }

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (int next : graph[cur]) {
                count[next] = Math.max(count[next], count[cur] + (colors.charAt(next) - 'a' == k ? 1 : 0));
                ans = Math.max(ans, count[next]); // 最优解肯定在终端节点上
                in[next]--;
                if (in[next] == 0) {
                    queue.offer(next);
                    nodes++;
                }
            }
        }
        return nodes == n ? ans : -1;
    }
}
// @lc code=end

