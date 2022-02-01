import java.util.Deque;

/*
 * @lc app=leetcode id=1425 lang=java
 *
 * [1425] Constrained Subsequence Sum
 */

// @lc code=start
class Solution {
    // time = O(n), space = O(n)
    public int constrainedSubsetSum(int[] nums, int k) {
        int n = nums.length;
        Deque<Integer> deque = new LinkedList<>();
        int[] dp = new int[n];

        for (int i = 0; i < n; i++) {
            while (!deque.isEmpty() && deque.peekFirst() < i - k) deque.pollFirst();

            dp[i] = nums[i];
            if (!deque.isEmpty()) {
                dp[i] = Math.max(dp[i], dp[deque.peekFirst()] + nums[i]);
            }

            while (!deque.isEmpty() && dp[deque.peekLast()] <= dp[i]) {
                deque.pollLast();
            }
            deque.offer(i);
        }

        int res = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) res = Math.max(res, dp[i]);
        return res;
    }
}
// @lc code=end

