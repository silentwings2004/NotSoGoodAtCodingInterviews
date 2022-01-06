import java.util.Deque;

/*
 * @lc app=leetcode id=862 lang=java
 *
 * [862] Shortest Subarray with Sum at Least K
 */

// @lc code=start
class Solution {
    // time = O(n), space = O(n)
    public int shortestSubarray(int[] nums, int k) {
        int n = nums.length;
        long[] presum = new long[n + 1];
        for (int i = 1; i <= n; i++) presum[i] = presum[i - 1] + nums[i - 1];

        Deque<Integer> deque = new LinkedList<>();
        deque.offer(0);

        int res = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            while (!deque.isEmpty() && presum[deque.peekFirst()] <= presum[i] - k) {
                res = Math.min(res, i - deque.pollFirst());
            }

            while (!deque.isEmpty() && presum[deque.peekLast()] >= presum[i]) {
                deque.pollLast();
            }
            deque.offer(i);
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }
}
// @lc code=end

