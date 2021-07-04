package S2_Sorting;
import java.util.*;
/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: SortColors
 * Creater: Silentwings
 * Date: Aug, 2020
 * Description: 75. Sort Colors
 */
public class LC75_SortColors {
    /**
     * Given an array with n objects colored red, white or blue, sort them in-place so that objects of the same color
     * are adjacent, with the colors in the order red, white and blue.
     *
     * Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.
     *
     * Note: You are not suppose to use the library's sort function for this problem.
     *
     * Example:
     *
     * Input: [2,0,2,1,1,0]
     * Output: [0,0,1,1,2,2]
     *
     * [1, 2, 1]
     * Follow up:
     *
     * A rather straight forward solution is a two-pass algorithm using counting sort.
     * First, iterate the array counting number of 0's, 1's, and 2's, then overwrite array with total number of 0's,
     * then 1's and followed by 2's.
     * Could you come up with a one-pass algorithm using only constant space?
     * @param nums
     */
    // time = O(n), space = O(1)
    public void sortColors(int[] nums) {
        // corner case
        if (nums == null || nums.length == 0) return;

        int start = 0, end = nums.length - 1;
        int cur = 0;
        while (cur <= end) {
            if (nums[cur] == 0) swap(nums, cur++, start++);
            else if (nums[cur] == 1) cur++;
            else swap(nums, cur, end--);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
