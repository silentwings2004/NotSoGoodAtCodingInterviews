package S8_BFSDFS;
import java.util.*;

/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: CombinationSumII
 * Creater: Silentwings
 * Date: Feb, 2020
 * Description: 40. Combination Sum II
 */
public class LC40_CombinationSumII {
    /**
     * Given a collection of candidate numbers (candidates) and a target number (target),
     * find all unique combinations in candidates where the candidate numbers sums to target.
     *
     * Each number in candidates may only be used once in the combination.
     *
     * Note:
     *
     * All numbers (including target) will be positive integers.
     * The solution set must not contain duplicate combinations.
     * Example 1:
     *
     * Input: candidates = [10,1,2,7,6,1,5], target = 8,
     * A solution set is:
     * [
     *   [1, 7],
     *   [1, 2, 5],
     *   [2, 6],
     *   [1, 1, 6]
     * ]
     * Example 2:
     *
     * Input: candidates = [2,5,2,1,2], target = 5,
     * A solution set is:
     * [
     *   [1,2,2],
     *   [5]
     * ]
     * @param candidates
     * @param target
     * @return
     */
    // S1: use hashmap to deduplicate
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        // corner case
        if (candidates == null || candidates.length == 0) return res;
        helper(res, new ArrayList<>(), candidates, target, 0);
        List<List<Integer>> ans = removeDup(res);
        return ans;
    }

    private List<List<Integer>> removeDup(List<List<Integer>> res) {
        Map<List<Integer>, Integer> map = new HashMap<>();
        for (int i = 0; i < res.size(); i++) {
            Collections.sort(res.get(i));
            if (!map.containsKey((res.get(i)))) map.put(res.get(i), i);
        }
        List<List<Integer>> ans = new ArrayList<>();
        for (List<Integer> list : map.keySet()) ans.add(list);
        return ans;
    }

    private void helper(List<List<Integer>> res, List<Integer> list, int[] nums, int target, int start) {
        if (target < 0) return;
        if (target == 0) {
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = start; i < nums.length; i++) {
            list.add(nums[i]);
            helper(res, list, nums, target - nums[i], i + 1);
            list.remove(list.size() - 1);
        }
    }

    // S2
    public List<List<Integer>> combinationSum22(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (candidates == null || candidates.length == 0) return res;
        Arrays.sort(candidates);
        helper2(res, new ArrayList<>(), candidates, target, 0);
        return res;
    }

    private void helper2(List<List<Integer>> res, List<Integer> list, int[] candidates, int target, int start) {
        if (target < 0) return;
        if (target == 0) {
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            if (i != start && candidates[i] == candidates[i - 1]) continue; // 在同层(同一个start开始的遍历)出现重复, 只取第一个
            list.add(candidates[i]);
            helper(res, list, candidates, target - candidates[i], i + 1);
            list.remove(list.size() - 1);
        }
    }
}
