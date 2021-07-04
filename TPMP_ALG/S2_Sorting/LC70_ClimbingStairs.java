package S2_Sorting;
import java.util.*;
/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: ClimbingStairs
 * Creater: Silentwings
 * Date: Aug, 2020
 * Description: 70. Climbing Stairs
 */
public class LC70_ClimbingStairs {
    /**
     * You are climbing a stair case. It takes n steps to reach to the top.
     *
     * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
     *
     * Example 1:
     *
     * Input: 2
     * Output: 2
     * Explanation: There are two ways to climb to the top.
     * 1. 1 step + 1 step
     * 2. 2 steps
     *
     * Example 2:
     *
     * Input: 3
     * Output: 3
     * Explanation: There are three ways to climb to the top.
     * 1. 1 step + 1 step + 1 step
     * 2. 1 step + 2 steps
     * 3. 2 steps + 1 step
     *
     *
     * Constraints:
     *
     * 1 <= n <= 45
     * @param n
     * @return
     */
    // time = O(n), space = O(n)
    public int climbStairs(int n) {
        // corner case
        if (n == 1 || n == 2) return n;

        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 2] + dp[i - 1];
        }
        return dp[n];
    }
}
