package S1_BS;
import java.util.*;
/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: BinarySearch
 * Creater: Silentwings
 * Date: Apr, 2020
 * Description: 704. Binary Search
 */
public class LC704_BinarySearch {
    /**
     * Given a sorted (in ascending order) integer array nums of n elements and a target value, write a function to
     * search target in nums. If target exists, then return its index, otherwise return -1.
     *
     *
     * Example 1:
     *
     * Input: nums = [-1,0,3,5,9,12], target = 9
     * Output: 4
     * Explanation: 9 exists in nums and its index is 4
     *
     * Example 2:
     *
     * Input: nums = [-1,0,3,5,9,12], target = 2
     * Output: -1
     * Explanation: 2 does not exist in nums so return -1
     *
     *
     * Note:
     *
     * You may assume that all elements in nums are unique.
     * n will be in the range [1, 10000].
     * The value of each element in nums will be in the range [-9999, 9999].
     * @param nums
     * @param target
     * @return
     */
    // S1：左右越过
    // time = O(logn), space = O(1)
    public int search(int[] nums, int target) {
        // corner case
        if (nums == null || nums.length == 0) return -1;

        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) return mid;
            if (nums[mid] < target) left = mid + 1;
            else right = mid - 1;
        }
        return -1;
    }

    // S2
    // time = O（logn), space = O(1)
    public int search2(int[] nums, int target) {
        // corner case
        if (nums == null || nums.length == 0) return -1;

        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) return mid;
            if (nums[mid] < target) left = mid + 1;
            else right = mid;
        }
        return nums[left] == target ? left : -1;
    }

    // S3: 左右相邻
    public int search3(int[] nums, int target) {
        // corner case
        if (nums == null || nums.length == 0) return -1;

        int left = 0, right = nums.length - 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) return mid;
            if (nums[mid] < target) left = mid;
            else right = mid;
        }
        if (nums[left] == target) return left;
        if (nums[right] == target) return right;
        return -1;
    }
}
