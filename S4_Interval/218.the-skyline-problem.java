import java.util.TreeMap;
import java.util.TreeSet;

/*
 * @lc app=leetcode id=218 lang=java
 *
 * [218] The Skyline Problem
 */

// @lc code=start
class Solution {
    // time = O(nlogn), space = O(n)
    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<List<Integer>> res = new ArrayList<>();

        List<int[]> edges = new ArrayList<>();
        for (int[] b : buildings) {
            edges.add(new int[]{b[0], -b[2]});
            edges.add(new int[]{b[1], b[2]});
        }

        Collections.sort(edges, (o1, o2) -> o1[0]!= o2[0] ? o1[0] - o2[0] : o1[1] - o2[1]);

        TreeMap<Integer, Integer> map = new TreeMap<>();
        map.put(0, 1);
        int prev = 0;

        for (int[] edge : edges) {
            if (edge[1] < 0) {
                map.put(-edge[1], map.getOrDefault(-edge[1], 0) + 1);
            } else {
                map.put(edge[1], map.get(edge[1]) - 1);
                if (map.get(edge[1]) == 0) map.remove(edge[1]);
            }
            int cur = map.firstKey();
            if (cur != prev) {
                res.add(Arrays.asList(edge[0], cur));
                prev = cur;
            }
        }
        return res;
    }
}
// @lc code=end

