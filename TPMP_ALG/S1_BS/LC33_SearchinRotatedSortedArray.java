package S1_BS;
import java.util.*;
/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: SearchinRotatedSortedArray
 * Creater: Silentwings
 * Date: Aug, 2020
 * Description: 33. Search in Rotated Sorted Array
 */
public class LC33_SearchinRotatedSortedArray {
    /**
     * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
     *
     * (i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).
     *
     * You are given a target value to search. If found in the array return its index, otherwise return -1.
     *
     * You may assume no duplicate exists in the array.
     *
     * Your algorithm's runtime complexity must be in the order of O(log n).
     *
     * Example 1:
     *
     * Input: nums = [4,5,6,7,0,1,2], target = 0
     * Output: 4
     *
     * Example 2:
     *
     * Input: nums = [4,5,6,7,0,1,2], target = 3
     * Output: -1
     * @param nums
     * @param target
     * @return
     */
    // time = O(logn), space = O(1)
    public int search(int[] nums, int target) {
        // corner case
        if (nums == null || nums.length == 0) return -1;

        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) return mid;
            // target and mid at the same side
            if (target >= nums[0] && nums[mid] >= nums[0] || target < nums[0] && nums[mid] < nums[0]) {
                if (nums[mid] < target) left = mid + 1;
                else right = mid - 1;
            } else if (target >= nums[0]) { // target at left, mid at right
                right = mid - 1;
            } else { // target at right, mid at left
                left = mid + 1;
            }
        }
        return nums[left] == target ? left : -1;
    }
}
