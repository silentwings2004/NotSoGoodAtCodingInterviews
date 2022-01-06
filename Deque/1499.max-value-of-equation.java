import java.util.Deque;

/*
 * @lc app=leetcode id=1499 lang=java
 *
 * [1499] Max Value of Equation
 */

// @lc code=start
class Solution {
    // time = O(n), space = O(n)
    public int findMaxValueOfEquation(int[][] points, int k) {
        int n = points.length;
        Deque<Integer> deque = new LinkedList<>();

        int res = Integer.MIN_VALUE;
        for (int j = 0; j < n; j++) {
            while (!deque.isEmpty() && points[j][0] - points[deque.peekFirst()][0] > k) {
                deque.pollFirst();
            }
            if (!deque.isEmpty()) {
                res = Math.max(res, points[deque.peekFirst()][1] + points[j][1] + points[j][0] - points[deque.peekFirst()][0]);
            }
            
            while (!deque.isEmpty() && points[deque.peekLast()][1] - points[deque.peekLast()][0] <= points[j][1] - points[j][0]) {
                deque.pollLast();
            }
            deque.offer(j);
        }
        return res;
    }
}
// @lc code=end

