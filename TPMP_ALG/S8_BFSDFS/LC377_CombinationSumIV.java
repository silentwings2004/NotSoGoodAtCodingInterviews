package S8_BFSDFS;
import java.util.*;

/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: CombinationSumIV
 * Creater: Silentwings
 * Date: Feb, 2020
 * Description: 377. Combination Sum IV
 */
public class LC377_CombinationSumIV {
    /**
     * Given an integer array with all positive numbers and no duplicates,
     * find the number of possible combinations that add up to a positive integer target.
     *
     * Example:
     *
     * nums = [1, 2, 3]
     * target = 4
     *
     * The possible combination ways are:
     * (1, 1, 1, 1)
     * (1, 1, 2)
     * (1, 2, 1)
     * (1, 3)
     * (2, 1, 1)
     * (2, 2)
     * (3, 1)
     *
     * Note that different sequences are counted as different combinations.
     *
     * Therefore the output is 7.
     *
     * 1. DP: res[i] += res[i - num];
     * 2. DFS + Memoization : HashMap<Integer, Integer>
     *
     * Follow up:
     * What if negative numbers are allowed in the given array?
     * How does it change the problem?
     * What limitation we need to add to the question to allow negative numbers?
     * @param nums
     * @param target
     * @return
     */
    // S1: DP: res[i] += res[i - num]   time: O(n * k)   space = O(k)
    public int combinationSum4(int[] nums, int target) {
        int[] res = new int[target + 1];
        res[0] = 1;
        for (int i = 1; i < res.length; i++) {
            for (int num : nums) {
                if (i - num >= 0) {
                    res[i] += res[i - num];
                }
            }
        }
        return res[target];
    }

    // S2: HashMap   time: < O(2^n)   space: O(n)
    public int combinationSum42(int[] nums, int target) {
        if (nums == null || nums.length == 0) return 0;
        Map<Integer, Integer> map = new HashMap<>();
        return helper(nums, target, map);
    }

    private int helper(int[] nums, int target, Map<Integer, Integer> map) {
        if (target < 0) return 0;
        if (target == 0) return 1;
        if (map.containsKey(target)) return map.get(target);

        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            res += helper(nums, target - nums[i], map);
        }
        map.put(target, res);
        return res;
    }

    // S3[Bad]: Backtracking --> Memory Limit Exceeded
    public int combinationSum43(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res.size();
        helper2(res, new ArrayList<>(), nums, target, 0);
        return res.size();
    }

    private void helper2(List<List<Integer>> res, List<Integer> list, int[] nums, int target, int start) {
        if (target < 0) return;
        if (target == 0) {
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = start; i < nums.length; i++) {
            list.add(nums[i]);
            helper2(res, list, nums, target - nums[i], 0);
            list.remove(list.size() - 1);
        }
    }
}
