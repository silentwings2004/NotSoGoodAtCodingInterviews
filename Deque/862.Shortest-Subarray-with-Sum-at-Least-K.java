package Deque;

import java.util.*;

class Solution {
    // time = O(n), space = O(n)
    public int shortestSubarray(int[] nums, int k) {
        // corner case
        if (nums == null || nums.length == 0) return 0;

        int n = nums.length;
        int[] presum = new int[n + 1];
        for (int i = 1; i <= n; i++) presum[i] = presum[i - 1] + nums[i - 1];
        Deque<Integer> deque = new LinkedList<>();
        
        int res = Integer.MAX_VALUE;
        for (int i = 0; i <= n; i++) { // 特别注意： 这里是 i <= n !!! 等于的情况不要漏掉了！！！
            while (!deque.isEmpty() && presum[deque.peekFirst()] + k <= presum[i]) {
                res = Math.min(res, i - deque.peekFirst());
                deque.pollFirst();
            }
            while (!deque.isEmpty() && presum[deque.peekLast()] >= presum[i]) deque.pollLast();
            deque.offer(i);
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }
}
