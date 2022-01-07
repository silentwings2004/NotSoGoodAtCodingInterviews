import java.util.Deque;

/*
 * @lc app=leetcode id=1696 lang=java
 *
 * [1696] Jump Game VI
 */

// @lc code=start
class Solution {
    // time = O(n), space = O(n)
    public int maxResult(int[] nums, int k) {
        int n = nums.length;
        Deque<int[]> deque = new LinkedList<>();
        deque.offer(new int[]{nums[0], 0});

        for (int i = 1; i < n; i++) {
            // check if the head is outdated or not
            while (!deque.isEmpty() && i - deque.peekFirst()[1] > k) deque.pollFirst();
            int curSteps = deque.peekFirst()[0] + nums[i];
            // check if the tail needs to pop out
            while (!deque.isEmpty() && deque.peekLast()[0] < curSteps) deque.pollLast();
            deque.offer(new int[]{curSteps, i});
        }
        return deque.peekLast()[0];
    }
}
// @lc code=end

