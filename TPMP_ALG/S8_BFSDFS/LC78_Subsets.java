package S8_BFSDFS;
import java.util.*;

/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: Subsets
 * Creater: Silentwings
 * Date: Feb, 2020
 * Description: 78. Subsets
 */
public class LC78_Subsets {
    /**
     * Given a set of distinct integers, nums, return all possible subsets (the power set).
     *
     * Note: The solution set must not contain duplicate subsets.
     *
     * Example:
     *
     * Input: nums = [1,2,3]
     * Output:
     * [
     *   [3],
     *   [1],
     *   [2],
     *   [1,2,3],
     *   [1,3],
     *   [2,3],
     *   [1,2],
     *   []
     * ]
     * @param nums
     * @return
     */
    // S1: DFS 1
    // time = O(n * 2^n), space = O(n * 2^n)
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        // corner case
        if (nums == null || nums.length == 0) return res;

        dfs(nums, 0, new ArrayList<>(), res);
        return res;
    }

    private void dfs(int[] nums, int idx, List<Integer> path, List<List<Integer>> res) {
        res.add(new ArrayList<>(path));

        for (int i = idx; i < nums.length; i++) {
            path.add(nums[i]);
            dfs(nums, i + 1, path, res);
            path.remove(path.size() - 1);
        }
    }

    // S2: DFS 2
    // time = O(n * 2^n), space = O(n * 2^n)
    public List<List<Integer>> subsets2(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        // corner case
        if (nums == null || nums.length == 0) return res;

        dfs2(nums, 0, new ArrayList<>(), res);
        return res;
    }

    private void dfs2(int[] nums, int idx, List<Integer> path, List<List<Integer>> res) {
        // base case - success
        if (idx == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }

        // case 1: take
        path.add(nums[idx]);
        dfs2(nums, idx + 1, path, res);
        path.remove(path.size() - 1);

        // case 2: not take
        dfs2(nums, idx + 1, path, res);
    }
}
