package S8_BFSDFS;
import java.util.*;

/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: Combinations
 * Creater: Silentwings
 * Date: Feb, 2020
 * Description: 77. Combinations
 */
public class LC77_Combinations {
    /**
     * Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.
     *
     * Example:
     *
     * Input: n = 4, k = 2
     * Output:
     * [
     *   [2,4],
     *   [3,4],
     *   [2,3],
     *   [1,2],
     *   [1,3],
     *   [1,4],
     * ]
     * @param n
     * @param k
     * @return
     */
    // 典型的DFS成pair add + backtracking的解法
    // time = O(k*C(n, k)), space = O(C(n, k))
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        if (n <= 0) return res;
        dfs(n, k, 1, new ArrayList<>(), res);
        return res;
    }

    private void dfs(int n, int k, int idx, List<Integer> path, List<List<Integer>> res) {
        if (path.size() == k) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = idx; i <= n; i++) {
            path.add(i);
            dfs(n, k, i + 1, path, res);
            path.remove(path.size() - 1);
        }
    }
}
