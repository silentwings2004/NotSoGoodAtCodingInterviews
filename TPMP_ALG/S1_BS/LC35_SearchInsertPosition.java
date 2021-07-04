package S1_BS;
import java.util.*;
/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: SearchInsertPosition
 * Creater: Silentwings
 * Date: Aug, 2020
 * Description: 35. Search Insert Position
 */
public class LC35_SearchInsertPosition {
    /**
     * Given a sorted array and a target value, return the index if the target is found. If not, return the index where
     * it would be if it were inserted in order.
     *
     * You may assume no duplicates in the array.
     *
     * Example 1:
     *
     * Input: [1,3,5,6], 5
     *
     * Output: 2
     * Example 2:
     *
     * Input: [1,3,5,6], 2
     * Output: 1
     *
     * Example 3:
     *
     * Input: [1,3,5,6], 7
     * Output: 4
     *
     * Example 4:
     *
     * Input: [1,3,5,6], 0
     * Output: 0
     * @param nums
     * @param target
     * @return
     */
    // time = O（logn), space = O(1)
    public int searchInsert(int[] nums, int target) {     // find the upperbound
        // corner case
        if (nums == null || nums.length == 0) return 0;

        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2 + 1;
            if (nums[mid] <= target) left = mid;
            else right = mid - 1;
        }
        return nums[left] < target ? left + 1 : left;
    }
}
