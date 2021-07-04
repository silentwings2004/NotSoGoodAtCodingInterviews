package S1_BS;
import java.util.*;
/**
 * Project Name: Lintcode
 * Package Name: lintcode
 * File Name: FindMinimuminRotatedSortedArrayII
 * Creater: Silentwings
 * Date: Aug, 2020
 * Description: 154. Find Minimum in Rotated Sorted Array II
 */
public class LC154_FindMinimuminRotatedSortedArrayII {
    /**
     * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
     *
     * (i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).
     *
     * Find the minimum element.
     *
     * The array may contain duplicates.
     *
     * Example 1:
     *
     * Input: [1,3,5]
     * Output: 1
     *
     * Example 2:
     *
     * Input: [2,2,2,0,1]
     * Output: 0
     *
     * [2, 3, 1, 1, 1]
     * Note:
     *
     * This is a follow up problem to Find Minimum in Rotated Sorted Array.
     * Would allow duplicates affect the run-time complexity? How and why?
     * @param nums
     * @return
     */
    // S1
    // time = O(n), space = O(1)
    public int findMin(int[] nums) {
        // corner case
        if (nums == null || nums.length == 0) return -1;

        int left = 0, right = nums.length - 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[right]) left = mid; // rotate在右边
            else if (nums[mid] < nums[right]) right = mid; // rotate在左边
            else right--; // 缩小范围
        }
        return Math.min(nums[left], nums[right]);
    }

    // S2
    // time = O(n), space = O(1)
    public int findMin2(int[] nums) {
        // corner case
        if (nums == null || nums.length == 0) return -1;

        int n = nums.length;
        int left = 0, right = n - 1;
        // 同LC81，只比上题多这一行
        while (right >= 1 && nums[right] == nums[0]) right--;

        if (nums[left] < nums[right]) return nums[left];

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] >= nums[0]) left = mid + 1; // 只跟左端点比,最终一定有收敛解！
            else right = mid;
        }
        return nums[left];
    }
}
