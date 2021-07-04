package S8_BFSDFS;
import java.util.*;

/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: CombinationSum
 * Creater: Silentwings
 * Date: Feb, 2020
 * Description: 39. Combination Sum
 */
public class LC39_CombinationSum {
    /**
     * Given a set of candidate numbers (candidates) (without duplicates) and a target number (target),
     * find all unique combinations in candidates where the candidate numbers sums to target.
     *
     * The same repeated number may be chosen from candidates unlimited number of times.
     *
     * Note:
     *
     * All numbers (including target) will be positive integers.
     * The solution set must not contain duplicate combinations.
     * Example 1:
     *
     * Input: candidates = [2,3,6,7], target = 7,
     * A solution set is:
     * [
     *   [7],
     *   [2,2,3]
     * ]
     * Example 2:
     *
     * Input: candidates = [2,3,5], target = 8,
     * A solution set is:
     * [
     *   [2,2,2,2],
     *   [2,3,3],
     *   [3,5]
     * ]
     *
     * time: O(2^n)
     * space: O(n)
     *
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (candidates == null || candidates.length == 0) return res;
        helper(res, new ArrayList<>(), candidates, target, 0);
        return res;
    }

    private void helper(List<List<Integer>> res, List<Integer> list, int[] candidates, int target, int start) {
        if (target < 0) return;
        if (target == 0) {
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            list.add(candidates[i]);
            helper(res, list, candidates, target - candidates[i], i);
            // 因为可以有重复，所以recursion中start永远从i开始
            list.remove(list.size() - 1);
        }
    }
}
