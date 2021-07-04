package S9_BitNumberMath;
import java.util.*;

/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: ContainsDuplicate
 * Creater: Silentwings
 * Date: Mar, 2020
 * Description: 217. Contains Duplicate
 */
public class LC217_ContainsDuplicate {
    /**
     * Given an array of integers, find if the array contains any duplicates.
     *
     * Your function should return true if any value appears at least twice in the array,
     * and it should return false if every element is distinct.
     *
     * Example 1:
     *
     * Input: [1,2,3,1]
     * Output: true
     * Example 2:
     *
     * Input: [1,2,3,4]
     * Output: false
     * Example 3:
     *
     * Input: [1,1,1,3,3,4,3,2,4,2]
     * Output: true
     * @param nums
     * @return
     */
    public boolean containsDuplicate(int[] nums) {
        // corner case
        if (nums == null || nums.length < 2) return false;
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (set.contains(num)) return true;
            set.add(num);
        }
        return false;
    }
}
