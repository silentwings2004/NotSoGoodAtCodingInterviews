/*
 * @lc app=leetcode id=1579 lang=java
 *
 * [1579] Remove Max Number of Edges to Keep Graph Fully Traversable
 */

// @lc code=start
class Solution {
    // time = O(m * a(n)), space = O(n)  其中 mm 是数组{edges}edges 的长度，a 是阿克曼函数的反函数。
    int[] parent;
    public int maxNumEdgesToRemove(int n, int[][] edges) {
        parent = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;

        List<int[]> edges0 = new ArrayList<>();
        List<int[]> edges1 = new ArrayList<>();
        List<int[]> edges2 = new ArrayList<>();

        for (int[] edge : edges) {
            if (edge[0] == 3) edges0.add(new int[]{edge[1] - 1, edge[2] - 1});
            if (edge[0] == 1) edges1.add(new int[]{edge[1] - 1, edge[2] - 1});
            if (edge[0] == 2) edges2.add(new int[]{edge[1] - 1, edge[2] - 1});
        }

        // check type 3 edges first
        int count0 = 0;
        for (int[] edge : edges0) {
            int a = edge[0], b = edge[1];
            if (findParent(a) != findParent(b)) {
                union(a, b);
                count0++;
            }
        }

        // check type 1 edges
        int count1 = 0;
        int[] copy = parent.clone();
        for (int[] edge : edges1) {
            int a = edge[0], b = edge[1];
            if (findParent(a) != findParent(b)) {
                union(a, b);
                count1++;
            }
        }
        if (count0 + count1 != n - 1) return -1; // not possible for Alice

        // check type 2 edges
        int count2 = 0;
        parent = copy;
        for (int[] edge : edges2) {
            int a = edge[0], b = edge[1];
            if (findParent(a) != findParent(b)) {
                union(a, b);
                count2++;
            }
        }
        if (count0 + count2 != n - 1) return -1; // not possible for Bob

        return edges.length - count0 - count1 - count2;
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

