import java.util.Deque;

/*
 * @lc app=leetcode id=239 lang=java
 *
 * [239] Sliding Window Maximum
 */

// @lc code=start
class Solution {
    // time = O(n), space = O(n)
    public int[] maxSlidingWindow(int[] nums, int k) {
        Deque<Integer> deque = new LinkedList<>();
        
        int n = nums.length, idx = 0;
        int[] res = new int[n - k + 1];
        for (int i = 0; i < n; i++) {
            while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[i]) deque.pollLast();
            deque.offer(i);

            // check if head is outdated or not
            while (!deque.isEmpty() && deque.peekFirst() <= i - k) deque.pollFirst();
            if (i >= k - 1) res[idx++] = nums[deque.peekFirst()];
        }
        return res;
    }
}
// @lc code=end

