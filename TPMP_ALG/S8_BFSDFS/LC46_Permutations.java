package S8_BFSDFS;
import java.util.*;

/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: Permutations
 * Creater: Silentwings
 * Date: Feb, 2020
 * Description: 46. Permutations
 */
public class LC46_Permutations {
    /**
     * Given a collection of distinct integers, return all possible permutations.
     *
     * Example:
     *
     * Input: [1,2,3]
     * Output:
     * [
     *   [1,2,3],
     *   [1,3,2],
     *   [2,1,3],
     *   [2,3,1],
     *   [3,1,2],
     *   [3,2,1]
     * ]
     * @param nums
     * @return
     */
    // S1: use HashMap for de-duplication time: O(n!),   space: O(n);
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
//        if (nums == null || nums.length == 0) return res;  // if nums = []，return res --> [], 而expected答案是[[]]
        if (nums == null) return res;
        if (nums.length == 0) {    // 所以必须在nums.length == 0的case下加入一个长度为0的list来return，这样答案就是[[]]
            res.add(new ArrayList<>(0));
            return res;
        }
        helper(res, new ArrayList<>(), nums);
        return res;
    }

    private void helper(List<List<Integer>> res, List<Integer> list, int[] nums) {
        if (list.size() == nums.length) {
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (list.contains(nums[i])) continue;
            list.add(nums[i]);
            helper(res, list, nums);
            list.remove(list.size() - 1);
        }
    }

    // S2: time: O(n! * n), space: O(n)
    public List<List<Integer>> permute2(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
//        if (nums == null || nums.length == 0) return res;
        if (nums == null) return res;
        if (nums.length == 0) {
            res.add(new ArrayList<>(0));
            return res;
        }
        helper2(nums, 0, res);
        return res;
    }

    private void helper2(int[] nums, int index, List<List<Integer>> res) {
        if (index == nums.length - 1) {
            List<Integer> list = new ArrayList<>();
            for (int num : nums) {
                list.add(num);
            }
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = index; i < nums.length; i++) {
            swap(nums, index, i);
            helper2(nums, index + 1, res);
            swap(nums, index, i);
        }
    }

    private void swap(int[] nums, int index, int i) {
        int count = nums[index];
        nums[index] = nums[i];
        nums[i] = count;
    }
}
