import java.util.Deque;

/*
 * @lc app=leetcode id=1438 lang=java
 *
 * [1438] Longest Continuous Subarray With Absolute Diff Less Than or Equal to Limit
 */

// @lc code=start
class Solution {
    // time = O(n), space = O(n)
    public int longestSubarray(int[] nums, int limit) {
        int n = nums.length;
        Deque<Integer> qmax = new LinkedList<>();
        Deque<Integer> qmin = new LinkedList<>();
        qmax.offer(0);
        qmin.offer(0);

        int res = 0, j = 0, max = nums[0], min = nums[0];
        for (int i = 0; i < n; i++) {
            while (max - min <= limit) {
                res = Math.max(res, j - i + 1);
                j++;
                if (j == n) break;

                while (!qmax.isEmpty() && nums[qmax.peekLast()] <= nums[j]) qmax.pollLast();
                qmax.offer(j);
                max = nums[qmax.peekFirst()];
                
                while (!qmin.isEmpty() && nums[qmin.peekLast()]  >= nums[j]) qmin.pollLast();
                qmin.offer(j);
                min = nums[qmin.peekFirst()];
            }
            if (j == n) break;

            if (!qmax.isEmpty() && qmax.peekFirst() == i) {
                qmax.pollFirst();
                if (!qmax.isEmpty()) max = nums[qmax.peekFirst()];
            }

            if (!qmin.isEmpty() && qmin.peekFirst() == i) {
                qmin.pollFirst();
                if (!qmin.isEmpty()) min = nums[qmin.peekFirst()];
            }
        }
        return res;
    }
}
// @lc code=end

