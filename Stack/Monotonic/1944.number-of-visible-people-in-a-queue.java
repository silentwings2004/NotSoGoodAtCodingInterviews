/*
 * @lc app=leetcode id=1944 lang=java
 *
 * [1944] Number of Visible People in a Queue
 */

// @lc code=start
class Solution {
    // time = O(n), space = O(n)
    public int[] canSeePersonsCount(int[] heights) {
        int n = heights.length;
        Stack<Integer> stack = new Stack<>();

        int[] res = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            int count = 0;
            while(!stack.isEmpty() && heights[stack.peek()] < heights[i]) {
                count++;
                stack.pop();
            }
            if (!stack.isEmpty()) count++;
            res[i] = count;
            stack.push(i);
        }
        return res;
    }
}
// @lc code=end

