/*
 * @ln app=lintcode id=90 lang=java
 *
 * [90] K Sum II
 */

// @ln code=start
import java.util.*;
class Solution {
    // time = O(n^k), space = O(k)
    public List<List<Integer>> kSumII(int[] A, int k, int target) {
        List<List<Integer>> res = new ArrayList<>();
        // corner case
        if (A == null || A.length == 0) return res;

        dfs(A, 0, k, 0, target, new ArrayList<>(), res);
        return res;
    }

    private void dfs(int[] A, int idx, int k, int sum, int target, List<Integer> path, List<List<Integer>> res) {
        // base case - success
        if (sum == target && path.size() == k) {
            res.add(new ArrayList<>(path));
        }

        // base case - fail
        if (idx == A.length || sum == target || path.size() == k) return;
            
        for (int i = idx; i < A.length; i++) {
            path.add(A[i]);
            dfs(A, i + 1, k, sum + A[i], target, path, res);
            path.remove(path.size() - 1);
        }
    } 
}
// @ln code=end
