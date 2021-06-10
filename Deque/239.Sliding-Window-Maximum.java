package Deque;

import java.util.Deque;
import java.util.LinkedList;

class Solution {
    // time = O(n), space = O(n)
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        Deque<Integer> deque = new LinkedList<>();
        // deque.offerLast(0);

        int[] res = new int[n - k + 1];
        int idx = 0;
        for (int i = 0; i < n; i++) {
            if (!deque.isEmpty() && i - deque.peekFirst() >= k) deque.pollFirst();
            while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[i]) deque.pollLast();
            deque.offer(i);
            if (i >= k - 1)res[idx++] = nums[deque.peekFirst()];
        }
        return res;
    }
}