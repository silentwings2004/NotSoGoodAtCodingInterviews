package Two_Pointers;

import java.util.Arrays;

class Solution {
    // time = O(n^2), space = O(logn)
    public int threeSumSmaller(int[] nums, int target) {
        // corner case
        if (nums == null || nums.length == 0) return 0;

        Arrays.sort(nums);

        int n = nums.length, res = 0;
        for (int i = 0; i < n; i++) {
            int left = i + 1, right = n - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum < target) {
                    res += right - left; // 固定left一头，right可以朝着left移动right - left步，都是答案
                    left++; // left收缩，继续看是否有符合条件的解出现
                } else right--;
            }
        }
        return res;
    }
}
/**
 * 注意计数的方法: count += right - left;
 * 
 */
