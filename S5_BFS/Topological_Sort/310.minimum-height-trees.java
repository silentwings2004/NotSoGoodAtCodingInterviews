/*
 * @lc app=leetcode id=310 lang=java
 *
 * [310] Minimum Height Trees
 */

// @lc code=start
class Solution {
    // time = O(n), space = O(n)
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer> res = new ArrayList<>();
        HashSet<Integer>[] graph = new HashSet[n];
        for (int i = 0; i < n; i++) graph[i] = new HashSet<>();
        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }

        Queue<Integer> queue = new LinkedList<>();
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            if (graph[i].size() == 1) queue.offer(i);
            set.add(i);
        }

        while (!queue.isEmpty()) {
            int size = queue.size();
            res = new ArrayList<>();
            while (size-- > 0) {
                int cur = queue.poll();
                res.add(cur);
                set.remove(cur);
                for (int next : graph[cur]) {
                    graph[next].remove(cur);
                    if (graph[next].size() == 1) queue.offer(next);
                }
            }
        }
        res.addAll(set);
        return res;
    }
}
// @lc code=end

