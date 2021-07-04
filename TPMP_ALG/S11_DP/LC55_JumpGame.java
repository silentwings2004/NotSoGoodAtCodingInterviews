package S11_DP;
import java.util.*;
/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: JumpGame
 * Creater: Silentwings
 * Date: Mar, 2020
 * Description: 55. Jump Game
 */
public class LC55_JumpGame {
    /**
     * Given an array of non-negative integers, you are initially positioned at the first index of the array.
     *
     * Each element in the array represents your maximum jump length at that position.
     *
     * Determine if you are able to reach the last index.
     *
     * Example 1:
     *
     * Input: [2,3,1,1,4]
     * Output: true
     * Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
     * Example 2:
     *
     * Input: [3,2,1,0,4]
     * Output: false
     * Explanation: You will always arrive at index 3 no matter what. Its maximum jump length is 0,
     * which makes it impossible to reach the last index.
     *
     * @param nums
     * @return
     */
    // S1: DFS recursion --> Time Limit Exceeded (not recommend)
    public boolean canJump(int[] nums) {
        // corner case
        if (nums == null || nums.length == 0) return false;

        int index = 0;
        boolean res = helper(nums, index);
        return res;
    }

    private boolean helper(int[] nums, int index) {
        if (index >= nums.length - 1) return true;
        int jump = nums[index];

        for (int i = 1; i <= jump; i++) {
            if (helper(nums, index + i)) return true;
        }
        return false;
    }

    // S2: DP (由近及远)
    public boolean canJump2(int[] nums) {
        // corner case
        if (nums == null || nums.length == 0) return false;

        boolean[] dp = new boolean[nums.length];
        dp[nums.length - 1] = true;

        for (int i = nums.length - 2; i >= 0; i--) {
            for (int j = 1; j <= nums[i]; j++) {
//                if (i + j < nums.length && dp[i + j]) {  // i + j >= nums.length - 1 || dp[i + j]
                if (dp[i + j]) {  // 出界前一定已经能跳到最后一个位置而返回true结束，所以这里不需要额外check出界问题
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[0];
    }

    // S2.1: DP (由远及近)
    public boolean canJump21(int[] nums) {
        // corner case
        if (nums == null || nums.length == 0) return false;

        boolean[] dp = new boolean[nums.length];
        dp[nums.length - 1] = true; // if nums.length == 1, dp[0]直接跳过下面for loop返回true

        for (int i = nums.length - 2; i >= 0; i--) {
            for (int j = nums[i]; j >= 1; j--) {
                if (i + j >= nums.length - 1 || dp[i + j]) { // edge case出界判定条件不能省，因为这里是由远及近，可能先出界再拉回来
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[0];
    }

    // S2.2: DP (in-place)
    public boolean canJump22(int[] nums) {
        // corner case
        if (nums == null || nums.length == 0) return false;

        // true --> -1, false --> -2
        nums[nums.length - 1] = -1;

        for (int i = nums.length - 2; i >= 0; i--) {
            for (int j = 1; j <= nums[i]; j++) {
                if (nums[i + j] == -1) {
                    nums[i] = -1;
                    break;
                }
            }
            if (nums[i] >= 0) nums[i] = -2; // nums[i] didn't be set up as true, then set up as false here
        }
        return nums[0] == -1;
    }

    // S3: Greedy
    public boolean canJump3(int[] nums) {
        // corner case
        if (nums == null || nums.length == 0) return false;

        int maxRange = 0;
        for (int i = 0; i <= maxRange; i++) {
            maxRange = Math.max(maxRange, i + nums[i]);
            if (maxRange >= nums.length - 1) return true;
        }
        return false;
    }
}
