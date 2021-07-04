package S1_BS;
import java.util.*;
/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: FindKClosestElements
 * Creater: Silentwings
 * Date: Aug, 2020
 * Description: 658. Find K Closest Elements
 */
public class LC658_FindKClosestElements {
    /**
     * Given a sorted array arr, two integers k and x, find the k closest elements to x in the array. The result should
     * also be sorted in ascending order. If there is a tie, the smaller elements are always preferred.
     *
     *
     *
     * Example 1:
     *
     * Input: arr = [1,2,3,4,5], k = 4, x = 3
     * Output: [1,2,3,4]
     *
     * Example 2:
     *
     * Input: arr = [1,2,3,4,5], k = 4, x = -1
     * Output: [1,2,3,4]
     *
     *
     * Constraints:
     *
     * 1 <= k <= arr.length
     * 1 <= arr.length <= 10^4
     * Absolute value of elements in the array and x will not exceed 104
     * @param arr
     * @param k
     * @param x
     * @return
     */
    // time = O(logn), space = O(1)
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> res = new ArrayList<>();
        // corner case
        if (arr == null || arr.length == 0 || k <= 0) return res;

        int n = arr.length;
        int left = 0, right = n - k; // left, right 都是 k + 1 size window 左端点的取值范围
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (x - arr[mid] > arr[mid + k] - x) { // 关键在于arr[mid + k]!!!
                left = mid + 1;
            } else right = mid;
        }
        for (int i = left; i < left + k; i++) res.add(arr[i]);
        return res;
    }
}
