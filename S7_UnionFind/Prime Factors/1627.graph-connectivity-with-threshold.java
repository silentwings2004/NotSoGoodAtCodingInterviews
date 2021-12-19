/*
 * @lc app=leetcode id=1627 lang=java
 *
 * [1627] Graph Connectivity With Threshold
 */

// @lc code=start
class Solution {
    // time = O(nlogn), space = O(n)
    int[] parent; 
    public List<Boolean> areConnected(int n, int threshold, int[][] queries) {
        parent = new int[n + 1];
        for (int i = 0; i <= n; i++) parent[i] = i;

        boolean[] visited = new boolean[n + 1];
        for (int k = threshold + 1; k <= n; k++) { // enumerate all possible factors
            if (visited[k]) continue;
            for (int x = 2 * k; x <= n; x += k) {
                visited[x] = true;
                if (findParent(x) != findParent(k)) {
                    union(x, k);
                }
            }
        }

        List<Boolean> res = new ArrayList<>();
        for (int[] query : queries) {
            res.add(findParent(query[0]) == findParent(query[1]));
        }
        return res;
    }

    private int findParent(int x) {
        if (x != parent[x]) parent[x] = findParent(parent[x]);
        return parent[x];
    }

    private void union(int x, int y) {
        x = parent[x];
        y = parent[y];
        if (x < y) parent[y] = x;
        else parent[x] = y;
    }
}
// @lc code=end

