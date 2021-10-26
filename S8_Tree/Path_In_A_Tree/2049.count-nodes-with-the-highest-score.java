import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

/*
 * @lc app=leetcode id=2049 lang=java
 *
 * [2049] Count Nodes With the Highest Score
 */

// @lc code=start
class Solution {
    // time = O(n), space = O(n)
    private long max = 0, res = 0;
    public int countHighestScoreNodes(int[] parents) {
        int n = parents.length;
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.putIfAbsent(parents[i], new ArrayList<>());
            map.get(parents[i]).add(i);
        }

        dfs(0, n, map);
        return (int) res;
    }

    private int dfs(int node, int n, HashMap<Integer, List<Integer>> map) {
        int sum = 1;
        long score = 1;

        for (int child : map.getOrDefault(node, new ArrayList<>())) {
            int count = dfs(child, n, map);
            sum += count;
            score *= count;
        }
        if (n - sum > 0) score *= n - sum;
        if (score > max) {
            max = score;
            res = 1;
        } else if (score == max) res++;
        return sum;
    }
}
// @lc code=end

