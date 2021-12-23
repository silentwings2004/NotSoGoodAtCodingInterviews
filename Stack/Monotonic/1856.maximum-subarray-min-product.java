/*
 * @lc app=leetcode id=1856 lang=java
 *
 * [1856] Maximum Subarray Min-Product
 */

// @lc code=start
class Solution {
    // time = O(n), space = O(n)
    public int maxSumMinProduct(int[] nums) {
        long M = (long)(1e9 + 7);
        int n = nums.length;
        int[] nextSmaller = new int[n];
        int[] prevSmaller = new int[n];
        Arrays.fill(nextSmaller, n);
        Arrays.fill(prevSmaller, -1);

        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] > nums[i]) {
                nextSmaller[stack.pop()] = i;
            }
            stack.push(i);
        }

        stack.clear();
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && nums[stack.peek()] > nums[i]) {
                prevSmaller[stack.pop()] = i;
            }
            stack.push(i);
        }

        long[] presum = new long[n];
        presum[0] = nums[0];
        for (int i = 1; i < n; i++) presum[i] = presum[i - 1] + nums[i];

        long res = 0;
        for (int i = 0; i < n; i++) {
            int a = prevSmaller[i];
            int b = nextSmaller[i];
            long sum = presum[b - 1] - (a == -1 ? 0 : presum[a]);
            res = Math.max(res, sum * nums[i]);
        }
        return (int)(res % M);
    }
}
// @lc code=end

