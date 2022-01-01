/*
 * @lc app=leetcode id=1489 lang=java
 *
 * [1489] Find Critical and Pseudo-Critical Edges in Minimum Spanning Tree
 */

// @lc code=start
class Solution {
    // time = O(m^2 * a(n)), space = O(m + n)
    public List<List<Integer>> findCriticalAndPseudoCriticalEdges(int n, int[][] edges) {
        List<List<Integer>> res = new ArrayList<>();

        int m = edges.length;
        List<int[]> edge = new LinkedList<>();
        for (int i = 0; i < m; i++) edge.add(new int[]{edges[i][0], edges[i][1], edges[i][2], i});

        Collections.sort(edge, (o1, o2) -> o1[2] - o2[2]);
        
        HashSet<Integer> set1 = new HashSet<>();
        HashSet<Integer> set2 = new HashSet<>();

        // find critical edges
        int minMST = helper(edge, -1, n);
        for (int i = 0; i < m; i++) {
            int mst = helper(edge, i, n);
            if (mst > minMST) set1.add(edge.get(i)[3]);
        }

        // find pseudo-cirtical edges
        for (int i = 0; i < m; i++) {
            int[] x = edge.get(i);
            if (set1.contains(x[3])) continue;
            edge.add(0, x);
            int mst = helper(edge, -1, n);
            if (mst == minMST) set2.add(x[3]);
            edge.remove(0); // 注意：要记得加在头部之后要recover删除！！！
        }
        res.add(new ArrayList<>(set1));
        res.add(new ArrayList<>(set2));
        return res;
    }

    int[] parent;
    private int helper(List<int[]> edge, int k, int n) {
        parent = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;

        int res = 0;
        for (int i = 0; i < edge.size(); i++) {
            if (i == k) continue;
            int[] x = edge.get(i);
            int a = x[0], b = x[1], weight = x[2];
            if (findParent(a) != findParent(b)) {
                union(a, b);
                res += weight;
            }
        }
        
        // check if the MST is real
        for (int i = 0; i < n; i++) {
            if (findParent(i) != findParent(0)) {
                res = Integer.MAX_VALUE;
                break;
            }
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

