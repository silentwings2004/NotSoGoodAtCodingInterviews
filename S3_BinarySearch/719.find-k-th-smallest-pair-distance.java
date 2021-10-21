/*
 * @lc app=leetcode id=719 lang=java
 *
 * [719] Find K-th Smallest Pair Distance
 */

// @lc code=start
class Solution {
    // time = O(nlogn), space = O(1)
    public int smallestDistancePair(int[] nums, int k) {
        Arrays.sort(nums);

        int n = nums.length;
        int right = nums[n - 1] - nums[0];
        int left = Integer.MAX_VALUE;
        for (int i = 1; i < n; i++) left = Math.min(left, nums[i] - nums[i - 1]);

        while (left < right) {
            int mid = left + (right - left) / 2;
            int count = 0;
            for (int i = 0; i < n; i++) {
                int idx = upperBound(nums, nums[i] + mid);
                count += idx - i;
            }
            if (count < k) left = mid + 1;
            else right = mid;
        }
        return left;
    }

    private int upperBound(int[] nums, int t) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = right - (right - left) / 2;
            if (nums[mid] <= t) left = mid;
            else right = mid - 1;
        }
        return nums[left] <= t ? left : left - 1;
    }
}
// @lc code=end

