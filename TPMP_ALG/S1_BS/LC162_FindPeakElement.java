package S1_BS;
import java.util.*;
/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: FindPeakElement
 * Creater: Silentwings
 * Date: Aug, 2020
 * Description: 162. Find Peak Element
 */
public class LC162_FindPeakElement {
    /**
     * A peak element is an element that is greater than its neighbors.
     *
     * Given an input array nums, where nums[i] ≠ nums[i+1], find a peak element and return its index.
     *
     * The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.
     *
     * You may imagine that nums[-1] = nums[n] = -∞.
     *
     * Example 1:
     *
     * Input: nums = [1,2,3,1]
     * Output: 2
     * Explanation: 3 is a peak element and your function should return the index number 2.
     *
     * Example 2:
     *
     * Input: nums = [1,2,1,3,5,6,4]
     * Output: 1 or 5
     * Explanation: Your function can return either index number 1 where the peak element is 2,
     *              or index number 5 where the peak element is 6.
     * Follow up: Your solution should be in logarithmic complexity.
     * @param nums
     * @return
     */
    // time = O(logn), space = O(1)
    public int findPeakElement(int[] nums) {
        // corner case
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return 0; // return the index instead of element itself

        int n = nums.length;
        int left = 0, right = n - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < nums[mid + 1]) left = mid + 1;
            else right = mid;
        }
        return left; // 一定有解，收敛解！
    }
}
