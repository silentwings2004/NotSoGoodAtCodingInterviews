import java.util.TreeMap;

/*
 * @lc app=leetcode id=1724 lang=java
 *
 * [1724] Checking Existence of Edge Length Limited Paths II
 */

// @lc code=start
class DistanceLimitedPathsExist {
    // time = O(nlogn), space = O(n)  
    TreeMap<Integer, int[]> map;
    int[] parent;
    public DistanceLimitedPathsExist(int n, int[][] edgeList) {
        map = new TreeMap<>();
        parent = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;

        Arrays.sort(edgeList, (o1, o2) -> o1[2] - o2[2]);
        for (int[] edge : edgeList) {
            int a = edge[0], b = edge[1], c = edge[2];
            if (findParent(a) != findParent(b)) union(a, b);
            map.put(c, parent.clone());
        }
    }
    
    public boolean query(int p, int q, int limit) {
        Integer lk = map.lowerKey(limit);
        if (lk == null) return false;
        parent = map.get(lk);
        return findParent(p) == findParent(q);
    }

    private int findParent(int x) {
        if (parent[x] != x) parent[x] = findParent(parent[x]);
        return parent[x];
    }

    private void union(int x, int y) {
        x = findParent(x);
        y = findParent(y);
        if (x < y) parent[y] = x;
        else parent[x] = y;
    }
}

/**
 * Your DistanceLimitedPathsExist object will be instantiated and called as such:
 * DistanceLimitedPathsExist obj = new DistanceLimitedPathsExist(n, edgeList);
 * boolean param_1 = obj.query(p,q,limit);
 */
// @lc code=end

