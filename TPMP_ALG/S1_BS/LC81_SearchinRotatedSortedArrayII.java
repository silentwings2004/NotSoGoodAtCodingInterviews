package S1_BS;
import java.util.*;
/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: SearchinRotatedSortedArrayII
 * Creater: Silentwings
 * Date: Aug, 2020
 * Description: 81. Search in Rotated Sorted Array II
 */
public class LC81_SearchinRotatedSortedArrayII {
    /**
     * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
     *
     * (i.e., [0,0,1,2,2,5,6] might become [2,5,6,0,0,1,2]).
     *
     * You are given a target value to search. If found in the array return true, otherwise return false.
     *
     * Example 1:
     *
     * Input: nums = [2,5,6,0,0,1,2], target = 0
     * Output: true
     *
     * Example 2:
     *
     * Input: nums = [2,5,6,0,0,1,2], target = 3
     * Output: false
     * Follow up:
     *
     * This is a follow up problem to Search in Rotated Sorted Array, where nums may contain duplicates.
     * Would this affect the run-time complexity? How and why?
     * @param nums
     * @param target
     * @return
     */
    // S1
    // time = O(logn), space = O(1)
    public boolean search(int[] nums, int target) {
        // corner case
        if (nums == null || nums.length == 0) return false;

        int left = 0, right = nums.length - 1;

        // 比上题就多了这一行，去掉队尾同队首所有相同的元素，但要注意保证不能减到一个都没有了！
        while (right >= 1 && nums[right] == nums[0]) right--; // right at least need to be nums[0] => right >= 1

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) return true;

                // target and mid at the same side
            else if (target >= nums[0] && nums[mid] >= nums[0] || target < nums[0] && nums[mid] < nums[0]) {
                if (nums[mid] < target) left = mid + 1;
                else right = mid - 1;
            } else if (target >= nums[0]) { // target at left, mid at right
                right = mid - 1;
            } else { // target at right, mid at left
                left = mid + 1;
            }
        }
        return nums[left] == target ? true : false;
    }
    // S2:
    // time = O(n), space = O(1)
    public boolean search2(int[] nums, int target) {
        // corner case
        if (nums == null || nums.length == 0) return false;

        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) return true;
            // 如果left, mid, right三值相等，则无法判断哪边有序，这时只能让left++或者right--再看
            // 因此最坏情况下的时间复杂度为O(n)
            if (nums[left] == nums[mid] && nums[mid] == nums[right]) left++; // right--也可以
            else if (nums[left] <= nums[mid]) { // mid在左半边
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else { // mid在右半边
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                }
                else {
                    right = mid - 1;
                }
            }
        }
        return false;
    }
}
