package S1_BS;
import java.util.*;
/**
 * Project Name: Lintcode
 * Package Name: lintcode
 * File Name: RecoverRotatedSortedArray
 * Creater: Silentwings
 * Date: Aug, 2020
 * Description: 39. Recover Rotated Sorted Array
 */
public class LN39_RecoverRotatedSortedArray {
    /**
     * Given a rotated sorted array, recover it to sorted array in-place.（Ascending）
     *
     * Example
     * Example1:
     * [4, 5, 1, 2, 3] -> [1, 2, 3, 4, 5]
     *
     * Example2:
     * [6,8,9,1,2] -> [1,2,6,8,9]
     *
     * Challenge
     * In-place, O(1) extra space and O(n) time.
     *
     * Clarification
     * What is rotated array?
     *
     * For example, the orginal array is [1,2,3,4], The rotated array of it can be [1,2,3,4], [2,3,4,1], [3,4,1,2],
     * [4,1,2,3]
     * @param nums
     */
    // time = O(n), space = O(1)
    public void recoverRotatedSortedArray(List<Integer> nums) {
        // corner case
        if (nums == null || nums.size() <= 1) return;

        int pos = 0;
        for (int i = 1; i < nums.size(); i++) {
            if (nums.get(i) < nums.get(i - 1)) {
                int temp = nums.get(i);
                nums.remove(i);
                nums.add(pos++, temp);
            }
        }
    }
}
