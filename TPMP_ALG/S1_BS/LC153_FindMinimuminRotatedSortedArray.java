package S1_BS;
import java.util.*;
/**
 * Project Name: Lintcode
 * Package Name: lintcode
 * File Name: FindMinimuminRotatedSortedArray
 * Creater: Silentwings
 * Date: Aug, 2020
 * Description: 153. Find Minimum in Rotated Sorted Array
 */
public class LC153_FindMinimuminRotatedSortedArray {
    /**
     * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
     *
     * (i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).
     *
     * Find the minimum element.
     *
     * You may assume no duplicate exists in the array.
     *
     * Example 1:
     *
     * Input: [3,4,5,1,2]
     * Output: 1
     *
     * Example 2:
     *
     * Input: [4,5,6,7,0,1,2]
     *
     * [1, 2, 3, 4, 5]
     * Output: 0
     * @param nums
     * @return
     */
    // time = O(logn), space = O(1)
    public int findMin(int[] nums) {
        // corner case
        if (nums == null || nums.length == 0) return -1;

        int left = 0, right = nums.length - 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[right]) left = mid;
            else right = mid;
        }
        return Math.min(nums[left], nums[right]);
    }

    // S2
    // time = O(logn), space = O(1)
    public int findMin2(int[] nums) {
        // corner case
        if (nums == null || nums.length == 0) return -1;

        int n = nums.length;
        int left = 0, right = n - 1;
        if (nums[left] < nums[right]) return nums[left]; // 单调序列，直接左端点就是最小值！

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] >= nums[0]) left = mid + 1; // 只跟左端点比,最终一定有收敛解！
            else right = mid;
        }
        return nums[left];
    }
}
