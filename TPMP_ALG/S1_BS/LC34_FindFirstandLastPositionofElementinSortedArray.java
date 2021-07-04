package S1_BS;
import java.util.*;
/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: FindFirstandLastPositionofElementinSortedArray
 * Creater: Silentwings
 * Date: Apr, 2020
 * Description: 34. Find First and Last Position of Element in Sorted Array
 */
public class LC34_FindFirstandLastPositionofElementinSortedArray {
    /**
     * Given an array of integers nums sorted in ascending order, find the starting and ending position of a given
     * target value.
     *
     * Your algorithm's runtime complexity must be in the order of O(log n).
     *
     * If the target is not found in the array, return [-1, -1].
     *
     * Example 1:
     *
     * Input: nums = [5,7,7,8,8,10], target = 8
     * Output: [3,4]
     *
     * Example 2:
     *
     * Input: nums = [5,7,7,8,8,10], target = 6
     * Output: [-1,-1]
     *
     *
     * Constraints:
     *
     * 0 <= nums.length <= 10^5
     * -10^9 <= nums[i] <= 10^9
     * nums is a non decreasing array.
     * -10^9 <= target <= 10^9
     * @param nums
     * @param target
     * @return
     */
    // time = O(logn), space = O(1)
    public int[] searchRange(int[] nums, int target) {
        // corner case
        if (nums == null || nums.length == 0 || target < nums[0] || target > nums[nums.length - 1]) {
            return new int[]{-1, -1};
        }

        int start = findFirstPos(nums, 0, nums.length - 1, target);
        int end = findLastPos(nums, 0, nums.length - 1, target);
        return new int[]{start, end};
    }

    private int findFirstPos(int[] nums, int start, int end, int target) {
        // corner case
        if (nums == null || nums.length == 0) return -1;

        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] < target) start = mid;
            else end = mid;
        }
        if (nums[start] == target) return start;
        if (nums[end] == target) return end;
        return -1;
    }

    private int findLastPos(int[] nums, int start, int end, int target) {
        // corner case
        if (nums == null || nums.length == 0) return -1;

        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] <= target) start = mid;
            else end = mid;
        }
        if (nums[end] == target) return end;
        if (nums[start] == target) return start;
        return -1;
    }

    // time = O(logn), space = O(1)
    public int[] searchRange2(int[] nums, int target) {
        // corner case
        if (nums == null || nums.length == 0) return new int[]{-1, -1};

        int n = nums.length;
        int[] res = new int[2];

        // find first pos
        int left = 0, right = n - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) left = mid + 1;
            else right = mid;
        }
        res[0] = nums[left] == target ? left : -1;

        // find last pos
        left = 0;
        right = n - 1;
        while (left < right) {
            int mid = left + (right - left) / 2 + 1;
            if (nums[mid] > target) right = mid - 1;
            else left = mid;
        }
        res[1] = nums[left] == target ? left : -1;

        return res;
    }
}
