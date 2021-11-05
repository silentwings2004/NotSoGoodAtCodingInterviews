/*
 * @lc app=leetcode id=90 lang=java
 *
 * [90] Subsets II
 */

// @lc code=start
class Solution {
    // time = O(n * 2^n), space = O(n)
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        dfs(nums, 0, new ArrayList<>(), res);
        return res;
    }

    private void dfs(int[] nums, int idx, List<Integer> path, List<List<Integer>> res) {
        res.add(new ArrayList<>(path));

        for (int i = idx; i < nums.length; i++) {
            if (i > idx && nums[i] == nums[i - 1]) continue;
            path.add(nums[i]);
            dfs(nums, i + 1, path, res);
            path.remove(path.size() - 1);
        }
    } 
}
// @lc code=end

