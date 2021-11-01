/*
 * @lc app=leetcode id=40 lang=java
 *
 * [40] Combination Sum II
 */

// @lc code=start
class Solution {
    // time = O(2^n), space = O(n)
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates);
        dfs(candidates, target, 0, 0, new ArrayList<>(), res);
        return res;
    }

    private void dfs(int[] candidates, int target, int idx, int sum, List<Integer> path, List<List<Integer>> res) {
        // base case - success
        if (sum == target) {
            res.add(new ArrayList<>(path));
            return;
        }

        // base case - fail
        if (idx == candidates.length || sum > target) return;

        for (int i = idx; i < candidates.length; i++) {
            if (i > idx && candidates[i] == candidates[i - 1]) continue; // deduplicate
            path.add(candidates[i]);
            dfs(candidates, target, i + 1, sum + candidates[i], path, res);
            path.remove(path.size() - 1); // setback
        }
    }
}
// @lc code=end

