package S10_ArrayString;
import java.util.*;
/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: PermutationsII
 * Creater: Silentwings
 * Date: Mar, 2020
 * Description: 47. Permutations II
 */
public class LC47_PermutationsII {
    /**
     * Given a collection of numbers that might contain duplicates, return all possible unique permutations.
     *
     * Example:
     *
     * Input: [1,1,2]
     * Output:
     * [
     *   [1,1,2],
     *   [1,2,1],
     *   [2,1,1]
     * ]
     * @param nums
     * @return
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        // corner case
        if (nums == null || nums.length == 0) return res;
        permutation(nums, res, 0);
        return res;
    }

    private void permutation(int[] nums, List<List<Integer>> res, int index) {
        if (index == nums.length) {
            List<Integer> list = arrayToList(nums);
            res.add(list);
            return;
        }
        HashSet<Integer> set = new HashSet<>();
        for (int i = index; i < nums.length; i++) {
            if (set.add(nums[i])) {
                swap(nums, i, index);
                permutation(nums, res, index + 1);
                swap(nums, index, i);
            }
        }
    }

    private List<Integer> arrayToList(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for (int n : nums) list.add(n);
        return list;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
