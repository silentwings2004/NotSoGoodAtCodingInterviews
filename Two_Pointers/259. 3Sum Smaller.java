package Two_Pointers;

import java.util.Arrays;

class Solution {
    // time = O(n^2), space = O(logn)
    public int threeSumSmaller(int[] nums, int target) {
        // corner case
        if (nums == null || nums.length == 0) return 0;

        Arrays.sort(nums);
        int res = 0, n = nums.length;

        for (int i = 0; i < n - 2; i++) {
            res += twoSumSmaller(nums, target - nums[i], i + 1);
        }
        return res;
    }

    private int twoSumSmaller(int[] nums, int target, int left) {
        int count = 0, right = nums.length - 1;
        while (left < right) {
            if (nums[left] + nums[right] < target) {
                count += right - left;
                left++;
            } else right--;
        }
        return count;
    }
}
/**
 * 注意计数的方法: count += right - left;
 * 
 */
