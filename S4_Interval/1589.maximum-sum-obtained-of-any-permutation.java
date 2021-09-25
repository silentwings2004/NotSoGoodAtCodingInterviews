/*
 * @lc app=leetcode id=1589 lang=java
 *
 * [1589] Maximum Sum Obtained of Any Permutation
 */

// @lc code=start
class Solution {
    // time = O(nlogn), space = O(n)
    public int maxSumRangeQuery(int[] nums, int[][] requests) {
        int n = nums.length;
        int[] diff = new int[n + 1];

        for (int[] request : requests) {
            diff[request[0]]++;
            diff[request[1] + 1]--;
        }
        
        // 重构freq
        int[] freq = new int[n];
        int pre = 0; // pre需要累加
        for (int i = 0; i < n; i++) {
            pre += diff[i];
            freq[i] = pre;
        }

        Arrays.sort(freq);
        Arrays.sort(nums);

        long res = 0;
        long M = (long)(1e9 + 7);
        for (int i = 0; i < n; i++) {
            res = (res + (long) freq[i] * (long) nums[i]) % M;
        }
        return (int) res;
    }
}
// @lc code=end

