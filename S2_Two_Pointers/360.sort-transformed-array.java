import java.awt.List;

/*
 * @lc app=leetcode id=360 lang=java
 *
 * [360] Sort Transformed Array
 */

// @lc code=start
class Solution {
    // time = O(n), space = O(1)
    public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
        int n = nums.length;
        int[] res = new int[n];
        int left = 0, right = n - 1;
        int i = a >= 0 ? n - 1 : 0;

        while (left <= right) {
            int start = getVal(nums[left], a, b, c);
            int end = getVal(nums[right], a, b, c);
            if (a >= 0) {
                if (start >= end) {
                    res[i--] = start;
                    left++;
                } else {
                    res[i--] = end;
                    right--;
                }
            } else {
                if (start <= end) {
                    res[i++] = start;
                    left++; 
                } else {
                    res[i++] = end;
                    right--;
                }
            }
        }
        return res;
    }

    private int getVal(int x, int a, int b, int c) {
        return a * x * x + b * x + c;
    }
}
// @lc code=end

