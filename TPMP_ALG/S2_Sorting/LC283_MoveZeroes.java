package S2_Sorting;
import java.util.*;
/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: MoveZeroes
 * Creater: Silentwings
 * Date: Aug, 2020
 * Description: 283. Move Zeroes
 */
public class LC283_MoveZeroes {
    /**
     * Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of
     * the non-zero elements.
     *
     * Example:
     *
     * Input: [0,1,0,3,12]
     * Output: [1,3,12,0,0]
     * Note:
     *
     * You must do this in-place without making a copy of the array.
     * Minimize the total number of operations.
     * @param nums
     */
    // S1: 补0
    // time = O(n), space = O(1)
    public void moveZeroes(int[] nums) {
        // conrer case
        if (nums == null || nums.length == 0) return;

        int start = 0;
        for (int n : nums) {
            if (n != 0) nums[start++] = n;
        }
        while (start < nums.length) nums[start++] = 0;
    }

    // S2: Swap
    // time = O(n), space = O(1)
    public void moveZeroes2(int[] nums){
        // conrer case
        if (nums == null || nums.length == 0) return;

        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                swap(nums, i, j++);
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
