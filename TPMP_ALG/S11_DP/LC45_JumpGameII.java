package S11_DP;
import java.util.*;

/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: JumpGameII
 * Creater: Silentwings
 * Date: Mar, 2020
 * Description: 45. Jump Game II
 */
public class LC45_JumpGameII {
    /**
     * Given an array of non-negative integers, you are initially positioned at the first index of the array.
     *
     * Each element in the array represents your maximum jump length at that position.
     *
     * Your goal is to reach the last index in the minimum number of jumps.
     *
     * Example:
     *
     * Input: [2,3,1,1,4]
     * Output: 2
     * Explanation: The minimum number of jumps to reach the last index is 2.
     *     Jump 1 step from index 0 to 1, then 3 steps to the last index.
     * Note:
     *
     * You can assume that you can always reach the last index.
     * @param nums
     * @return
     */
    // S1: DP
    public int jump1(int[] nums) {
        // corner case
        if (nums == null || nums.length < 2) return 0;

        int n = nums.length;
        int[] dp = new int[n];
        dp[n - 1] = 0;

        for (int i = n - 2; i >= 0; i--) {
            int min = Integer.MAX_VALUE;
            for (int j = 1; j <= nums[i]; j++) {
                if (i + j < n) min = Math.min(dp[i + j], min);
            }
            if (min == Integer.MAX_VALUE) dp[i] = Integer.MAX_VALUE;
            else dp[i] = min + 1;
        }
        return dp[0];
    }

    // S1.1: DP (in-place)
    public int jump11(int[] nums) {
        // corner case
        if (nums == null || nums.length < 2) return 0;

        int n = nums.length;
        nums[n - 1] = 0;

        for (int i = n - 2; i >= 0; i--) {
            int min = Integer.MAX_VALUE;
            for (int j = 1; j <= nums[i]; j++) {
                if (i + j < n) min = Math.min(nums[i + j], min);
            }
            if (min == Integer.MAX_VALUE) nums[i] = Integer.MAX_VALUE;
            else nums[i] = min + 1;
        }
        return nums[0];
    }

    // S2: Greedy time = O(n)
    public int jump(int[] nums) {
        // corner case
        if (nums == null || nums.length < 2) return 0;

        int res = 0;
        int preMax = 0;
        int curMax = 0; // 能到达的最远位置

        for (int i = 0; i < nums.length; i++) { // use curMax to justify whether you can can hit the target or not
            if (curMax >= nums.length - 1) return res + 1;
            if (i > preMax) {
                preMax = curMax;
                res++;
            }
            curMax = Math.max(curMax, i + nums[i]); // i vs cur
        }
        return 0;
    }
}
