/*
 * @lc app=leetcode id=1766 lang=java
 *
 * [1766] Tree of Coprimes
 */

// @lc code=start
class Solution {
    // time = O(n), space = O(n)
    public int[] getCoprimes(int[] nums, int[][] edges) {
        int n = nums.length;
        List<Integer>[] map = new List[51];
        for (int i = 0; i < 51; i++) map[i] = new ArrayList<>();
        List<Integer>[] graph = new List[n]; // graph: {idx -> idx}
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }

        int[] res = new int[n];
        boolean[] visited = new boolean[n];
        visited[0] = true;
        
        dfs(nums, 0, 0, map, graph, new ArrayList<>(), res, visited);
        return res;
    }

    private void dfs(int[] nums, int idx, int depth, List<Integer>[] map, List<Integer>[] graph, List<Integer> path, int[] res, boolean[] visited) {
        int maxDepth = -1;
        for (int d = 1; d <= 50; d++) {
            if (map[d].size() > 0 && gcd(d, nums[idx]) == 1) {
                maxDepth = Math.max(maxDepth, map[d].get(map[d].size() - 1));
            }
        }
        res[idx] = (maxDepth == -1 ? -1 : path.get(maxDepth)); // path[i]: the i-th node idx in the current dfs branch

        // save
        path.add(idx); // path: {depth -> idx}
        map[nums[idx]].add(depth); // map: {val -> depth}

        for (int next : graph[idx]) {
            if (visited[next]) continue;
            visited[next] = true;
            dfs(nums, next, depth + 1, map, graph, path, res, visited);
            visited[next] = false;
        }

        // setback
        path.remove(path.size() - 1);
        map[nums[idx]].remove(map[nums[idx]].size() - 1);
    }

    private int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }
}
// @lc code=end

