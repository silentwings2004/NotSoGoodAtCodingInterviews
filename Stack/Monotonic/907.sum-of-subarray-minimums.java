/*
 * @lc app=leetcode id=907 lang=java
 *
 * [907] Sum of Subarray Minimums
 */

// @lc code=start
class Solution {
    // time = O(n), space = O(n)
    public int sumSubarrayMins(int[] arr) {
        long M= (long)(1e9 + 7);
        int n = arr.length;
        int[] nextSmaller = new int[n];
        int[] prevSmaller = new int[n];
        Arrays.fill(nextSmaller, n);
        Arrays.fill(prevSmaller, -1);

        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                nextSmaller[stack.pop()] = i;
            }
            stack.push(i);
        }

        stack.clear();
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                prevSmaller[stack.pop()] = i;
            }
            stack.push(i);
        }

        long res = 0;
        for (int i = 0; i < n; i++) {
            int a = prevSmaller[i];
            int b = nextSmaller[i];
            long num = (i - a) * (b - i) % M * arr[i] % M;
            res = (res + num) % M;
        }
        return (int) res;
    }
}
// @lc code=end

