package Deque;

import java.util.Deque;
import java.util.LinkedList;

class Solution {
    public int constrainedSubsetSum(int[] nums, int k) {
        // corner case
        if (nums == null || nums.length == 0) return 0;

        int n = nums.length;
        int[] dp = new int[n];
        Deque<Integer> deque = new LinkedList<>();
        
        for (int i = 0; i < n; i++) {
            while (!deque.isEmpty() && i - deque.peekFirst() > k) deque.pollFirst();
            dp[i] = nums[i];
            if (!deque.isEmpty()) dp[i] = Math.max(dp[i], dp[deque.peekFirst()] + nums[i]);
            while (!deque.isEmpty() && dp[deque.peekLast()] <= dp[i]) deque.pollLast();
            deque.offer(i);
        }
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) res = Math.max(res, dp[i]);
        return res;
    }
}
/**
 * 维护一个单调递减序列
 */