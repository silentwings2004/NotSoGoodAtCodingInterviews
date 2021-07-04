package S11_DP;
import java.util.*;

/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: MaximumSubarray
 * Creater: Silentwings
 * Date: Mar, 2020
 * Description: 53. Maximum Subarray
 */
public class LC53_MaximumSubarray {
    /**
     * Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest
     * sum and return its sum.
     *
     * Example:
     *
     * Input: [-2,1,-3,4,-1,2,1,-5,4],
     * Output: 6
     * Explanation: [4,-1,2,1] has the largest sum = 6.
     * Follow up:
     *
     * If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach,
     * which is more subtle.
     * @param nums
     * @return
     */
    // S1: time = O(n)   space = O(n)
    public int maxSubArray(int[] nums) {
        // corner case
        if (nums == null || nums.length == 0) throw new IllegalArgumentException();

        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int max = dp[0];

        for (int i = 1; i < nums.length; i++) {
            if (dp[i -1] >= 0) dp[i] = dp[i - 1] + nums[i];
            else dp[i] = nums[i];
            max = Math.max(dp[i], max);
        }
        return max;
    }

    // S2: time = O(n)   space = O(1)
    public int maxSubArray2(int[] nums) {
        // corner case
        if (nums == null || nums.length == 0) throw new IllegalArgumentException();

        int dp = nums[0];
        int max = dp;

        for (int i = 1; i < nums.length; i++) {
            if (dp >= 0) dp += nums[i];
            else dp = nums[i];
            max = Math.max(dp, max);
        }
        return max;
    }
}
