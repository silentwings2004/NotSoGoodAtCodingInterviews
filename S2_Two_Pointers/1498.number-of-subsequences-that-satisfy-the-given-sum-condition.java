/*
 * @lc app=leetcode id=1498 lang=java
 *
 * [1498] Number of Subsequences That Satisfy the Given Sum Condition
 */

// @lc code=start
class Solution {
    // time = O(n), space = O(n)
    public int numSubseq(int[] nums, int target) {
        // corner case
        if (nums == null || nums.length == 0) return 0;
        
        Arrays.sort(nums);
        
        int n = nums.length;
        long M = (long)(1e9 + 7);
        
        long[] power = new long[n + 1];
        Arrays.fill(power, 1);
        for (int i = 1; i <= n; i++) {
            power[i] = power[i - 1] * 2 % M;
        }
        
        int j = n - 1;
        long res = 0;
        for (int i = 0; i < n; i++) {
            while (j >= i && nums[i] + nums[j] > target) j--;
            if (j < i) break;
            res = (res + power[j - i]) % M;
        }
        return (int)res;
    }
}
// @lc code=end

